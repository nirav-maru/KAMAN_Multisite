package com.testcases.driverscripts;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//import com.JiraProcess.JiraRules;
//import com.JiraProcess.SendJiraDetails;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.operations.FailedTC_Master_data;
import com.operations.Common.Constants;
import com.operations.Common.FireClass;
import com.operations.Common.ReadUserconfig;


public class Execute_FailedScript {


	String Testcasenumber;
	String Sitename;
	String Section;
	String Functionality;
	String Testcase_description;
	String Executionmode;
	String Severity;
	public String FailRound;
	public static String FailedsheetPath;
	public static SimpleDateFormat EndTime;
	public static SimpleDateFormat EndTime_Email;
	public static Date Enddate;
	public static Date Enddate_Email;
	int FailTestcasecounter;
	public static int failcounter;
	

	Execute_MainScript exMain = new Execute_MainScript();
	ReadUserconfig uc =new ReadUserconfig();

	@BeforeTest(dependsOnGroups={"Execute_MainScript.Pre_requisite"})

	public void Failed_Pre_requisite() throws IOException{

		uc.getUserConfig();
		int FailCounter = exMain.ExecutionRoundCounter;
		FailCounter =  FailCounter+1;
		FailRound="Round_"+FailCounter;

	}


	//@JiraRules(logJiraTicket=true)
	@Test(priority =1 ,dataProvider = "Fetch_FailedTC_data",dataProviderClass=FailedTC_Master_data.class)
	public void ExecuteFailedTC(String Section,String Functionality,String Testcasenumber, String Testcase_description , String Executionmode,String Severity) throws Throwable {

		System.out.println("ReExecution of Failed testcase "+Testcasenumber+ " Started...");

		this.Testcasenumber=Testcasenumber;
		this.Sitename=uc.SiteName;
		this.Section=Section;
		this.Functionality=Functionality;
		this.Testcase_description=Testcase_description;
		this.Executionmode=Executionmode;
		this.Severity=Severity;




		if(Executionmode.equalsIgnoreCase("Yes")){

			if (Execute_MainScript.browser.equalsIgnoreCase("IE11")){

				FailTestcasecounter = FailTestcasecounter + 1;

				if (FailTestcasecounter==1) {

					Execute_MainScript.webdriver.close();
					Runtime rt = Runtime.getRuntime();
					rt.exec("taskkill /F /IM IEDriverServer.exe");
					rt.exec("taskkill /F /IM iexplore.exe");
					Thread.sleep(2000);
					InternetExplorerOptions ieOptions = new InternetExplorerOptions();
					ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
					ieOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
					//ieOptions.setCapability("requireWindowFocus", true);
					//ieOptions.setCapability("ignoreZoomSetting", true);

					System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +"/Browser_files/IEDriverServer_Win32_3.150.1/IEDriverServer.exe");
					Execute_MainScript.webdriver=new InternetExplorerDriver(ieOptions);
					Execute_MainScript.webdriver.manage().window().maximize();
					FailTestcasecounter=0;
				}

			}



			try {
				if(Execute_MainScript.Channel.equalsIgnoreCase("Desktop")){

					if(uc.OS.equalsIgnoreCase("Windows")) {

						exMain.FC.ExecuteTestcasesWindows(Testcasenumber, exMain.scre, Execute_MainScript.Sitename, Execute_MainScript.browser,Execute_MainScript.StartTime, Execute_MainScript.Startdate, Execute_MainScript.webdriver, Functionality, Section, Testcase_description, Executionmode, Severity, Execute_MainScript.extent, Execute_MainScript.Applog,FailRound,Execute_MainScript.Env,Execute_MainScript.Concept);
						System.gc();
					}

				}
			} catch (TimeoutException Te) {


				Te.printStackTrace(new PrintWriter(exMain.stack));
				Execute_MainScript.Applog.error(exMain.stack.toString());
				exMain.test = Execute_MainScript.extent.createTest(Execute_MainScript.browser+"_"+Testcasenumber);
				exMain.test.fail(MarkupHelper.createLabel(FireClass.failmsg,ExtentColor.RED));
				exMain.FC.FailedTC_SeleniumExcpetions(exMain.stack.toString(),Testcase_description, exMain.screxe, Execute_MainScript.webdriver, exMain.xls_writer, exMain.Testscase_failresults, Execute_MainScript.browser,Section, Functionality, Testcasenumber,Testcase_description,Executionmode, Severity, Execute_MainScript.StartTime, Execute_MainScript.Startdate, exMain.softAssert,exMain.test, Execute_MainScript.extent,FailRound);

			}

			catch(NoSuchElementException Nse) {

				Nse.printStackTrace(new PrintWriter(exMain.stack));
				Execute_MainScript.Applog.error(exMain.stack.toString());
				exMain.test = Execute_MainScript.extent.createTest(Execute_MainScript.browser+"_"+Testcasenumber);
				exMain.test.fail(MarkupHelper.createLabel(FireClass.failmsg,ExtentColor.RED));
				exMain.FC.FailedTC_SeleniumExcpetions(exMain.stack.toString(),Testcase_description, exMain.screxe, Execute_MainScript.webdriver, exMain.xls_writer, exMain.Testscase_failresults, Execute_MainScript.browser,Section, Functionality, Testcasenumber,Testcase_description,Executionmode, Severity, Execute_MainScript.StartTime, Execute_MainScript.Startdate, exMain.softAssert, exMain.test, Execute_MainScript.extent,FailRound);

			}
			catch(Exception e) {

				e.printStackTrace(new PrintWriter(exMain.stack));
				System.out.println(exMain.stack.toString());
				Execute_MainScript.Applog.error(exMain.stack.toString());
				exMain.test = Execute_MainScript.extent.createTest(Execute_MainScript.browser+"_"+Testcasenumber);
				exMain.test.fail(MarkupHelper.createLabel(FireClass.exception,ExtentColor.RED));
				exMain.FC.FailedTC_Excpetions(exMain.stack.toString(),Testcase_description, exMain.screxe, Execute_MainScript.webdriver, exMain.xls_writer, exMain.Testscase_failresults, Execute_MainScript.browser,Section, Functionality, Testcasenumber,Testcase_description,Executionmode, Severity, Execute_MainScript.StartTime, Execute_MainScript.Startdate, exMain.softAssert, exMain.test, Execute_MainScript.extent,FailRound);
				Assert.fail(exMain.stack.toString());


			}

		}
		else{

			if(uc.ExcelReports.equalsIgnoreCase("Yes")) {

				exMain.xls_writer.GenearateSkipFile(exMain.Testcase_skipresults,Functionality, Testcasenumber, Severity,System.getProperty("user.dir")+"/Output_files/"+Execute_MainScript.StartTime.format(Execute_MainScript.Startdate)+"/"+uc.SiteName+"/"+Execute_MainScript.browser+"/");
			}

			Execute_MainScript.Applog.info(Testcasenumber + " has been skipped for this execution...");
			throw new SkipException(Testcasenumber +" has been skipped..");
		}

	}


	@AfterMethod
	public void deleteCookies() {

		if(Execute_MainScript.browser.equalsIgnoreCase("chrome")) {
			if (Executionmode.equalsIgnoreCase("Yes")) {
				Set<Cookie> allCookies = Execute_MainScript.webdriver.manage().getCookies();
				for (Cookie cookie : allCookies) {

					String CookieName=cookie.getName();
					//System.out.println(CookieName);
					if (CookieName.equalsIgnoreCase("JSESSIONID") || CookieName.equalsIgnoreCase("com.kamandirect.LoggedInAccountCookie")) {

						Execute_MainScript.webdriver.manage().deleteCookieNamed(CookieName);

						System.out.println("Cookie : "+CookieName+" Successfully deleted...");
					}

				}

			}
		}
	}

	@AfterMethod(dependsOnMethods="deleteCookies")
	public void TestResults(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {

			System.out.println("Test execution status : FAILED...!!!!");
			FailedsheetPath=System.getProperty("user.dir")+Constants.Windows_FailedFileLocation+exMain.StartTime.format(exMain.Startdate)+"/"+uc.SiteName+"/"+exMain.browser+"/"+FailRound;
			exMain.xls_writer.GenerateFailReport(exMain.Testscase_failresults, Section, Functionality, Testcasenumber,Testcase_description,Executionmode, Severity,FailedsheetPath,FailRound);
			/*exMain.test = Execute_MainScript.extent.createTest(Execute_MainScript.browser+"_"+Testcasenumber);
			exMain.test.fail(MarkupHelper.createLabel("Test failed",ExtentColor.RED));*/
			failcounter = failcounter +1;
		

			/*if(uc.LogJira.equalsIgnoreCase("yes")) {

				SendJiraDetails JiraDetails = new SendJiraDetails(uc.JiraURL, uc.JiraUsername, uc.JiraAPIKey, uc.JiraProjectName);
				String DefectTitle = Testcasenumber + "  Failed ...!!!";
				String Defectdescription=result.getThrowable().getMessage() + "\n" + ExceptionUtils.getFullStackTrace(result.getThrowable());
				JiraDetails.LogJiraDefect("Bug", DefectTitle, Defectdescription, "Unassigned");
			}     */   
	}
			else if (result.getStatus() == ITestResult.SKIP) {


				exMain.test = Execute_MainScript.extent.createTest(Execute_MainScript.browser+"_"+Testcasenumber);
				exMain.test.skip(MarkupHelper.createLabel(Testcasenumber+" has been skipped for this execution...", ExtentColor.AMBER));
				Execute_MainScript.skipcounter = Execute_MainScript.skipcounter +1;
			}


			else if (result.getStatus() == ITestResult.SUCCESS) {

				exMain.test = Execute_MainScript.extent.createTest(Execute_MainScript.browser+"_"+Testcasenumber);
				exMain.test.pass(MarkupHelper.createLabel(Testcasenumber + " has been passed", ExtentColor.GREEN));
				System.out.println("Test execution status : PASSED...$$$$");
				Execute_MainScript.Passcounter = Execute_MainScript.Passcounter +1;

			}

		}
	
	
		@AfterClass
		@Parameters({"browser"})

		public void close(String browser) throws IOException {
			Execute_MainScript.extent.flush();
			Enddate = new Date() ;
			EndTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss") ;
			Enddate_Email = new Date() ;
			EndTime_Email = new SimpleDateFormat("MM-dd-yyyy hh:mm") ;
			EndTime_Email.setTimeZone(exMain.timeZone);
			EndTime_Email = new SimpleDateFormat("mm/dd/yyyy hh:mm",Locale.getDefault()) ;
			Execute_MainScript.Applog.info("Execution ended on : " + EndTime.format(Enddate));
			System.out.println("Execution ended on : " + EndTime.format(Enddate));
			long diff = Enddate.getTime() - Execute_MainScript.Startdate.getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			System.out.print("Total Time for Execution : ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");
			exMain.html.GenerateFinalExecutionStatus();
		}

		@AfterClass(dependsOnMethods="close")
		@Parameters({"browser"})

		public void closebrowser(String browser) throws IOException {	
			if (browser.equalsIgnoreCase("firefox")){
				Execute_MainScript.webdriver.close();	
				Runtime rt = Runtime.getRuntime();
				rt.exec("taskkill /F /IM geckodriver.exe");
			}
			else if(browser.equalsIgnoreCase("chrome")){
				Execute_MainScript.webdriver.close();
				if(uc.OS.equalsIgnoreCase("Windows")) {
					Runtime rt = Runtime.getRuntime();
					rt.exec("taskkill /F /IM chromedriver.exe");

				}


			}
			else if(browser.equalsIgnoreCase("ie11")){
				Execute_MainScript.webdriver.close();
				Runtime rt = Runtime.getRuntime();
				rt.exec("taskkill /F /IM IEDriverServer.exe");
				rt.exec("taskkill /F /IM iexplore.exe");
			}
			else if(browser.equalsIgnoreCase("safari")){
				Execute_MainScript.webdriver.close();

			}

		}


	}
