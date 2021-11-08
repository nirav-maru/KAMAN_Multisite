package com.testcases.driverscripts;

import org.testng.annotations.Test;

import com.operations.Master_data;
import com.operations.Common.FireClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest {
	File cDrive = new File("C:");

	@Test(priority=1)
	public void f() {

		System.out.println("********************* Calculating Disk space  ***************************");

		System.out.println(String.format("Total space: %.2f GB",
				(double)cDrive.getTotalSpace() /1073741824));
		System.out.println(String.format("Free space: %.2f GB", 
				(double)cDrive.getFreeSpace() /1073741824));
		System.out.println(String.format("Usable space: %.2f GB", 
				(double)cDrive.getUsableSpace() /1073741824));

		System.out.println("********************* Calculate Disk space completed ***************************");
		System.out.println();
		System.out.println();

		System.out.println("********************* Calculating CPU Usage ***************************");

		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

		for(Long threadID : threadMXBean.getAllThreadIds()) {
			ThreadInfo info = threadMXBean.getThreadInfo(threadID);
			System.out.println("Thread name: " + info.getThreadName());
			System.out.println("Thread State: " + info.getThreadState());
			System.out.println(String.format("CPU time: %s ns", 
					threadMXBean.getThreadCpuTime(threadID)));



		}

		System.out.println("********************* Calculate CPU usage completed ***************************");

		System.out.println();
		System.out.println();

		System.out.println("********************* Calculate Memory completed ***************************");

		MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
		System.out.println(String.format("Initial memory: %.2f GB", 
				(double)memoryMXBean.getHeapMemoryUsage().getInit() /1073741824));
		System.out.println(String.format("Used heap memory: %.2f GB", 
				(double)memoryMXBean.getHeapMemoryUsage().getUsed() /1073741824));
		System.out.println(String.format("Max heap memory: %.2f GB", 
				(double)memoryMXBean.getHeapMemoryUsage().getMax() /1073741824));
		System.out.println(String.format("Committed memory: %.2f GB", 
				(double)memoryMXBean.getHeapMemoryUsage().getCommitted() /1073741824));


		System.out.println("********************* Calculate Memory completed ***************************");
		System.out.println();
		System.out.println();

	}
	@Test(priority=2)
	public double f1() throws MalformedObjectNameException, NullPointerException, InstanceNotFoundException, ReflectionException {


		MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
		ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
		AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

		if (list.isEmpty())    return Double.NaN;

		Attribute att = (Attribute)list.get(0);
		Double value  = (Double)att.getValue();

		// usually takes a couple of seconds before we get real values
		if (value == -1.0)      return Double.NaN;
		// returns a percentage value with 1 decimal point precision
		System.out.println("Percentage : " + ((int)(value * 1000) / 10.0));
		return ((int)(value * 1000) / 10.0);


	}

	@Test(priority=3)
	public void f2() {

		System.out.println("This is Test3 ....");



	}


	@BeforeMethod
	public void beforeMethod() {

		System.out.println("This is beforeMethod ....");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("This is afterMethod ....");
	}

	@BeforeClass
	public void beforeClass() {

		System.out.println("This is beforeClass ....");
	}

	@AfterClass
	public void afterClass() {

		System.out.println("This is afterClass ....");
	}

	@BeforeTest
	public void beforeTest() {

		System.out.println("This is beforeTest ....");
	}

	@AfterTest
	public void afterTest() {

		System.out.println("This is afterTest ....");
	}


	@BeforeSuite
	public void beforeSuite() {

		System.out.println("This is beforeSuite ....");
	}

	@AfterSuite
	public void afterSuite() {

		System.out.println("This is afterSuite ....");
	}

}
