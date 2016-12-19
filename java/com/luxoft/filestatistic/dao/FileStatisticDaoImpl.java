package com.luxoft.filestatistic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.luxoft.filestatistic.model.FileStatistic;

public class FileStatisticDaoImpl implements FileStatisticDao{
	
	private static FileStatisticDaoImpl instance = null;
	private Logger logger = Logger.getLogger(FileStatisticDaoImpl.class);
	
	private FileStatisticDaoImpl() {
	}

	public static FileStatisticDaoImpl getInstance() {
		if (instance == null) {
			instance = new FileStatisticDaoImpl();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
        DataSource dataSource = DBCPDataSourceFactory.getDataSource();
        return dataSource.getConnection();
    }
	
	public Long saveFileStatistic(FileStatistic fs){
		Connection con = null;
		PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long idInDb = -1L;
        try {
        	
        	con = getConnection();

            preparedStatement = con.prepareStatement(
            		"INSERT INTO files(filename, shortestWord, "
            		+ "longestWord, averageWordLenght) "
            		+ "VALUES(?,?,?,?)",
            		Statement.RETURN_GENERATED_KEYS
            		);
            preparedStatement.setString(1, fs.getFilename());
            preparedStatement.setString(2, fs.getShortestWordOfFile());
            preparedStatement.setString(3, fs.getLongestWordOfFile());
            preparedStatement.setDouble(4, fs.getAverageWordLenghtOfFile());
            preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				idInDb = generatedKeys.getLong(1);
			} else {
				throw new SQLException();
			}
            
            logger.info("File statistic " + fs.getFilename() + " saved to DB");

        } catch (Exception e) {
        	logger.error("Error saving file statistic");
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (con != null) {
                	con.close();
                }
            } catch (Exception e1) {
            	logger.error("Error closing database resources");
                e1.printStackTrace();
            }
        }
        return idInDb;
	}
	
	public List<FileStatistic> getAllFileStatistic(){
		List<FileStatistic> fileStatistics = new ArrayList<>();
		
		Connection con = null;
		Statement statement = null;
        ResultSet resultSet = null;
        try {
        	
        	con = getConnection();
            statement = con.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM files");
            while (resultSet.next()) {
            	FileStatistic fs = getFileStatisticFromResultSet(resultSet);
                fileStatistics.add(fs);
            }
            
        } catch (Exception e) {
        	logger.error("Error getting file statistic");
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (con != null) {
                	con.close();
                }
            } catch (Exception e1) {
            	logger.error("Error closing database resources");
                e1.printStackTrace();
            }
        }
        
        return fileStatistics;
	}
	
	private FileStatistic getFileStatisticFromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String filename = rs.getString("filename");
        String shortestWord = rs.getString("shortestWord");
        String longestWord = rs.getString("longestWord");
        double averageWordLenght = rs.getDouble("averageWordLenght");
        
        FileStatistic fs = new FileStatistic(filename);
        fs.setId(id);
        fs.setShortestWordOfFile(shortestWord);;
        fs.setLongestWordOfFile(longestWord);;
        fs.setAverageWordLenghtOfFile(averageWordLenght);
        return fs;
    }

}
