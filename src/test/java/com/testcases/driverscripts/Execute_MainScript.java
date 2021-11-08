package com.testcases.driverscripts;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.operations.Common.FireClass;
import com.operations.Common.ReadStats;
import com.operations.Common.Constants;
import com.operations.Common.ReadUserconfig;
import com.operations.Common.Script_executor;
import com.operations.Common.Xls_writer;
import com.operations.Master_data;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.Utilities.CreateExecutionStatusHTMLfile;
//import com.Utilities.SendEmail;
//import com.Utilities.SendStatusReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Execute_MainScript {

	Platform macOS;

	public static WebDriver webdriver=null;

	public static Logger Applog;

	public static ExtentHtmlReporter htmlreporter;
	public static ExtentHtmlReporter htmlTempreporter;

	public static ExtentReports extent;

	public ExtentTest test;

	Script_executor scre = new Script_executor();

	public static String browser;

	String Testcasenumber;

	String failmsg;
	public static String Sitename;
	public static String Channel;
	String Device;
	public static String Env;
	String Section;
	String Functionality;
	String Testcase_description; 
	String Executionmode;
	String Severity;
	String LogJiraTicket;
	public static String ExecutionRound;
	int DeviceScrHeight;
	int DeviceScrWidth;
	public static int ExecutionRoundCounter;
	public static Date Startdate;
	//public static Date Enddate;
	public static Date Startdate_Email;
	//public static Date Enddate_Email;

	long startTime ;
	String Object;
	File Reportdir;
	File TempReportdir;
	public static int Passcounter;	
	public static int skipcounter;
	public static int TotalTCcounter;
	public static String ExecutionStart;
	int Testcasecounter;
	public static String FailedsheetPath;
	Xls_writer xls_writer=new Xls_writer();

	ReadStats rs = new ReadStats();
	ReadUserconfig uc =new ReadUserconfig();
	//SendStatusReport email =new SendStatusReport();
	StringWriter stack = new StringWriter();
	Script_executor screxe = new Script_executor();
	FireClass FC = new FireClass();
	CollectTestData gtest = new CollectTestData();
	CreateExecutionStatusHTMLfile html = new CreateExecutionStatusHTMLfile();

	public static SimpleDateFormat StartTime;
	//public static SimpleDateFormat EndTime;
	public static SimpleDateFormat StartTime_Email;
	//public static SimpleDateFormat EndTime_Email;
	TimeZone timeZone = TimeZone.getTimeZone("IST");

	Map<Integer, Object[]> Testcase_skipresults = new LinkedHashMap<Integer, Object[]>();

	Map<Integer, Object[]> Testscase_failresults = new LinkedHashMap<Integer, Object[]>();

	SoftAssert softAssert = new SoftAssert();


	@BeforeTest(groups={"Execute_MainScript.Pre_requisite"})
	@Parameters({"Channel","Device","DeviceScrHeight","DeviceScrWidth"})
	public void Pre_requisite() throws IOException{

		gtest.tests();
		rs.getRepositoryValues();
		uc.getUserConfig();
		extent = new ExtentReports ();
		Applog=Logger.getLogger(uc.SiteName);
		ExecutionRoundCounter = ExecutionRoundCounter +1;
		ExecutionRound= "Round_"+ExecutionRoundCounter;

		PropertyConfigurator.configure(System.getProperty("user.dir")+"/Resources/log4j.properties");
		Startdate = new Date() ;
		StartTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss") ;
		Startdate_Email = new Date() ;
		StartTime_Email = new SimpleDateFormat("MM-dd-yyyy hh:mm") ;


		//System.out.println(Startdate_Email);

		String TempRep_file=System.getProperty("user.dir") +"/test-output/TestSummary_Report.html";
		TempReportdir= new File(TempRep_file);
		TempReportdir.getParentFile().mkdirs();
		TempReportdir.createNewFile();

		if ((uc.HistoricalReports).equalsIgnoreCase("Yes")) {

			String rep_file=System.getProperty("user.dir") +"/Reports/"+ StartTime.format(Startdate)+"/"+rs.Envtype+"/"+"/TestSummary_Report.html";
			Reportdir= new File(rep_file);
			Reportdir.getParentFile().mkdirs();
			Reportdir.createNewFile();
			htmlreporter= new ExtentHtmlReporter(Reportdir);
			extent.attachReporter(htmlreporter);
		}

		htmlTempreporter= new ExtentHtmlReporter(TempReportdir);



		extent.attachReporter(htmlTempreporter);

		//startTime = System.
		Applog.info("Execution started on " + StartTime.format(Startdate));
		System.out.println("Execution started on : " + StartTime.format(Startdate));
		StartTime_Email.setTimeZone(timeZone);
		//dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm") ;
		//Applog.info("Execution started on Firefox" + dateFormat.format(date));
	}


	@Parameters({"Channel","Device","DeviceScrHeight","DeviceScrWidth"})
	@BeforeTest(dependsOnMethods ="Pre_requisite")

	public void EnvSetup(String Channel,String Device,int DeviceScrHeight,int DeviceScrWidth) throws IOException
	{
		this.browser=rs.browser;
		this.Channel=Channel;
		this.Env=rs.Envtype;
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("start-maximized");
		//options.addArguments("--disable-notifications");

		if (Channel.equalsIgnoreCase("Desktop")) {


			if (browser.equalsIgnoreCase("IE11"))
			{

				InternetExplorerOptions ieOptions = new InternetExplorerOptions();
				ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				ieOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
				//ieOptions.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, false);
				//ieOptions.setCapability("requireWindowFocus", true);
				//ieOptions.setCapability("ignoreZoomSetting", true);
				//ieOptions.setCapability("nativeEvents",false);
				//ieOptions.

				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +"/Browser_files/IEDriverServer_Win32_3.150.1/IEDriverServer.exe");
				webdriver=new InternetExplorerDriver(ieOptions);
				webdriver.manage().window().maximize();

				this.browser=browser;
				this.Channel=Channel;

				Applog.info("Execution started on IE" + StartTime.format(Startdate));


			} else if (browser.equalsIgnoreCase("chrome"))
			{

				if(uc.OS.equalsIgnoreCase("Linux")) {

					//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"/Browser_files/chromedriver_linux64/chromedriver");
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"/Browser_files/chromedriver_win32/chromedriver.exe");
					ChromeOptions options = new ChromeOptions();
					//options.addArguments("--headless");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("disable-blink-features=AutomationControlled");
					options.addArguments("window-size=1364,768");
					webdriver = new ChromeDriver(options);
					//webdriver.manage().window().maximize();
				}

				else if (uc.OS.equalsIgnoreCase("Windows")) {
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"/Browser_files/chromedriver_win32/chromedriver.exe");
					//WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					//options.addArguments("disable-blink-features=AutomationControlled");
					webdriver = new ChromeDriver(options);
					//Dimension d = new Dimension(DeviceScrWidth, DeviceScrHeight);
					//webdriver.manage().window().setSize(d);
					webdriver.manage().window().maximize();
				}


				this.browser=browser;
				this.Channel=Channel;
				//Applog.info(" Execution started on Chrome" + dateFormat.format(date));

			}

			else if (browser.equalsIgnoreCase("Firefox"))
			{

				if(uc.OS.equalsIgnoreCase("Linux")) {

					//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"/Browser_files/chromedriver_linux64/chromedriver");
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"/Browser_files/chromedriver_win32/chromedriver.exe");
					ChromeOptions options = new ChromeOptions();
					//options.addArguments("--headless");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("window-size=1364,768");
					webdriver = new ChromeDriver(options);
					//webdriver.manage().window().maximize();
				}

				else if (uc.OS.equalsIgnoreCase("Windows")) {
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"/Browser_files/geckodriver/geckodriver.exe");
					//WebDriverManager.chromedriver().setup();
					webdriver = new FirefoxDriver();
					//Dimension d = new Dimension(DeviceScrWidth, DeviceScrHeight);
					//webdriver.manage().window().setSize(d);
					webdriver.manage().window().maximize();
				}


				this.browser=browser;
				this.Channel=Channel;
				//Applog.info(" Execution started on Chrome" + dateFormat.format(date));

			}
			
			else if (browser.equalsIgnoreCase("Edge")) {
				
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") +"/Browser_files/edgedriver/msedgedriver.exe");
				webdriver= new EdgeDriver();
				webdriver.manage().window().maximize();
			}
			else
			{
				throw new IllegalArgumentException("The Browser Type is Undefined");
			}
		}}


	/*else if (Channel.equalsIgnoreCase("Mobile")) {
		this.Device=Device;
		this.DeviceScrHeight=DeviceScrHeight;
		this.DeviceScrWidth=DeviceScrWidth;
		if (browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"/Browser_files/geckodriver-v0.23.0-win64/geckodriver.exe");
			webdriver = new FirefoxDriver();
			Dimension d = new Dimension(DeviceScrWidth,DeviceScrHeight);
			webdriver.manage().window().setSize(d);
			//Applog.info("Mobile Test execution started on Firefox" + dateFormat.format(date));
		} else if (browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"/Browser_files/chromedriver_win32/chromedriver.exe");
			//WebDriverManager.chromedriver().setup();
			webdriver = new ChromeDriver();
			Dimension d = new Dimension(DeviceScrWidth,DeviceScrHeight);
			webdriver.manage().window().setSize(d);
			Applog.info("Mobile Test execution started on Chrome" + StartTime.format(Startdate));
		}
		else
		{
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}
	}
	else {
		throw new IllegalArgumentException("The Channel Type is Undefined");
	}
}
	 */





	//	@Test(priority =1 ,dataProvider = "Fetch_Master_data",dataProviderClass=Master_data.class, retryAnalyzer=RetryFailedTestCases.class)
	@Test(invocationCount = 1,priority =1 ,dataProvider = "Fetch_Master_data",dataProviderClass=Master_data.class)
	public void ExecuteTest(String Section,String Functionality,String Testcasenumber, String Testcase_description , String Executionmode,String Severity) throws Throwable  
	{

		this.Testcasenumber=Testcasenumber;
		this.Sitename=uc.SiteName;
		this.Section=Section;
		this.Functionality=Functionality;
		this.Testcase_description=Testcase_description;
		this.Executionmode=Executionmode;
		this.Severity=Severity;

		TotalTCcounter = TotalTCcounter +1;




		if(Executionmode.equalsIgnoreCase("Yes")){

			if (browser.equalsIgnoreCase("IE11")){

				Testcasecounter = Testcasecounter + 1;

				if (Testcasecounter==1) {

					webdriver.close();
					Runtime rt = Runtime.getRuntime();
					rt.exec("taskkill /F /IM IEDriverServer.exe");
					rt.exec("taskkill /F /IM iexplore.exe");
					Thread.sleep(2000);
					InternetExplorerOptions ieOptions = new InternetExplorerOptions();
					ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
					ieOptions.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
					/*ieOptions.setCapability("requireWindowFocus", true);
					ieOptions.setCapability("ignoreZoomSetting", true);
					 */
					System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +"/Browser_files/IEDriverServer_Win32_3.150.1/IEDriverServer.exe");
					webdriver=new InternetExplorerDriver(ieOptions);
					webdriver.manage().window().maximize();
					Testcasecounter=0;


					/*System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +"/Browser_files/IEDriverServer_Win32_3.150.1/IEDriverServer.exe");
					webdriver=new InternetExplorerDriver(ieOptions);
					webdriver.manage().window().maximize();
					Testcasecounter=0;*/

				}

			}

			try {
				if(Channel.equalsIgnoreCase("Desktop")){

					if(uc.OS.equalsIgnoreCase("Windows")) {

						FC.ExecuteTestcasesWindows(Testcasenumber, scre, Sitename, browser,StartTime, Startdate, webdriver, Functionality, Section, Testcase_description, Executionmode, Severity, extent, Applog,ExecutionRound,Env);
						System.gc();
					}

					if(uc.OS.equalsIgnoreCase("Linux")) {

						///FC.ExecuteTestcasesLinux(Testcasenumber, scre, Sitename, browser,StartTime, Startdate, webdriver, Functionality, Section, Testcase_description, Executionmode, Severity, extent, Applog);
					}

					else {
						//System.out.println("Please Specify OS correctly i.e. either Windows or Linux...!!!!");
					}
					//System.out.println("Currently running Testcase : " + Testcasenumber);
					//	scre.Execute_script(Sitename,browser_name,Constants.Windows_InputFileLocation+uc.SiteName+"/",Constants.Windows_OutputFileLocation+StartTime.format(Startdate)+"/"+Sitename+"/"+browser_name+"/",
					//		Constants.Windows_ScreenshotsLocation+StartTime.format(Startdate)+"/"+uc.SiteName+"/"+browser_name+"/", webdriver,Section,Functionality, Testcasenumber, Testcase_description, Executionmode, Severity,uc.Scr,uc.ExcelReports,extent,Applog);

				}
				/*else if (Channel.equalsIgnoreCase("Mobile")) {
					scre.Execute_script(Sitename,browser,"./Input_files/Actual_testcases/"+uc.SiteName+"/","./Output_files/"+StartTime.format(Startdate)+"/"+Sitename+"/"+browser+"/"+Device+"/",
							"./Screenshots/"+StartTime.format(Startdate)+"/"+uc.SiteName+"/"+browser+"/"+Device+"/", webdriver,Section,Functionality, Testcasenumber, Testcase_description, Executionmode, Severity,uc.Scr,uc.ExcelReports,extent,Applog);

				}*/

			} catch (TimeoutException Te) {


				Te.printStackTrace(new PrintWriter(stack));
				Applog.error(stack.toString());
				FC.FailedTC_SeleniumExcpetions(stack.toString(),Testcase_description, screxe, webdriver, xls_writer, Testscase_failresults, browser,Section, Functionality, Testcasenumber,Testcase_description,Executionmode, Severity, StartTime, Startdate, softAssert, test, extent,ExecutionRound);

			}

			catch(NoSuchElementException Nse) {

				Nse.printStackTrace(new PrintWriter(stack));
				Applog.error(stack.toString());
				FC.FailedTC_SeleniumExcpetions(stack.toString(),Testcase_description, screxe, webdriver, xls_writer, Testscase_failresults, browser,Section, Functionality, Testcasenumber,Testcase_description,Executionmode, Severity, StartTime, Startdate, softAssert, test, extent,ExecutionRound);

			}
			catch(Exception e) {

				e.printStackTrace(new PrintWriter(stack));
				System.out.println(stack.toString());
				Applog.error(stack.toString());
				FC.FailedTC_Excpetions(stack.toString(),Testcase_description, screxe, webdriver, xls_writer, Testscase_failresults, browser,Section, Functionality, Testcasenumber,Testcase_description,Executionmode, Severity, StartTime, Startdate, softAssert, test, extent,ExecutionRound);
				Assert.fail(stack.toString());

				//	}
				//stack.flush();


			}

		}


		else{

			if(uc.ExcelReports.equalsIgnoreCase("Yes")) {

				xls_writer.GenearateSkipFile(Testcase_skipresults,Functionality, Testcasenumber, Severity,System.getProperty("user.dir")+"/Output_files/"+StartTime.format(Startdate)+"/"+uc.SiteName+"/"+browser+"/");
			}

			Applog.info(Testcasenumber + " has been skipped for this execution...");
			throw new SkipException(Testcasenumber +" has been skipped..");
		}
	}

	@AfterMethod
	public void deleteCookies() {

		if(browser.equalsIgnoreCase("chrome")) {
			if (Executionmode.equalsIgnoreCase("Yes")) {
				Set<Cookie> allCookies = webdriver.manage().getCookies();
				for (Cookie cookie : allCookies) {

					String CookieName=cookie.getName();
					//System.out.println(CookieName);
					if (CookieName.equalsIgnoreCase("JSESSIONID") || CookieName.equalsIgnoreCase("com.kamandirect.LoggedInAccountCookie")) {

						webdriver.manage().deleteCookieNamed(CookieName);

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

			FC.CreateFailTCList(Section, Functionality, Testcasenumber, Testcase_description, Executionmode, Severity);
			FailedsheetPath=System.getProperty("user.dir")+Constants.Windows_FailedFileLocation+StartTime.format(Startdate)+"/"+uc.SiteName+"/"+browser+"/"+ExecutionRound;
			xls_writer.GenerateFailReport(Testscase_failresults, Section, Functionality, Testcasenumber,Testcase_description,Executionmode, Severity,FailedsheetPath,ExecutionRound);

			if (Channel.equalsIgnoreCase("Mobile")) {
				test = extent.createTest(Sitename+"_"+browser+"_"+Device+"_"+Testcasenumber);
				test.fail(MarkupHelper.createLabel(Testcasenumber+" has been failed....", ExtentColor.RED));
				System.out.println("Test execution status : FAILED...!!!!");
			}
			else {
				//test = extent.createTest(browser_name+"_"+Testcasenumber);
				//	test.fail(MarkupHelper.createLabel(Testcasenumber+"\t"+" has been failed due to following reason : "+ "\n"+ stack.toString(), ExtentColor.RED));

			}

		}        
		else if (result.getStatus() == ITestResult.SKIP) {

			if (Channel.equalsIgnoreCase("Mobile")) {
				test = extent.createTest(Sitename+"_"+browser+"_"+Device+"_"+Testcasenumber);
				test.skip(MarkupHelper.createLabel(Testcasenumber+" has been skipped for this execution...", ExtentColor.AMBER));
				//System.out.println("Test execution status : SKIPPED.....");
			}

			else {

				test = extent.createTest(browser+"_"+Testcasenumber);
				test.skip(MarkupHelper.createLabel(Testcasenumber+" has been skipped for this execution...", ExtentColor.AMBER));
				//System.out.println("Test execution status : SKIPPED.....");
				skipcounter = skipcounter +1;
			}

		}
		else if (result.getStatus() == ITestResult.SUCCESS) {

			if (Channel.equalsIgnoreCase("Mobile")) {
				test = extent.createTest(Sitename+"_"+browser+"_"+Device+"_"+Testcasenumber);
				test.pass(MarkupHelper.createLabel(Testcasenumber + " has been passed", ExtentColor.GREEN));
				System.out.println("Test execution status : PASSED...$$$$");
			}
			else {
				test = extent.createTest(browser+"_"+Testcasenumber);
				test.pass(MarkupHelper.createLabel(Testcasenumber + " has been passed", ExtentColor.GREEN));
				System.out.println("Test execution status : PASSED...$$$$");
				Passcounter = Passcounter +1;

			}

		}
	}


	/*@AfterSuite
	public void close() {
		//email.performTask();
	}  
	 */

	/*
	@AfterClass()

	public void GetFailedData() throws Throwable {

		//FC.FailedTCMasterData();
		//FireClass fc = new FireClass();
		Object[][] mapping = FC.FailedTCMasterData();
		int Failcol;
		for (int Failrow = 0; Failrow < mapping.length; Failrow++) {

			String Testcasenumber =String.valueOf(mapping[Failrow][0]);
			String Section= String.valueOf(mapping[Failrow][1]);
			String Functionality= String.valueOf(mapping[Failrow][2]);
			String Testcase_description= String.valueOf(mapping[Failrow][3]);
			String Executionmode= String.valueOf(mapping[Failrow][4]);
			String Severity= String.valueOf(mapping[Failrow][5]);

			ExecuteTest(Section, Functionality, Testcasenumber, Testcase_description, Executionmode, Severity);
			System.gc();
		}


	}*/






	/*@AfterClass(dependsOnMethods="GetFailedData")
	@Parameters({"browser"})

	public void close(String browser) throws IOException {
		extent.flush();
		Enddate = new Date() ;
		EndTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss") ;
		Enddate_Email = new Date() ;
		EndTime_Email = new SimpleDateFormat("MM-dd-yyyy hh:mm") ;
		EndTime_Email.setTimeZone(timeZone);
		//System.out.println(Enddate_Email);
		EndTime_Email = new SimpleDateFormat("mm/dd/yyyy hh:mm",Locale.getDefault()) ;
		Applog.info("Execution ended on : " + EndTime.format(Enddate));
		System.out.println("Execution ended on : " + EndTime.format(Enddate));
		long diff = Enddate.getTime() - Startdate.getTime();
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		System.out.print("Total Time for Execution : ");
		System.out.print(diffHours + " hours, ");
		System.out.print(diffMinutes + " minutes, ");
		System.out.print(diffSeconds + " seconds.");
		html.GenerateFinalExecutionStatus();
	}

	@AfterClass(dependsOnMethods="close")
	@Parameters({"browser"})

	public void closebrowser(String browser) throws IOException {	
		if (browser.equalsIgnoreCase("firefox")){
			webdriver.close();	
			Runtime rt = Runtime.getRuntime();
			rt.exec("taskkill /F /IM geckodriver.exe");
		}
		else if(browser.equalsIgnoreCase("chrome")){
			webdriver.close();
			if(uc.OS.equalsIgnoreCase("Windows")) {
				Runtime rt = Runtime.getRuntime();
				rt.exec("taskkill /F /IM chromedriver.exe");
				//email.performTask();
			}


		}
		else if(browser.equalsIgnoreCase("ie")){
			webdriver.close();
			Runtime rt = Runtime.getRuntime();
			rt.exec("taskkill /F /IM IEDriverServer.exe");
			rt.exec("taskkill /F /IM iexplore.exe");
		}
		else if(browser.equalsIgnoreCase("safari")){
			webdriver.close();

		}

	}*/


}