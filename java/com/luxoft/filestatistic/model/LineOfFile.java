package com.luxoft.filestatistic.model;

public class LineOfFile {
	
	private String line;
	private int lineNumberInFile;
	private FileStatistic fs;
	private String longestWord;
	private String shortestWord;
	private double averageWordLenght;
	
	public LineOfFile(String line, int lineNumberInFile, FileStatistic fs) {
		this.line = line;
		this.lineNumberInFile = lineNumberInFile;
		this.fs = fs;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public int getLineNumberInFile() {
		return lineNumberInFile;
	}

	public void setLineNumberInFile(int lineNumberInFile) {
		this.lineNumberInFile = lineNumberInFile;
	}

	public FileStatistic getFs() {
		return fs;
	}

	public void setFs(FileStatistic fs) {
		this.fs = fs;
	}

	public String getLongestWord() {
		return longestWord;
	}

	public void setLongestWord(String longestWord) {
		this.longestWord = longestWord;
	}

	public String getShortestWord() {
		return shortestWord;
	}

	public void setShortestWord(String shortestWord) {
		this.shortestWord = shortestWord;
	}

	public double getAverageWordLenght() {
		return averageWordLenght;
	}

	public void setAverageWordLenght(double averageWordLenght) {
		this.averageWordLenght = averageWordLenght;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fs == null) ? 0 : fs.hashCode());
		result = prime * result + lineNumberInFile;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineOfFile other = (LineOfFile) obj;
		if (fs == null) {
			if (other.fs != null)
				return false;
		} else if (!fs.equals(other.fs))
			return false;
		if (lineNumberInFile != other.lineNumberInFile)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LineOfFile [line=" + line + ", lineNumberInFile=" + lineNumberInFile + ", fs=" + fs.getFilename() + ", longestWord="
				+ longestWord + ", LINE LENGTH=" + line.length() + ", shortestWord=" + shortestWord + ", averageWordLenght=" + averageWordLenght + "]";
	}	
	

}
