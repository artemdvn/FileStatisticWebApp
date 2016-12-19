package com.luxoft.filestatistic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LineOfFilePK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer lineNumberInFile;
	private Long fileId;
	
	public LineOfFilePK() {
	}
	
	public LineOfFilePK(Integer lineNumberInFile, FileStatistic fs) {
		this.lineNumberInFile = lineNumberInFile;
		this.fileId = fs.getId();
	}

	@Column(name = "lineNumber")
	public Integer getLineNumberInFile() {
		return lineNumberInFile;
	}

	public void setLineNumberInFile(Integer lineNumberInFile) {
		this.lineNumberInFile = lineNumberInFile;
	}

	@Column(name = "fileId")
	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result + ((lineNumberInFile == null) ? 0 : lineNumberInFile.hashCode());
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
		LineOfFilePK other = (LineOfFilePK) obj;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		if (lineNumberInFile == null) {
			if (other.lineNumberInFile != null)
				return false;
		} else if (!lineNumberInFile.equals(other.lineNumberInFile))
			return false;
		return true;
	}	

}
