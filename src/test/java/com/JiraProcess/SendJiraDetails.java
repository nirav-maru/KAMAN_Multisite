package com.JiraProcess;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.Issue.FluentCreate;

public class SendJiraDetails {

	public JiraClient jira;
	public String project;
	
	
	public SendJiraDetails(String jiraUrl , String uname , String password, String project) {
		
		BasicCredentials crd = new BasicCredentials(uname, password);
		jira = new JiraClient(jiraUrl,crd);
		this.project=project;
	}
	
	public void LogJiraDefect (String type, String DefectTitle , String description, String reporter) {
		
		try {
			FluentCreate ft = jira.createIssue(project, type);
			ft.field(Field.SUMMARY, DefectTitle);
			ft.field(Field.DESCRIPTION, description);
			Issue newIssue = ft.execute();
			System.out.println("New Dfect created with ID : " +newIssue);
			
		} catch (JiraException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
