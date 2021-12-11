package com.testcases.driverscripts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.testng.annotations.Test;


public class CollectTestData {
	//@Test
	public void tests() throws IOException {

		String fileName = "GetAppData.properties"; 
		try { 
			BufferedWriter out = new BufferedWriter( 
					new FileWriter(fileName)); 
			out.write("PunchOutURL=\n"); 
			out.write("Registered_Username="+ System.getProperty("Registered_Username")+"\n"); 
			out.write("Registered_Password="+ System.getProperty("Registered_Password")+"\n"); 
			out.write("Managed_Username="+ System.getProperty("Managed_Username")+"\n"); 
			out.write("Managed_Password="+ System.getProperty("Managed_Password")+"\n"); 
			out.write("BaseURL="+ System.getProperty("BaseURL")+"\n"); 
			out.write("Envtype="+ System.getProperty("Envtype")+"\n"); 
			out.write("Browser="+ System.getProperty("browser")+"\n");
			out.write("SelfService_SU_Username="+ System.getProperty("SelfService_SU_Username")+"\n");
			out.write("SelfService_SU_Password="+ System.getProperty("SelfService_SU_Password")+"\n");
			out.write("SelfService_MU_Username="+ System.getProperty("SelfService_MU_Username")+"\n");
			out.write("SelfService_MU_Password="+ System.getProperty("SelfService_MU_Password")+"\n");
			out.write("Concept="+ System.getProperty("Concept")+"\n");
			out.close(); 
		} 
		catch (IOException e) { 
			System.out.println("Exception Occurred" + e); 
		} 

	}

}


