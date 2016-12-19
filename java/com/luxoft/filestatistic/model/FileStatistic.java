package com.luxoft.filestatistic.model;

public class FileStatistic {
	
	private Long id;
	private String filename;
	private String shortestWordOfFile;
	private String longestWordOfFile;
	private double averageWordLenghtOfFile;
	
	private static Long nextId = 0L;
	
	protected FileStatistic(final Long id) {
		this.id = id;
	}

	public FileStatistic(String filename) {
		this(nextId++);
		if (nextId == Long.MAX_VALUE) {
			throw new RuntimeException(String.format("%s has reached maximum ID value. " + "Restart the Application.",
					this.getClass().getSimpleName()));
		}
		this.filename = filename;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getShortestWordOfFile() {
		return shortestWordOfFile;
	}

	public void setShortestWordOfFile(String shortestWordOfFile) {
		this.shortestWordOfFile = shortestWordOfFile;
	}

	public String getLongestWordOfFile() {
		return longestWordOfFile;
	}

	public void setLongestWordOfFile(String longestWordOfFile) {
		this.longestWordOfFile = longestWordOfFile;
	}

	public double getAverageWordLenghtOfFile() {
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
