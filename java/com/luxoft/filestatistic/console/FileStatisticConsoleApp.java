package com.luxoft.filestatistic.console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.luxoft.filestatistic.dao.FileStatisticDao;
import com.luxoft.filestatistic.dao.FileStatisticDaoImpl;
import com.luxoft.filestatistic.dao.LineOfFileDao;
import com.luxoft.filestatistic.dao.LineOfFileDaoImpl;
import com.luxoft.filestatistic.model.FileStatistic;
import com.luxoft.filestatistic.model.LineOfFile;

public class FileStatisticConsoleApp {

	private FileStatistic fileStatistic;
	private List<LineOfFile> linesOfFile = new ArrayList<LineOfFile>();

	private int sumWordsOfFileLenght = 0;
	private int wordsOfFileCount = 0;

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

	public static void main(String... args) {

		FileStatisticConsoleApp app = new FileStatisticConsoleApp();
		System.out.println("Welcome to File Statistic app!");

		Scanner in = new Scanner(System.in);
		System.out.print("Please enter filename: ");
		String filename = in.nextLine();
		in.close();

		app.handleFile(filename);

		if (app.getFileStatistic() != null) {
			System.out.println("----------------------------");
			System.out.println("Lines Statistic");
			System.out.println("----------------------------");
			for (LineOfFile line : app.getLinesOfFile()) {
				System.out.println(line.toString());
			}

			System.out.println("----------------------------");
			System.out.println("File Statistic");
			System.out.println("----------------------------");
			System.out.println(app.getFileStatistic().toString());
		}
		
		FileStatisticDao fileDao = FileStatisticDaoImpl.getInstance();
		Long fileId = fileDao.saveFileStatistic(app.getFileStatistic());
		
		LineOfFileDao lineDao = LineOfFileDaoImpl.getInstance();
		List<LineOfFile> lines = app.getLinesOfFile();
		for (LineOfFile line : lines) {			
			lineDao.saveLineOfFile(line, fileId);
		}
		
		System.out.println("----------------------------");
		System.out.println("Aggregate files statistic from DB");
		System.out.println("----------------------------");
		List<FileStatistic> lfs = fileDao.getAllFileStatistic();
		for (FileStatistic fs : lfs) {
			System.out.println(fs.toString());
			List<LineOfFile> linesFromFile = lineDao.getAllLinesOfFile(fs);
			for (LineOfFile line : linesFromFile) {
				System.out.println(line.toString());
			}
			System.out.println("----------------------------");
		}

	}

	public void handleFile(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			fileStatistic = new FileStatistic(filename);
			
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
