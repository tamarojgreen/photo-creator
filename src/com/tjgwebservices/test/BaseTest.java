package com.tjgwebservices.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

import com.tjgwebservices.app.Configurations;

public class BaseTest {
	private Configurations testConfiguration;
	private Logger logger;
	private String dataPath;
	private String targetPath;
	
	public BaseTest(){
//		logger.log(Level.CONFIG, "Starting Test");
		
		init();
		
//		logger.log(Level.CONFIG, "Initialized Test");
	}
	
	protected boolean init(){
		
		
		Properties properties = new Properties();
		File file = new File("bpg.properties");
		FileInputStream is;
		try {
			is = new FileInputStream(file);
			try {
				properties.load(is);
			} catch (IOException e1) {
				logger.log(Level.SEVERE, "Properties file cannot be loaded.");
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e2) {
			logger.log(Level.SEVERE, "Properties file could not be parsed.");
			e2.printStackTrace();
		}
		
		String os = properties.getProperty("os.configuration");
		System.out.println("os");
		System.out.println(os);
		XPath xpath = XPathFactory.newInstance().newXPath();
		InputSource inputSource = new InputSource("configuration.xml");
		
		try {
			System.out.println("dataPath");
			System.out.println(xpath.evaluate("//configurations//configuration[@id='"+os+"']//dataPath", inputSource));
			System.out.println(xpath.evaluate("//configurations//configuration[@id='"+os+"']//targetPath", inputSource));
			setDataPath(xpath.evaluate("//configurations//configuration[@id='"+os+"']//dataPath", inputSource));
			setTargetPath(xpath.evaluate("//configurations//configuration[@id='"+os+"']//targetPath", inputSource));
			setTestConfiguration(new Configurations(os));
			System.out.println(getTestConfiguration().getDataPath());
			System.out.println(getTestConfiguration().getTargetPath());
		} catch (XPathExpressionException e) {
			logger.log(Level.SEVERE, "Configuration xpath could not be parsed.");
			e.printStackTrace();
			return false;
		}

		
		return true;
		
	}

	public Configurations getTestConfiguration() {
		return testConfiguration;
	}

	public void setTestConfiguration(Configurations testConfiguration) {
		this.testConfiguration = testConfiguration;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	
}
