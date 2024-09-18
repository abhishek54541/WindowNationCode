package com.qa.automation.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class StoreDataInFile {

	public static void storeData() {
		
		String fileName = "Request1.txt";
		File file_obj= new File("src/test/resources/testdata/"+fileName);
		
		try {
			FileWriter fw = new FileWriter(file_obj , true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.close();
		}
		
		catch(Exception e) {
			System.out.println("Error while creating file");
		}
	}
}
