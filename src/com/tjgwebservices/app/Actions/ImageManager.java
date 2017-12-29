package com.tjgwebservices.app.Actions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.tjgwebservices.app.dataObjects.Image;
import com.tjgwebservices.app.dataObjects.ImportCSV;
import com.tjgwebservices.app.dataObjects.TextObject;
import com.tjgwebservices.app.dataObjects.TextString;

public class ImageManager extends ActionsBase {
	
	private BufferedImage inputImage;
	private URL url;
	private Graphics graphics;
	private ImportCSV importCSV;
	
	public ImageManager(){
		
	}
	
	public File processImage(String imagePath,TextObject textObject) {
		Image image;
		try {
			setUrl(new URL(imagePath));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			setUrl(null);
			e1.printStackTrace();
		}
		try {
			
			setInputImage(ImageIO.read(getUrl()));
		} catch (IOException e) {
			System.out.println("Unable to readImage"+getUrl());
			e.printStackTrace();
			return null;
		}		
		
		setGraphics(getInputImage().getGraphics());
		getGraphics().setFont(getGraphics().getFont());
		
		ArrayList<TextString> texts = importCSV.getTexts();
		String imageText = null;
		
		int textsSize = texts.size(); 
		for (int i=0;i<textsSize; i++){
			if (textObject.getTextID() == i) {
				imageText = texts.get(i).getText();
			}
			if(imageText.isEmpty() || imageText.equals(null)) {
				try {
					throw new Exception("Text for id: " + textObject.getTextID());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		String pos =  textObject.getPosition();
		String posXY[] = pos.split(",");
		int posX = Integer.valueOf(posXY[0]);
		int posY = Integer.valueOf(posXY[1]);		
		
		getGraphics().drawString(imageText, posX, posY);
		File targetFile = new File(".\\target\\ImageManagerTest.png");
		
		try {
			ImageIO.write(getInputImage(), "png", targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return targetFile;
		
	}

	
	public BufferedImage processWatermark(BufferedImage img, String origImage, String destImage){
		Graphics2D g2 = img.createGraphics();
		
		try {
		    // retrieve image
		    File outputfile = new File(destImage);
		    ImageIO.write(img, "png", outputfile);
			return ImageIO.read(new URL(destImage));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}				
		
	}
	
	public BufferedImage processBufferedImageWatermark( String origImage, String destImage){
		try {
		    // retrieve image
		    BufferedImage bi = this.getInputImage();
		    File outputfile = new File("saved.png");
		    ImageIO.write(bi, "png", outputfile);
		    return ImageIO.read(new URL(destImage));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public File processTextOnImage(BufferedImage img, String text){
		Graphics2D g2 = img.createGraphics();
		//g2.setColor(Color.black);
//		g2.setFont(Font.SERIF);
		g2.setColor(Color.BLUE);
//		g2.setFont(Font.SERIF);
		g2.drawString(text, 80,1440);
		 
		File outputfile = new File("imagewithtext.png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return outputfile;
	}
/*	
	public BufferedImage waterMarkImage(BufferedImage img, BufferedImage watermarkedImage, String origImage, String destImage) {
		FileFilter filter = new FileFilter();
		Watermark wm = new Watermark();
		
//		Watermark filter = new Watermark(Position.CENTER, watermarkedImage, 0.5f);
		BufferedImage newImage = ((Object) filter).apply(img);
		return newImage;
	}
	*/
	public BufferedImage getInputImage() {
		return inputImage;
	}

	public void setInputImage(BufferedImage inputImage) {
		this.inputImage = inputImage;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public Graphics getGraphics() {
		return graphics;
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}
	
	public ImportCSV getImportCSV() {
		return importCSV;
	}

	public void setImportCSV(ImportCSV importCSV) {
		this.importCSV = importCSV;
	}

	
}
