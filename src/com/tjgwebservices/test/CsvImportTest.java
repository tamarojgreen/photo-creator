package com.tjgwebservices.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tjgwebservices.app.Actions.ReadCSV;
import com.tjgwebservices.app.dataObjects.Image;
import com.tjgwebservices.app.dataObjects.ImportCSV;
import com.tjgwebservices.app.dataObjects.TextObject;
import com.tjgwebservices.app.dataObjects.TextString;

public class CsvImportTest extends BaseTest {
	private ArrayList<TextObject> textObjects;
	private ArrayList<TextString> textStrings;
	private TextObject textObject;
	private TextString textString;

	@Before 
	public void setupTest() {
	}
	
	@Test
	public void testCsvImport() {
		ImportCSV csv = new ImportCSV();
		
		assert(csv.getImages().isEmpty());
//		csv.setTextObjects(textObjects);
//		csv.setTextStrings(textStrings);
		
	}

	@Test
	public void testReadCSV() {
		ReadCSV rCSV = new ReadCSV();
		ArrayList<Image> images = rCSV.readCSVFromPath("C:\\Users\\vgreeta\\Documents\\photos_archive\\Images.csv");
		assertTrue(images.get(0).getImageID() == 1);
	}
	
	@Test
	public void testParseCSV() {
		ImportCSV csv = new ImportCSV();
		Image image = new Image();
		assertTrue(csv.getImages().isEmpty());		
	}
	
	@After
	public void tearDown(){
		textObjects = null;
		textStrings = null;
	}

}
