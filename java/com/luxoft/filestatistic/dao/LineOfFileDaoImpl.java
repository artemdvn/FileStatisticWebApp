package com.luxoft.filestatistic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.luxoft.filestatistic.model.FileStatistic;
import com.luxoft.filestatistic.model.LineOfFile;

public class LineOfFileDaoImpl implements LineOfFileDao{
	
	private static LineOfFileDaoImpl instance = null;
	private Logger logger = Logger.getLogger(LineOfFileDaoImpl.class);
	
	private LineOfFileDaoImpl() {
	}

	public static LineOfFileDaoImpl getInstance() {
		if (instance == null) {
			instance = new LineOfFileDaoImpl();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
        DataSource dataSource = DBCPDataSourceFactory.getDataSource();
        return dataSource.getConnection();
    }
	
	public void saveLineOfFile(LineOfFile line, Long fileId){
		Connection con = null;
		PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	con = getConnection();

            preparedStatement = con.prepareStatement(
            		"INSERT INTO filelines(line, lineNumber, fileId, "
            		+ "shortestWord, longestWord, averageWordLenght) "
            		+ "VALUES(?,?,?,?,?,?)"
            		);
            preparedStatement.setString(1, line.getLine());
            preparedStatement.setInt(2, line.getLineNumberInFile());
            preparedStatement.setLong(3, fileId);
            preparedStatement.setString(4, line.getShortestWord());
            preparedStatement.setString(5, line.getLongestWord());
            preparedStatement.setDouble(6, line.getAverageWordLenght());
            preparedStatement.executeUpdate();
            
            logger.info("Line # " + line.getLineNumberInFile() + " of file " + line.getFs().getFilename() + " saved to DB");

        } catch (Exception e) {
        	logger.error("Error saving line of file");
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
	}
	
	public List<LineOfFile> getAllLinesOfFile(FileStatistic fs){
		List<LineOfFile> lines = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
        	
        	con = getConnection();
        	preparedStatement = con.prepareStatement(
        			"SELECT * FROM filelines WHERE fileId = ?");
        	preparedStatement.setLong(1, fs.getId());
        	
        	resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            	LineOfFile line = getLineOfFileFromResultSet(resultSet, fs);
            	lines.add(line);
            }
            
        } catch (Exception e) {
        	logger.error("Error getting line of file");
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
        
        return lines;
	}
	
	private LineOfFile getLineOfFileFromResultSet(ResultSet rs, 
			FileStatistic fs) throws SQLException {
        String line = rs.getString("line");
        int lineNumber = rs.getInt("lineNumber");
        String shortestWord = rs.getString("shortestWord");
        String longestWord = rs.getString("longestWord");
        double averageWordLenght = rs.getDouble("averageWordLenght");
        
        LineOfFile lineOfFile = new LineOfFile(line, lineNumber, fs);
        lineOfFile.setShortestWord(shortestWord);;
        lineOfFile.setLongestWord(longestWord);;
        lineOfFile.setAverageWordLenght(averageWordLenght);
        return lineOfFile;
    }

}
