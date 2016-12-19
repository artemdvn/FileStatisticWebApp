package com.luxoft.filestatistic.dao;

import java.util.List;

import com.luxoft.filestatistic.model.FileStatistic;
import com.luxoft.filestatistic.model.LineOfFile;

public interface LineOfFileDao {
	
	public void saveLineOfFile(LineOfFile line, Long fileId);
	
	public List<LineOfFile> getAllLinesOfFile(FileStatistic fs);

}
