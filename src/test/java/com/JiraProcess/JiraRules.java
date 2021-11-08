package com.JiraProcess;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)	
public @interface JiraRules {

	boolean logJiraTicket();
	
}
