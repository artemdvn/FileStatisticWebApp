package com.luxoft.filestatistic.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luxoft.filestatistic.model.FileStatistic;
import com.luxoft.filestatistic.model.LineOfFile;

@Repository
public class LineOfFileDaoImpl implements LineOfFileDao{
	
	@Autowired
    private SessionFactory sf;
	
	private static LineOfFileDaoImpl instance = null;
	
	private LineOfFileDaoImpl() {
	}

	public static LineOfFileDaoImpl getInstance() {
		if (instance == null) {
			instance = new LineOfFileDaoImpl();
		}
		return instance;
	}
	
	@Override
	public void saveLineOfFile(LineOfFile line){
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(line);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public List<LineOfFile> getAllLinesOfFile(FileStatistic fs){
		Session session = sf.openSession();
		Query query = session.createQuery("from LineOfFile where fileId = :id");
		query.setParameter("id", fs.getId());
		List<LineOfFile> lines = query.getResultList();
		session.close();
		return lines;		
	}

}
