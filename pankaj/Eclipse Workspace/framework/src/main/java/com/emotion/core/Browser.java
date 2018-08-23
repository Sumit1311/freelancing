package com.emotion.core;

/**
 * 	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 Feb 2014
 *
 *	Browser Interface to provide wrapped methods and interfaces to Selenium Webdriver functionality
 *	This interface provides base for the automation framework and webdriver functions.
 *	Interface contains nested interfaces for handling windows and wait. Also contains wrapped functions 
 *	for Webdriver to handle browser related operation like quit,refresh,maximize etc..
 *	Also it extends @Elements interface to override getElement() methods.  
 */

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public interface Browser extends Elements
{
	/**
	 * Method to get Webdriver instance
	 * @return @Webdriver instance
	 */
	WebDriver driver();
	/**
	 * Method to quit browser
	 */
	void quit();
	/**
	 * Method to refresh browser
	 */
	void refresh();
	/**
	 * Method to maximize browser
	 */
	void maximize();
	/**
	 * Method to get page source code
	 * @return @String page source code
	 */
	String getPageSource();
	/**
	 * Method to get instance of class where @HandleWindows interface is implemented
	 * @return return instance of class where @HandleWindows interface is implemented
	 */
	HandleWindows switchTo();
	/**
	 * Method to get instance of class where @ManageWait interface is implemented
	 * @return return instance of class where @ManageWait interface is implemented
	 */
	ManageWait manageWait();
	
	interface HandleWindows
	{
		/**
		 * Method switch to pop up browser window with specific title
		 * @param title Title of the pop up browser window
		 */
		void window(String title);
		/**
		 * Method to close pop up browser window
		 * @param BaseWindowHandle Handle for base or parent browser window
		 */
		void closeWindow(String BaseWindowHandle);
		/**
		 * Method to switch to parent browser window
		 * @param ParentWindowHandle Handle for base or parent browser window
		 */
		void parentWindow(String ParentWindowHandle);
		/**
		 * Method switch to pop up browser window
		 */
		void popUpWindow();
		/**
		 * Method to get window handle
		 * @return
		 */
		String getBaseWindowHandle();
		/** 
		 * Method to switch to alert
		 * @return @Alert instance 
		 */
		Alert alert();
		/**
		 * Method to change or switch browser frames using frame locater
		 * @param locater frame locater xpath,id etc...
		 */
		void frame(String locater);
		/**
		 * Method to change or switch browser frame using frame index
		 * @param index Index of frame
		 */ 
		void frame(int index);
	}
	
	interface ManageWait
	{
		/**
		 * Method to set implicitly wait to default time
		 */
		void implicitlyWait();
		/**
		 * Method to set thread wait
		 * @param seconds wait time in seconds
		 */
		void threadWait(int seconds);
		/**
		 * Method to set custom implicitly wait in seconds
		 * @param timeInSeconds wait time in seconds
		 */
		void implicitlyWait(int timeInSeconds);
		/**
		 * Method to set explicitly wait in seconds for any element as per the expected condition to be true.
		 * @param ElementTag element tag from the @ElementTag file
		 * @param By Element locate by as the element locater
		 * @param ExpectedCondition Expected condition for wait from @ExpectedCondition
		 * @param timeInSeconds Wait time in seconds
		 * @param Texttobepresent Text to be present as per the expected condition set, null if not required.
		 * @return returns object as per expected condition,type case can be used to convert in desired type return.
		 */
		Object explicitlyWait(String ElementTag, String By, String ExpectedCondition,
				int timeInSeconds, String Texttobepresent);
		/**
		 * Method to set explicitly wait in seconds for any element as per the expected condition to be true.
		 * @param ElementTags ElementTag element tag from the @ElementTag file
		 * @param ExpectedCondition ExpectedCondition Expected condition for wait from @ExpectedCondition
		 * @param timeInSeconds Wait time in seconds
		 * @param Texttobepresent Text to be present as per the expected condition set, null if not required.
		 * @return object as per expected condition,type case can be used to convert in desired type return
		 */
		Object explicitlyWait(String ElementTags,String ExpectedCondition,
				int timeInSeconds, String Texttobepresent);
	}
	
	/**
	 * Override function from @Element interface
	 */
	@Override
	PageElements getElement(String ElementTag);
	/**
	 * Override function from @Element interface
	 */
	@Override
	List<PageElements> getElements(String ElementTag);
	/**
	 * Override function from @Element interface
	 */
	@Override
	PageElements getElement(String locater,String By);
	/**
	 * Override function from @Element interface
	 */
	@Override
	List<PageElements> getElements(String locater,String By);
	
	/**
	 * Method to get element locater form Page object repository file
	 * @return Element Locater type of @String
	 */
	String getElementLocater();
	/**
	 * Method to get element locate by form Page object repository file
	 * @return Element locate by type of @String
	 */
	String getBy();
}


