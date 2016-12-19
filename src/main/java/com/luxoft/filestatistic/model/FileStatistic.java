package com.luxoft.filestatistic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class FileStatistic implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String filename;
	private String shortestWordOfFile;
	private String longestWordOfFile;
	private Double averageWordLenghtOfFile;
	
	public FileStatistic() {
	}
	
	public FileStatistic(String filename) {
		this.filename = filename;
	}

	@Id
    @GeneratedValue
    @Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "filename")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "shortestWord")
	public String getShortestWordOfFile() {
		return shortestWordOfFile;
	}

	public void setShortestWordOfFile(String shortestWordOfFile) {
		this.shortestWordOfFile = shortestWordOfFile;
	}

	@Column(name = "longestWord")
	public String getLongestWordOfFile() {
		return longestWordOfFile;
	}

	public void setLongestWordOfFile(String longestWordOfFile) {
		this.longestWordOfFile = longestWordOfFile;
	}

	@Column(name = "averageWordLenght")
	public Double getAverageWordLenghtOfFile() {
		return averageWordLenghtOfFile;
	}

	public void setAverageWordLenghtOfFile(double averageWordLenghtOfFile) {
		this.averageWordLenghtOfFile = averageWordLenghtOfFile;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		FileStatistic other = (FileStatistic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileStatistic [id=" + id + ", filename=" + filename + ", shortestWordOfFile=" + shortestWordOfFile
				+ ", longestWordOfFile=" + longestWordOfFile 
				+ ", averageWordLenghtOfFile=" + averageWordLenghtOfFile
				+ "]";
	}
	
	

}
