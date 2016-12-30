package com.luxoft.filestatistic.dao;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sf = configureSF();
    
    private static SessionFactory configureSF() {
    	Logger logger = Logger.getLogger(HibernateUtil.class);
    	try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure("db/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			logger.error("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
    }

    public static SessionFactory getSessionFactory() {
        return sf;
    }
}