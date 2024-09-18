package com.qa.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.yaml.snakeyaml.Yaml;

public class YamlReader {
	private static InputStream fis;
	private static Yaml yaml;
	private static Map<String, Object> data;
	public YamlReader() {
		try{
			fis = new FileInputStream(new File("src/test/resources/testdata/"+PropFileHandler.readProperty("tier")+"_TestData.yaml"));
		}catch(FileNotFoundException e) {
			System.out.println("File Does Not Exist!!!");
		}
		yaml= new Yaml();
	}
	
	public static String getKey(String key) {
		String value= null;	
		try {
			data = yaml.load(fis);
			value= data.get(key).toString();
		}catch(Exception e) {
			System.out.println("AN ERROR OCCURED!!!!!!!!!!!!!!");
		}
		return value;
	}
	public static String getKeyFromFile(String FileName,String key) {
		String value= null;	
		InputStream Stream;
		try {
			Stream = new FileInputStream("src/test/resources/testdata/"+FileName+".properties");
			data = yaml.load(Stream);
			value= data.get(key).toString();
		}catch(IOException e) {
			System.out.println("AN ERROR OCCURED!!!!!!!!!!!!!!");
		}
		return value;
	}
	
}
