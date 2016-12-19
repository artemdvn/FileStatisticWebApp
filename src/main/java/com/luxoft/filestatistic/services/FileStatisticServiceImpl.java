package com.luxoft.filestatistic.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luxoft.filestatistic.dao.FileStatisticDao;
import com.luxoft.filestatistic.dao.LineOfFileDao;
import com.luxoft.filestatistic.model.FileStatistic;
import com.luxoft.filestatistic.model.LineOfFile;

@Service
public class FileStatisticServiceImpl implements FileStatisticService {
	
	private Logger logger = Logger.getLogger(FileStatisticServiceImpl.class);
	
	@Autowired
	private FileStatisticDao fileStatisticDao;
	
	@Autowired
	private LineOfFileDao lineOfFileDao;
	
	public List<FileStatistic> getAllFileStatistic(){
		return fileStatisticDao.getAllFileStatistic();		
	}
	
	public FileStatistic getFileStatistic(Long id){
		return fileStatisticDao.get(id);    	
    }
	
	@Transactional
	public void saveFileStatistic(String filename) {
		FileHandler fileHandler = new FileHandler();
		fileHandler.handleFile(filename);
		fileStatisticDao.saveFileStatistic(fileHandler.getFileStatistic());
		
		List<LineOfFile> lines = fileHandler.getLinesOfFile();
		for (LineOfFile line : lines) {
			line.getId().setFileId(fileHandler.getFileStatistic().getId());
			lineOfFileDao.saveLineOfFile(line);
		}
	}
	
	public List<LineOfFile> getAllLinesOfFile(FileStatistic fs){
		return lineOfFileDao.getAllLinesOfFile(fs);
	}
	
}
