package com.luxoft.filestatistic.dao;

import java.util.List;

import com.luxoft.filestatistic.model.FileStatistic;

public interface FileStatisticDao {
	
	public Long saveFileStatistic(FileStatistic fs);
	
	public List<FileStatistic> getAllFileStatistic();

}
