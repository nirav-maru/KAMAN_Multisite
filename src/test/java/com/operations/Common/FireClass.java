package com.operations.Common;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class FireClass {

	ReadUserconfig uc =new ReadUserconfig();
	ReadStats rs = new ReadStats();
	public static String failmsg;
	public static String exception;
	int Frow = 0;
	int Fcoln = 1;
	//public static int failcounter;
	Map<Object, Object[]> listOfFailedTCsMap  = new LinkedHashMap<Object, Object[]>();
	//public static String FailedsheetPath;



	public void VerifyUserLoginforLogout(WebDriver webdriver) throws InterruptedException {

		String User = "//*[@class='logout-link']";
		String LogOut = "//*[@id='logout-button']";

		int CheckUserLogin = webdriver.findElements(By.xpath(User)).size();

		if(CheckUserLogin > 0){

			WebElement ele = webdriver.findElement(By.xpath("//span[@class='capitalize']"));
			//ele.click();
			Actions Mouseaction = new Actions(webdriver);
			//WebElement Mh = webdriver.findElement(ele);
			Mouseaction.moveToElement(ele).build().perform();
			Thread.sleep(2000);
			webdriver.findElement(By.xpath(LogOut)).click();
			Thread.sleep(2000);
		}

	}

	public void FailedTC_SeleniumExcpetions(String Exception,String Object,Script_executor screxe,WebDriver webdriver,Xls_writer xls_writer,Map<Integer, Object[]> Testscase_failresults ,String browser_name, String Section,String Functionality,String Testcasenumber, 
			String Testcase_description, String Executionmode,String Severity,SimpleDateFormat StartTime,Date Startdate,SoftAssert softAssert,ExtentTest test,ExtentReports extent,String ExecutionRound) throws Exception {

		uc.getUserConfig();

		Object=screxe.Object;
		//VerifyUserLoginforLogout(webdriver);
		//FailedsheetPath=System.getProperty("user.dir")+Constants.Windows_FailedFileLocation+StartTime.format(Startdate)+"/"+uc.SiteName+"/"+browser_name+"/"+ExecutionRound;

		if(uc.ExcelReports.equalsIgnoreCase("Yes")) {

			//xls_writer.GenerateFailReport(Testscase_failresults, Section, Functionality, Testcasenumber,Testcase_description,Executionmode, Severity,FailedsheetPath,ExecutionRound);

		}

		//xls_writer.GenerateFailReport(Testscase_failresults, uc.SiteName, browser_name, Functionality, Testcasenumber, Severity,"./Failed_Reports/"+StartTime.format(Startdate)+"/"+uc.SiteName+"/"+browser_name+"/");



		softAssert.assertAll();

		//failmsg="NOT able to find element within given time frame...!!! Element name: " +"'" + Object + "." ;
		failmsg=Exception;
		//test = extent.createTest(browser_name+"_"+Testcasenumber);	
		//test.fail(MarkupHelper.createLabel(failmsg,ExtentColor.RED));
		//failcounter=failcounter+1;

		/*Actions Mouseaction = new Actions(webdriver);
		WebElement ele = webdriver.findElement(By.xpath("//div[text()='My Account']"));
		Mouseaction.moveToElement(ele).build().perform();
		webdriver.findElement(By.xpath("//*[@id='logout-button']")).click();
		//test.fail(MarkupHelper.createLabel(Testcasenumber+" has been failed....", ExtentColor.RED));
		 */

		Assert.fail(failmsg);

	}

	public void FailedTC_Excpetions(String Exception,String Object,Script_executor screxe,WebDriver webdriver,Xls_writer xls_writer,Map<Integer, Object[]> Testscase_failresults ,String browser_name, String Section,String Functionality,String Testcasenumber, 
			String Testcase_description, String Executionmode,String Severity,SimpleDateFormat StartTime,Date Startdate,SoftAssert softAssert,ExtentTest test,ExtentReports extent,String ExecutionRound) throws Exception {

		uc.getUserConfig();

		exception=Exception;

		Object=screxe.Object;
		//VerifyUserLoginforLogout(webdriver);

		//FailedsheetPath=System.getProperty("user.dir")+Constants.Windows_FailedFileLocation+StartTime.format(Startdate)+"/"+uc.SiteName+"/"+browser_name+"/"+ExecutionRound;
		if(uc.ExcelReports.equalsIgnoreCase("Yes")) {

			///xls_writer.GenerateFailReport(Testscase_failresults, Section, Functionality, Testcasenumber,Testcase_description,Executionmode, Severity,FailedsheetPath,ExecutionRound);

		}

		//xls_writer.GenerateFailReport(Testscase_failresults, uc.SiteName, browser_name, Functionality, Testcasenumber, Severity,"./Failed_Reports/"+StartTime.format(Startdate)+"/"+uc.SiteName+"/"+browser_name+"/");



		softAssert.assertAll();
		//test = extent.createTest(browser_name+"_"+Testcasenumber);	
		//test.fail(MarkupHelper.createLabel("",ExtentColor.RED));
		//failcounter=failcounter+1;

		/*Actions Mouseaction = new Actions(webdriver);
		WebElement ele = webdriver.findElement(By.xpath("//div[text()='My Account']"));
		Mouseaction.moveToElement(ele).build().perform();
		webdriver.findElement(By.xpath("//*[@id='logout-button']")).click();
		//test.fail(MarkupHelper.createLabel(Testcasenumber+" has been failed....", ExtentColor.RED));
		 */

		Assert.fail(exception);

	}



	public void ExecuteTestcasesWindows(String Testcasenumber,Script_executor scre,String Sitename,String browser_name,SimpleDateFormat StartTime,Date Startdate,WebDriver webdriver,String Functionality,String Section,
			String Testcase_description,String Executionmode, String Severity ,ExtentReports extent,Logger Applog,String ExecutionRound,String env,String Concept ) throws Throwable {

		uc.getUserConfig();
		rs.getRepositoryValues();
		System.out.println("Currently running Testcase : " + Testcasenumber);
		scre.Execute_script(Sitename,browser_name,Constants.Windows_InputFileLocation+uc.SiteName+"/"+rs.Envtype+"/",Constants.Windows_OutputFileLocation+StartTime.format(Startdate)+"/"+Sitename+"/"+browser_name+"/",
				Constants.Windows_ScreenshotsLocation+StartTime.format(Startdate)+"/"+uc.SiteName+"/"+browser_name+"/"+rs.Envtype +"/"+ExecutionRound+"/", webdriver,Section,Functionality, Testcasenumber, Testcase_description, Executionmode, Severity,uc.Scr,uc.ExcelReports,extent,Applog,env,Concept);


	}

	/*public void ExecuteTestcasesLinux(String Testcasenumber,Script_executor scre,String Sitename,String browser_name,SimpleDateFormat StartTime,Date Startdate,WebDriver webdriver,String Functionality,String Section,
			String Testcase_description,String Executionmode, String Severity ,ExtentReports extent,Logger Applog ) throws Throwable {

		uc.getUserConfig();
		System.out.println("Currently running Testcase : " + Testcasenumber);
		scre.Execute_script(Sitename,browser_name,Constants.Linux_InputFileLocation+uc.SiteName+"/",Constants.Linux_OutputFileLocation+StartTime.format(Startdate)+"/"+Sitename+"/"+browser_name+"/",
				Constants.Linux_ScreenshotsLocation+StartTime.format(Startdate)+"/"+uc.SiteName+"/"+browser_name+"/", webdriver,Section,Functionality, Testcasenumber, Testcase_description, Executionmode, Severity,uc.Scr,uc.ExcelReports,extent,Applog);


	}
*/
	public void CreateFailTCList(String Section,String Functionality,String Testcasenumber, String Testcase_description , String Executionmode,String Severity) {


		//failcounter=failcounter+1;
		listOfFailedTCsMap.put(Testcasenumber, new Object[] {Section,Functionality,Testcase_description,Executionmode,Severity});

	}

	@DataProvider(name = "Fetch_FailedTC_data")
	public Object[][] FailedTCMasterData() {

		int TotalFailedTestcaseCount=listOfFailedTCsMap.size();

		Object[][] twoDarray = new Object[TotalFailedTestcaseCount][6];
		Set<Object> keyset = listOfFailedTCsMap.keySet();
		System.out.println();

		for (Object key : keyset) {
			twoDarray[Frow][0] = key;
			System.out.println(key);

			Object [] objArr = listOfFailedTCsMap.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				twoDarray[Frow][Fcoln] = obj;
				System.out.println(obj);
				Fcoln++;
			}

			Frow++;
			Fcoln=1;


		}
		return twoDarray;
	}


}
