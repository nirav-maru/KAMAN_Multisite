package com.JiraProcess;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewTest {
	@JiraRules(logJiraTicket=true)
	@Test
	public void f123() throws IOException {

		WebDriver webdriver;
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
		
		webdriver.get("http://ec2-54-83-9-212.compute-1.amazonaws.com/coerto/");
		String url = webdriver.getCurrentUrl();
		System.out.println(url);
		
		Assert.assertEquals(url, "http://ec2-54-83-9-212.compute-1.amazonaws.com/concerto/Login?goto=Central");
		webdriver.close();
		Runtime rt = Runtime.getRuntime();
		rt.exec("taskkill /F /IM IEDriverServer.exe");
		rt.exec("taskkill /F /IM iexplore.exe");
	}
}
