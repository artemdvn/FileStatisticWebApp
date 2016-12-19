package com.luxoft.filestatistic.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBCPDataSourceFactory {
	
	public static DataSource getDataSource() {
		
		Properties props = new Properties();
		InputStream inputStream = null;
		BasicDataSource ds = new BasicDataSource();
		
		try {
			inputStream = new DBCPDataSourceFactory().getClass().getClassLoader()
					.getResourceAsStream("db/jdbc.properties");
			props.load(inputStream);
			
			ds.setDriverClassName(props.getProperty("jdbc.driverClassName"));
			ds.setUrl(props.getProperty("jdbc.databaseurl"));
			ds.setUsername(props.getProperty("jdbc.username"));
			ds.setPassword(props.getProperty("jdbc.password"));
			ds.setMaxIdle(30);
			ds.setInitialSize(3);
						
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return ds;
	}

}
