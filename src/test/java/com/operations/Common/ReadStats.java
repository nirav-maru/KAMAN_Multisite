package com.operations.Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadStats {

	public String Envtype;
	public String browser;
	public String Concept;
	
	//public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		public void getRepositoryValues() throws IOException{
			//Read object repository file
					
			File configFile = new File(System.getProperty("user.dir")+"/GetAppData.properties");
			InputStream inputStream = new FileInputStream(configFile);
			Properties prop1 = new Properties();
			prop1.load(inputStream);
			Envtype = prop1.getProperty("Envtype");	
			browser = prop1.getProperty("Browser");	
			Concept= prop1.getProperty("Concept");
		//	System.out.println(Envtype);
			
			}
	}


