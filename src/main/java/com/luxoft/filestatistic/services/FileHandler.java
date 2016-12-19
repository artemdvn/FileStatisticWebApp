package com.luxoft.filestatistic.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.luxoft.filestatistic.model.FileStatistic;
import com.luxoft.filestatistic.model.LineOfFile;

public class FileHandler {

	private FileStatistic fileStatistic;
	private List<LineOfFile> linesOfFile = new ArrayList<LineOfFile>();

	private int sumWordsOfFileLenght = 0;
	private int wordsOfFileCount = 0;

	public FileHandler() {
	}
	
	public FileStatistic getFileStatistic() {
		return fileStatistic;
	}

	public void setFileStatistic(FileStatistic fs) {
		this.fileStatistic = fs;
	}

	public List<LineOfFile> getLinesOfFile() {
		return linesOfFile;
	}

	public void setLinesOfFile(List<LineOfFile> linesOfFile) {
		this.linesOfFile = linesOfFile;
	}

	public void handleFile(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			fileStatistic = new FileStatistic(new File(filename).getName());
			
			String s;
			int lineCounter = 0;
			while ((s = br.readLine()) != null) {
				handleLine(s, lineCounter);
				lineCounter++;
			}
			br.close();

			double averageWordLenghtOfFile = (wordsOfFileCount == 0) ? 0
					: (double) sumWordsOfFileLenght / (double) wordsOfFileCount;
			fileStatistic.setAverageWordLenghtOfFile(averageWordLenghtOfFile);

		} catch (Exception e) {
			System.out.print("Error reading from file");
		}
	}

	public void handleLine(String str, int lineCounter) {
		if (str == null)
			return;
		if (str.length() == 0) {
			return;
		}

		LineOfFile currentLine = new LineOfFile(str, lineCounter, fileStatistic);
		String shortestWord = "";
		String longestWord = "";

		int maxLenght = str.length();
		int minLenght = 0;
		int sumWordsLenght = 0;
		int wordsCount = 0;

		String words[] = str.split(" ");
		for (String word : words) {
			int wordLength = word.length();

			if (wordLength == 0) {
				continue;
			}
			if (wordLength < maxLenght) {
				shortestWord = word;
				maxLenght = wordLength;
			}
			if (wordLength > minLenght) {
				longestWord = word;
				minLenght = wordLength;
			}
			sumWordsLenght += wordLength;
			wordsCount++;
		}

		if (longestWord.length() == 0) {
			return;
		}

		currentLine.setLongestWord(longestWord);
		currentLine.setShortestWord(shortestWord);

		double averageWordLenght = (wordsCount == 0) ? 0 : (double) sumWordsLenght / (double) wordsCount;
		currentLine.setAverageWordLenght(averageWordLenght);

		linesOfFile.add(currentLine);

		if (fileStatistic.getShortestWordOfFile() == null) {
			fileStatistic.setShortestWordOfFile(shortestWord);
		}
		if (shortestWord.length() < fileStatistic.getShortestWordOfFile().length()) {
			fileStatistic.setShortestWordOfFile(shortestWord);
		}

		if (fileStatistic.getLongestWordOfFile() == null) {
			fileStatistic.setLongestWordOfFile(longestWord);
		}
		if (longestWord.length() > fileStatistic.getLongestWordOfFile().length()) {
			fileStatistic.setLongestWordOfFile(longestWord);
		}
		sumWordsOfFileLenght += sumWordsLenght;
		wordsOfFileCount += wordsCount;
	}
}
