package com.JiraProcess;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.utils.ExceptionUtil;

public class JiraListeners implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		JiraRules JiraRules = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraRules.class);
		boolean logJiraTicket = JiraRules.logJiraTicket();
		
		if(logJiraTicket) {
			
			SendJiraDetails JiraDetails = new SendJiraDetails("https://logixal.atlassian.net/", "rohit.karkhanis@logixal.com", "R9O7lh1U2mSpPTIYZPy8C3F7", "TA");
			String DefectTitle = result.getMethod().getConstructorOrMethod().getMethod().getName() + "Failed ...!!!";
			String Defectdescription=result.getThrowable().getMessage() + "\n" + ExceptionUtils.getFullStackTrace(result.getThrowable());
			JiraDetails.LogJiraDefect("Bug", DefectTitle, Defectdescription, "Unassigned");
		}
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
