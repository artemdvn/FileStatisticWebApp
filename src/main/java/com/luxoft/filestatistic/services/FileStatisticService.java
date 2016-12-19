package com.luxoft.filestatistic.services;

import java.util.List;

import com.luxoft.filestatistic.model.FileStatistic;
import com.luxoft.filestatistic.model.LineOfFile;

public interface FileStatisticService {
	
	List<FileStatistic> getAllFileStatistic();
	FileStatistic getFileStatistic(Long id);
    void saveFileStatistic(String filename);
    List<LineOfFile> getAllLinesOfFile(FileStatistic fs);

}
