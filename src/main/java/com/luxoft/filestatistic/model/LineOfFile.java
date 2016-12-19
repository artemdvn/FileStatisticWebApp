package com.luxoft.filestatistic.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "filelines")
public class LineOfFile {
	
	@EmbeddedId
	LineOfFilePK id;
	
	private String line;
	private String longestWord;
	private String shortestWord;
	private Double averageWordLenght;
	
	public LineOfFile() {
	}
	
	public LineOfFile(String line, int lineNumberInFile, FileStatistic fs) {
		this.line = line;
		this.id = new LineOfFilePK(lineNumberInFile, fs);
	}
	
	public LineOfFilePK getId() {
		return id;
	}

	public void setId(LineOfFilePK id) {
		this.id = id;
	}

	@Column(name = "line")
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Column(name = "longestWord")
	public String getLongestWord() {
		return longestWord;
	}

	public void setLongestWord(String longestWord) {
		this.longestWord = longestWord;
	}

	@Column(name = "shortestWord")
	public String getShortestWord() {
		return shortestWord;
	}

	public void setShortestWord(String shortestWord) {
		this.shortestWord = shortestWord;
	}

	@Column(name = "averageWordLenght")
	public Double getAverageWordLenght() {
		return averageWordLenght;
	}

	public void setAverageWordLenght(double averageWordLenght) {
		this.averageWordLenght = averageWordLenght;
	}

	@Override
	public String toString() {
		return "LineOfFile [line=" + line + ", lineNumberInFile=" + getId().getLineNumberInFile() + ", fileId=" + getId().getFileId() + ", longestWord="
				+ longestWord + ", LINE LENGTH=" + line.length() + ", shortestWord=" + shortestWord + ", averageWordLenght=" + averageWordLenght + "]";
	}	
	

}
