package com.tjgwebservices.app;

import java.io.Reader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

public class Configurations {

	private String dataPath;
	private String photoArchive;
	private String targetPath;
	public Configurations(){}
	
	public Configurations(String os){
		XPath xpath = XPathFactory.newInstance().newXPath();
	    InputSource inputSource = new InputSource("configuration.xml");
        Reader reader = inputSource.getCharacterStream();
		    
	    try {
		    setDataPath(xpath.evaluate("//configurations//configuration[@id='"+os+"']//dataPath", inputSource));
		    setPhotoArchive(xpath.evaluate("//configurations//configuration[@id='"+os+"']//photoArchive", inputSource));
		    setTargetPath(xpath.evaluate("//configurations//configuration[@id='Windows7']//targetPath", inputSource));
		} catch (XPathExpressionException e1) {
			e1.printStackTrace();
		}
		    
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getPhotoArchive() {
		return photoArchive;
	}

	public void setPhotoArchive(String photoArchive) {
		this.photoArchive = photoArchive;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}
	
}
