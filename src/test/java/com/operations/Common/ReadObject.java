package com.operations.Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.testcases.driverscripts.CollectTestData;

public class ReadObject {

	Properties p1 = new Properties();
	ReadStats rs = new ReadStats();
	
	public Properties getObjectRepository() throws IOException{
		//Read object repository file
		rs.getRepositoryValues();
		String str = System.getProperty("user.dir")+"\\resources\\CSSLocators_"+rs.Envtype+"_"+rs.Concept+".properties";
		InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\resources\\CSSLocators_"+rs.Envtype+"_"+rs.Concept+".properties"));
		//load all objects
		p1.clear();
		p1.load(stream);
		return p1;
	}

}
