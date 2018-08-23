package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 19 FEB 2015
 *	Interface to define wrapper methods for Selenium WebElement interface.
 */

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.emotion.core.Elements;

public interface PageElements extends Elements
{
	//Element action, attribute and status related methods
	void click();
	void sendKeys(String Keys);
	void sendKeys(Keys keysToSend);
	void clear();
	String getText();
	String getValue();
	String getAttribute(String Attribute);
	String getTagName();
	boolean isSelected();
	boolean isDisplayed();
	boolean isEnabled();
	
	//Setter and getter methods for WebElement
	void setElement(WebElement element);
	public WebElement getElement();
	
	//Methods to find/get element from page using element tags or element locater & by.
	@Override
	PageElements getElement(String ElementTag);
	@Override
	List<PageElements> getElements(String ElementTag);
	@Override
	PageElements getElement(String locater,String By);
	@Override
	List<PageElements> getElements(String locater,String By);
}
