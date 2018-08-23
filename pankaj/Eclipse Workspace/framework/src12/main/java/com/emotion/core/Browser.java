package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 15 Dec 2014
 */

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public interface Browser extends Elements
{
	WebDriver driver();
	void quit();
	void refresh();
	void maximize();
	
	String getPageSource();
	HandleWindows switchTo();
	ManageWait manageWait();
	
	interface HandleWindows
	{
		void window(String title);
		void closeWindow(String BaseWindowHandle);
		void parentWindow(String ParentWindowHandle);
		void popUpWindow();
		String getBaseWindowHandle();
		Alert alert();
		void frame(String locater);
		void frame(int index);
	}
	
	interface ManageWait
	{
		void implicitlyWait();
		void threadWait(int seconds);
		void implicitlyWait(int timeInSeconds);
		Object explicitlyWait(String ElementTag, String By, String ExpectedCondition,
				int timeInSeconds, String Texttobepresent);
		Object explicitlyWait(String ElementTags,String ExpectedCondition,
				int timeInSeconds, String Texttobepresent);
	}
	
	@Override
	PageElements getElement(String ElementTag);
	@Override
	List<PageElements> getElements(String ElementTag);
	@Override
	PageElements getElement(String locater,String By);
	@Override
	List<PageElements> getElements(String locater,String By);
	
	
	String getElementLocater();
	String getBy();
}


