package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 18 FEB 2015
 *	Class to implement @PageElement method and extends Locater class. This class implement all the method that 
 *	are required to deal with elements. Selenium methods are wrapped in this class similar to WebElement interface.
 */


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PageElement extends Locater implements PageElements {

	private WebElement element=null;
	/**
	 * Method to get WebElement instance
	 */
	public WebElement getElement() {
		return element;
	}
	/**
	 * Method to set Webelement instance
	 */
	public void setElement(WebElement element) {
		this.element = element;
	}
	
	/**
	 * implemented override method to find element on page
	 */
	@Override
	public PageElements getElement(String ElementTag) {
		if (ElementTag != null)
		{
			PageElements ele = new PageElement();
			ele.setElement(this.getElement().findElement(locater(ElementTag)));
			return ele;
		}
		throw new FrameworkException("Invalid Element Tag : " + ElementTag);
	}
	/**
	 * implemented override method to find elements on page
	 */
	@Override
	public PageElements getElement(String locater,String By)
	{
		if (locater != null)
		{
			PageElements ele = new PageElement();
			ele.setElement(this.getElement().findElement(locater(locater,By)));
			return ele;
		}
		throw new FrameworkException("Invalid locater : " + locater);
	}
	
	/**
	 * implemented override method to find element on page
	 */
	@Override
	public List<PageElements> getElements(String locater,String By) {
		if (locater != null)
		{
			List<WebElement> list = getElement().findElements(locater(locater,By));
			List<PageElements> list2 = new ArrayList<PageElements>();
			for (WebElement webElement : list) {
				PageElements ele = new PageElement();
				ele.setElement(webElement);
				list2.add(ele);
			}
			return list2;
		}
		throw new FrameworkException("Invalid Element Tag : " + locater);
	}
	
	/**
	 * implemented override method to find elements on page
	 */
	@Override
	public List<PageElements> getElements(String ElementTag) {
		if (ElementTag != null)
		{
			List<WebElement> list = getElement().findElements(locater(ElementTag));
			List<PageElements> list2 = new ArrayList<PageElements>();
			for (WebElement webElement : list) {
				PageElements ele = new PageElement();
				ele.setElement(webElement);
				list2.add(ele);
			}
			return list2;
		}
		throw new FrameworkException("Invalid Element Tag : " + ElementTag);
	}
	
	//Actions on elements
	/**
	 * Method implementation to click on element
	 */
	@Override
	public void click() {
		getElement().click();
	}
	/**
	 * Method implementation to send keys (enter text) on element
	 */
	@Override
	public void sendKeys(String keysToSend) {
		getElement().sendKeys(keysToSend);
	}
	/**
	 * Method implementation to Send keys (enter text) on element
	 */
	@Override
	public void sendKeys(Keys keysToSend) {
		getElement().sendKeys(keysToSend);
	}
	/**
	 * Method implementation to clear value on element
	 */
	@Override
	public void clear() {
		getElement().clear();
	}
	/**
	 * Method implementation to get text present on element
	 */
	@Override
	public String getText() {
		return getElement().getText();		
	}
	/**
	 * Method implementation to get value present for element
	 */
	@Override
	public String getValue() {
		return getElement().getAttribute("value");
	}
	/**
	 * Method implementation to get attribute value for element
	 */
	@Override
	public String getAttribute(String Attribute) {
		return getElement().getAttribute(Attribute);
	}
	/**
	 * Method implementation to check element is selected or not
	 */
	@Override
	public boolean isSelected() 
	{
		return getElement().isSelected();
	}
	/**
	 * Method implementation to check element is displyed or not
	 */
	@Override
	public boolean isDisplayed() {
		return getElement().isDisplayed();
	}
	/**
	 * Method implementation to check element is enable or not
	 */
	@Override
	public boolean isEnabled() {
		return getElement().isEnabled();
	}
	/**
	 * Method implementation to get element tag name
	 */
	@Override
	public String getTagName() {
		return getElement().getTagName();
	}
	
}
