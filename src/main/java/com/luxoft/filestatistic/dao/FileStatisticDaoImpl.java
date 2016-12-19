package com.luxoft.filestatistic.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luxoft.filestatistic.model.FileStatistic;

@Repository
public class FileStatisticDaoImpl implements FileStatisticDao{
	
	@Autowired
    private SessionFactory sf;
	
	private static FileStatisticDaoImpl instance = null;
	
	private FileStatisticDaoImpl() {
	}

	public static FileStatisticDaoImpl getInstance() {
		if (instance == null) {
			instance = new FileStatisticDaoImpl();
		}
		return instance;
	}
	
	public void saveFileStatistic(FileStatistic fs){
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(fs);
		session.getTransaction().commit();
		session.close();		
    }
	
	public List<FileStatistic> getAllFileStatistic(){
		Session session = sf.openSession();
		List<FileStatistic> fileStatistics = session.createQuery("from FileStatistic").getResultList();
		session.close();
		return fileStatistics;		
	}
	
	public FileStatistic get(Long id){
		Session session = sf.openSession();
		FileStatistic fs = session.get(FileStatistic.class, id);
		session.close();
		return fs;		
	}

}
