package com.emotion.lib;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 NOV 2014
 */

import com.emotion.core.Browser;
import com.emotion.core.By;
import com.emotion.external.Configuration;
import com.emotion.external.ElementTag;

public class Authentication implements IMMOD.IAuthentication{

	private Browser browser = null;

	public Authentication(Browser browser)
	{
		this.browser=browser;
	}
	
	private String getURL()
	{
		String URL = Configuration.getProperty(Configuration.URL);
		if(URL==null || URL.isEmpty())
		{
			URL= "http://" + Configuration.getProperty(Configuration.HOSTNAME) + ":" + 
						Configuration.getProperty(Configuration.PORT);
		}
		return URL;
	}
	
	private String getUsername()
	{
		return Configuration.getProperty(Configuration.USERNAME);
	}
	
	private String getPassword()
	{
		return Configuration.getProperty(Configuration.PASSWORD);
	}
	
	@Override
	public void login()
	{
		login(getUsername(), getPassword());
	}
	
	@Override
	public void login(String username, String password)
	{
		browser.driver().get(getURL());
		browser.maximize();
		browser.getElement(ElementTag.txtUserName).sendKeys(username);
		browser.getElement(ElementTag.txtPassword).sendKeys(password);
		browser.getElement(ElementTag.bttnLogin).click();
	}
	
	@Override
	public boolean isUserLoggedIn(String username)
	{
		if(browser.getElement(username, By.linktext).getText()==username && 
				browser.driver().getTitle().equals("Media Management On Demand - Home"))
		{
			return true;
		}else
		{
			return false;	
		}
	}
	
	@Override
	public void logOut()
	{
		browser.getElement(ElementTag.linkSignOut).click();
	}
	
}
