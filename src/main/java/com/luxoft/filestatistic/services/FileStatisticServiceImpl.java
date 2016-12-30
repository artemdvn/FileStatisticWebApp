package com.luxoft.filestatistic.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luxoft.filestatistic.dao.FileStatisticDao;
import com.luxoft.filestatistic.dao.LineOfFileDao;
import com.luxoft.filestatistic.model.FileStatistic;
import com.luxoft.filestatistic.model.LineOfFile;

@Service
public class FileStatisticServiceImpl implements FileStatisticService {
	
	@Autowired
	private FileStatisticDao fileStatisticDao;
	
	@Autowired
	private LineOfFileDao lineOfFileDao;
	
	@Override
	public List<FileStatistic> getAllFileStatistic(){
		return fileStatisticDao.getAllFileStatistic();		
	}
	
	@Override
	public FileStatistic getFileStatistic(Long id){
		return fileStatisticDao.get(id);    	
    }
	
	@Override
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
	
	@Override
	public List<LineOfFile> getAllLinesOfFile(FileStatistic fs){
		return lineOfFileDao.getAllLinesOfFile(fs);
	}
	
}
