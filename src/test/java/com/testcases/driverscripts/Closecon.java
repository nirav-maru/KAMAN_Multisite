package com.testcases.driverscripts;

import java.io.IOException;
import java.util.Set;

import javax.management.MBeanServer;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.net.URL;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;


public class Closecon {


	public static void main(String[] args) throws Exception {
	// TODO Auto-generated method stub
	//@Test
	//public void Closeiedriver() throws IOException, InterruptedException {
		/*Runtime rt = Runtime.getRuntime();
		rt.exec("taskkill /F /IM IEDriverServer.exe");
		rt.exec("taskkill /F /IM iexplore.exe");
		rt.exec("taskkill /F /IM chromedriver.exe");*/

		double Total_CPU_Usage = getProcessCpuLoad();
		
		long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long MaxfreeMemory = Runtime.getRuntime().maxMemory() - usedMemory;
		long freeMemory =Runtime.getRuntime().freeMemory();
		long totalMemory =Runtime.getRuntime().totalMemory();
		long MaxMemory =Runtime.getRuntime().maxMemory();
		System.out.println("CPU Usage   :  " + Total_CPU_Usage + " %");
		System.out.println("Used Memory   :  " + (usedMemory/1024)/1024 + " MB");
        System.out.println("Free Memory   : " + freeMemory/1024/1024 + " MB");
        System.out.println("Total Memory  : " + totalMemory/1024/1024 + " MB");
        System.out.println("Max Memory    : " + MaxMemory/1024/1024 + " MB");      
        System.out.println("Total free Memory from max   : " + MaxfreeMemory/1024/1024 + " MB");  
		
	}
	
	
	public static double getProcessCpuLoad() throws Exception {

	    MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
	    AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

	    if (list.isEmpty())     return Double.NaN;

	    Attribute att = (Attribute)list.get(0);
	    Double value  = (Double)att.getValue();
	    if (value == -1.0)      return Double.NaN;
	   // System.out.println("Percentage : " + ((int)(value * 1000) / 10.0));
	    return ((int)(value * 1000) / 10.0);
	}
}
