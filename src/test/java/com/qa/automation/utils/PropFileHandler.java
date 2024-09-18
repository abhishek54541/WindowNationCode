package com.qa.automation.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PropFileHandler {
	public static Properties config = new Properties();

	public static String readProperty(String property) {
		String value;
		try {
			FileInputStream in = new FileInputStream("src/test/resources/testdata/Config.properties");
			try {
				config.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		value = config.getProperty(property);
		return value;
	}

	public static String readAPIJsonFile(String groupName, String endPointName) {
		JSONParser parser = new JSONParser();
		String APIEndPoint = null;
		try {
			String filePath = System.getProperty("user.dir");
			String DataFilepath = filePath + "/src/test/resources/testdata/API_endpoint.json";
			Object obj = parser.parse(new FileReader(DataFilepath));
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject endPointGroup = (JSONObject) jsonObject.get(groupName);
			APIEndPoint = (String) endPointGroup.get(endPointName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return APIEndPoint;
	}

	public static String readAPIJsonFile(String groupName, String endPointName, String param1) {
		return readAPIJsonFile(groupName, endPointName) + "/" + param1;
	}

	public static String readAPIJsonFile(String groupName, String endPointName, String param1, String param2) {
		return readAPIJsonFile(groupName, endPointName, param1) + "/" + param2;
	}

	public static String readPropertyFromFile(String FileName,String property) {
		String value;
		try {
			FileInputStream in = new FileInputStream("src/test/resources/testdata/"+FileName+".properties");
			try {
				config.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		value = config.getProperty(property);
		return value;
	}
	
	public static void writeDataToFile(String fileName, Map<String, String> data) {
		FileOutputStream output = null;
		Properties prop = new Properties();
		System.out.println("Writing Data to File: "+data);
		for (Map.Entry<String, String> entry : data.entrySet()) {
			prop.setProperty(entry.getKey(), entry.getValue());
		}
		try {
			output = new FileOutputStream("src/test/resources/testdata/" + fileName + ".properties");
			prop.store(output, null);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
