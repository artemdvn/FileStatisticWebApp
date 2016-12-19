package com.luxoft.filestatistic;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.luxoft.filestatistic.model.LineOfFile;
import com.luxoft.filestatistic.services.FileHandler;

public class TestFileStatistic {
	
	FileHandler app = new FileHandler();
	String filename = "testFile.txt";
	
	@Before
	public void setUp() {
		try{
		    PrintWriter writer = new PrintWriter(filename, "UTF-8");
		    writer.println("The first line of the file");
		    writer.println("The second line");
		    writer.close();
		} catch (Exception e) {
			System.out.print("Error writing to file");
		}
	}
	
	@Test
	public void testGetLongestWordOfTheFile() {
		app.handleFile(filename);
		assertEquals("second", app.getFileStatistic().getLongestWordOfFile());		
	}
	
	@Test
	public void testGetShortestWordOfTheFile() {
		app.handleFile(filename);
		assertEquals("of", app.getFileStatistic().getShortestWordOfFile());		
	}
	
	@Test
	public void testGetLongestWordOfTheFirstLine() {
		app.handleFile(filename);
		LineOfFile line = app.getLinesOfFile().get(0);
		assertEquals("first", line.getLongestWord());		
	}
	
	@Test
	public void testGetShortestWordOfTheFirstLine() {
		app.handleFile(filename);
		LineOfFile line = app.getLinesOfFile().get(0);
		assertEquals("of", line.getShortestWord());		
	}
	
	@Test
	public void testGetLongestWordOfTheSecondLine() {
		app.handleFile(filename);
		LineOfFile line = app.getLinesOfFile().get(1);
		assertEquals("second", line.getLongestWord());		
	}
	
	@Test
	public void testGetShortestWordOfTheSecondLine() {
		app.handleFile(filename);
		LineOfFile line = app.getLinesOfFile().get(1);
		assertEquals("The", line.getShortestWord());		
	}
	
	@After
	public void tearDown() {
		try{
    		File file = new File(filename);
    		file.delete();
    	}catch(Exception e){
    		System.out.print("Error deleting file");
    	}
	}	

}
