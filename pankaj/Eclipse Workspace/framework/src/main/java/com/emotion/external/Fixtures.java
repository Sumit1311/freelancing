package com.emotion.external;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 19 NOV 2014
 */

import com.emotion.core.Browser;
import com.emotion.core.Log;
import com.emotion.lib.MMOD;

public class Fixtures 
{
	private GlobalSetup global =  new GlobalSetup();
	public Browser browser = null;
	public MMOD mmod = null;	
	
	public void setupFixture(String message)
	{
		browser = global.getBrowser();
		mmod=new MMOD(browser);
		Log.info(message);
		browser.manageWait().implicitlyWait(20);
		mmod.authentication().login();
		Log.info("Fixture Setup completed");
	}
	
	public void tearDownFixture()
	{
		Log.info("Fixture teardown started.");
		mmod.authentication().logOut();
		Log.info("Quit Webdriver.");
		browser.quit();
		Log.info("Fixture teardown completed.");
	}
	
	public void setupFixtureWithoutLogin(String message)
	{
		browser = global.getBrowser();
		mmod=new MMOD(browser);
		Log.info(message);
		browser.manageWait().implicitlyWait(20);
		Log.info("Fixture Setup completed");
	}
	
	public void tearDownFixtureWithoutLogin()
	{
		Log.info("Fixture teardown started.");
		Log.info("Quit Webdriver.");
		browser.quit();
		Log.info("Fixture teardown completed.");
	}
	
}
