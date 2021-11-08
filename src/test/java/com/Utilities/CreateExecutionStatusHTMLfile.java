package com.Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.operations.Common.FireClass;
import com.operations.Common.ReadStats;
import com.operations.Common.ReadUserconfig;
import com.testcases.driverscripts.Execute_FailedScript;
import com.testcases.driverscripts.Execute_MainScript;

public class CreateExecutionStatusHTMLfile {

	//public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub
	ReadStats rs = new ReadStats();

	public void GenerateFinalExecutionStatus() throws IOException {
		rs.getRepositoryValues();
		Date date;
		SimpleDateFormat Time;
		Date Sdate=Execute_MainScript.Startdate;
		Date edate=Execute_FailedScript.Enddate;
		int passcount = Execute_MainScript.Passcounter;
		int skipcount = Execute_MainScript.skipcounter;
		int failcount = Execute_FailedScript.failcounter;
		int TotalTC = Execute_MainScript.TotalTCcounter;
		String browser = Execute_MainScript.browser;
		String ExecutionStatus = null;
		FileWriter writer = new FileWriter("./test-output/MailStatus.html", false);
		String StatusColor=null;
		String mailBOdy = null;
		String FinalMailBody = null;



		int SendPassStatus = PassTCpercent(passcount,skipcount,TotalTC);
		int SendFailStatus = FailTCpercent(failcount,TotalTC);
		
		
		if(SendPassStatus == 100) {

			ExecutionStatus = "PASSED";
		}

		else if (SendPassStatus >= 80){

			ExecutionStatus = "CONDITIONALLY PASSED";
		}

		else if (SendPassStatus < 80) {

			ExecutionStatus = "FAILED";
		}


		if (ExecutionStatus=="PASSED") {

			StatusColor="#228B22";
		}

		else if (ExecutionStatus=="FAILED") {

			StatusColor="#ff0000";
		}

		else if (ExecutionStatus=="CONDITIONALLY PASSED") {

			StatusColor="#FFA500";
		}

		String tmpmailBOdy="Test execution status as below :"+"<br />"+"<br />"
				+"<p style='color:"+StatusColor+";font-size:20px'><font color='Black'; size=2px><b>Test Execution Status: </font>"+ExecutionStatus+"</b></p>"
				+ "<b>Test browser : </b>"+browser+"<br />"
				+ "<b>Test execution Start Time : </b>"+Sdate+"<br />"
				+ "<b>Test execution End Time : </b>"+edate+"<br />"+"<br />"
				+ "<table width='60%' border='1' align='Left'>"
				+ "<tr align='center'>"
				+ "<th bgcolor='#D3D3D3'><b>Total number of testcases<b></th>"
				+ "<th bgcolor='#00FF00'><b>Passed Testcases<b></th>"
				+ "<th bgcolor='#FF0000'><b>Failed testcases<b></th>"
				+ "<th bgcolor='#FFA500'><b>Skipped testcases<b></th>"
				+ "</tr>"
				+ "<tbody>"
				+ "<tr align='center'>"
				+ "<td>"+TotalTC+"</td>"
				+ "<td>"+passcount+"</td>"
				+ "<td>"+failcount+"</td>"
				+ "<td>"+skipcount+"</td></tr></tbody></table>"
				+ "<br />"+"<br />"+"<br />"+"<br />"+"<br />"
				+ "<p>Kindly find attached HTML report for more information about Test execution summary.</p>"+"<br />";
		/*if (rs.Envtype.equalsIgnoreCase("ECTEST")) {
			mailBOdy = tmpmailBOdy.concat("<b>NOTE: From this Sanity run , we have disabled 2 testcases on ECTEST which are related to Contact Us Page (https://ectest.kamandirect.com/storeus/Kaman-locations) due to functional issues.</b>");
			System.out.println(mailBOdy);
		}

		if (mailBOdy==null) {

			tmpmailBOdy=new String(FinalMailBody);
		}

		else {

			FinalMailBody=new String(mailBOdy);
		}*/
		FinalMailBody=new String(tmpmailBOdy);
		writer.write(FinalMailBody);
		writer.close();
		System.out.println("Success...");
	}

	public static int PassTCpercent(int passTCcount , int SkipTCcount , int TotalTCcount) {
		float Passpercentage; 
		float fpassTCcount = passTCcount;
		float fTotalTCcount = TotalTCcount - SkipTCcount;

		Passpercentage = (float)(fpassTCcount/fTotalTCcount*100);
		System.out.println(Passpercentage);

		Math.round(Passpercentage);

		//System.out.println(Math.round(Passpercentage));

		return Math.round(Passpercentage);
	}

	public static  int FailTCpercent(int FailTCcount , int TotalTCcount) {
		float Failpercentage;
		float fFailTCcount = FailTCcount;
		float fTotalTCcount = TotalTCcount;

		Failpercentage = (float)(fFailTCcount/fTotalTCcount*100);

		Math.round(Failpercentage);

		//System.out.println(Math.round(Failpercentage));

		return Math.round(Failpercentage);
	}

}

