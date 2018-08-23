package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 12 NOV 2014
 */

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TesngListener implements IInvokedMethodListener 
{
	@SuppressWarnings("unused")
	private Log log = new Log();
	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) 
	{
		String methodname= method.getTestMethod().getMethodName();
		if (methodname.equalsIgnoreCase("setUp"))
		{}
		else if (methodname.equalsIgnoreCase("tearDown"))
		{}
		else
		{
			Log.info("==========TEST CASE START=========");
			Log.info("DESCRIPTION: " + method.getTestMethod().getDescription());
			Log.info("TEST NAME:   " + methodname);
		}
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) 
	{
		String methodname= method.getTestMethod().getMethodName();
		if (methodname.equalsIgnoreCase("tearDown"))
		{Log.info("================================\n");}
		else if (methodname.equalsIgnoreCase("setUp")) 
		{}
		else
		{
			Log.info("==========TEST CASE END=========");
			Log.info("RESULT:" + getMethodStatus(testResult) +"\n");
		}
	}
	
	private String getMethodStatus(ITestResult result)
	{
		if (result.getStatus() == 1)
		{
			return "PASSED";
		}else if (result.getStatus() == 2)
		{
			return "FAILED";
		}else
		{
		return "TEST NOT RUN";
		}
	}

}
