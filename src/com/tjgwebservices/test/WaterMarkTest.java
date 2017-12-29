package com.tjgwebservices.test;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.tjgwebservices.app.Actions.WaterMarkCreator;

public class WaterMarkTest extends BaseTest{

	final private String imageDirectory = "";
			
	@Before 
	public void setupTest() {
		
	}
	
	@Test
	public void waterMarkTest() {
		File sourceImageFile = new File(imageDirectory+"photo1.jpg");
		File destImageFile = new File(".\\target\\text_watermarked.png");
		

		WaterMarkCreator.addTextWatermark("Text Watermark", sourceImageFile, destImageFile);

		destImageFile = new File(".\\target\\image_watermarked.png");
		File watermarkImageFile = new File(imageDirectory+"logo1.png");
		
		WaterMarkCreator.addImageWatermark(watermarkImageFile, sourceImageFile, destImageFile);
	}

}
