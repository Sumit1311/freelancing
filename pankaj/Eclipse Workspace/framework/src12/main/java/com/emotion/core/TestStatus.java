package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 12 NOV 2014
 */

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;

public class TestStatus 
{
	public static void fail()
	{
		fail("Test case failed");
	}
	
	public static void fail(String message)
	{
		SoftAssert.clearErrors();
		Log.error(message);
		Reporter.log(message);
		Assert.fail(message);
	}
	
	public static void pass()
	{
		pass("Test Case Passed");
	}
	
	public static void pass(String message)
	{
		Log.info(message);
		Reporter.log(message);
	}
	
	public static void skip()
	{
		skip("Test Case Skipped");
	}
	
	public static void skip(String message)
	{
		SoftAssert.clearErrors();
		Log.warn(message);
		Reporter.log(message);
		throw new SkipException(message);
	}
}
