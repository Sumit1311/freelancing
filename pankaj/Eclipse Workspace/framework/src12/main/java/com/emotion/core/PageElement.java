package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 08 DEC 2014
 */


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PageElement extends Locater implements PageElements {

	private WebElement element=null;
		
	public WebElement getElement() {
		return element;
	}

	public void setElement(WebElement element) {
		this.element = element;
	}

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
	
	@Override
	public void click() {
		getElement().click();
	}

	@Override
	public void sendKeys(String keysToSend) {
		getElement().sendKeys(keysToSend);
	}

	@Override
	public void sendKeys(Keys keysToSend) {
		getElement().sendKeys(keysToSend);
	}

	@Override
	public void clear() {
		getElement().clear();
	}

	@Override
	public String getText() {
		return getElement().getText();		
	}

	@Override
	public String getValue() {
		return getElement().getAttribute("value");
	}

	@Override
	public String getAttribute(String Attribute) {
		return getElement().getAttribute(Attribute);
	}
	
	@Override
	public boolean isSelected() 
	{
		return getElement().isSelected();
	}
	
	@Override
	public boolean isDisplayed() {
		return getElement().isDisplayed();
	}
	
	@Override
	public boolean isEnabled() {
		return getElement().isEnabled();
	}
	
	@Override
	public String getTagName() {
		return getElement().getTagName();
	}
	
}
