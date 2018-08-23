package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 08 DEC 2014
 */

import org.openqa.selenium.By;

public class Locater {
	private String by;
	private String ElementLocater;
	
	private PageObjectRepository repository = new PageObjectRepository();
		
	//Web Page Elements functions 
	
	public String getBy() {
		return this.by;
	}

	private void setLocateBy(String By) {
		this.by = By;
	}
	
	private void setElementLocater(String elementLocater) {
		this.ElementLocater = elementLocater;
	}
	
	public String getElementLocater() {
		return this.ElementLocater;
	}
	
	public void getElementLocater(String ElementTag)
	{
		setElementLocater(repository.getElmentValue(ElementTag)); 
		setLocateBy(repository.getAttribute());
	}

	protected By locater(String ElementTag) {
		getElementLocater(ElementTag);
		return locater(getElementLocater(), getBy());
	}
	
	protected By locater(String ElementLocater,String by)
	{
		switch (by.toLowerCase()) {
		case "id":
			return By.id(ElementLocater);
		case "linktext":
			return By.linkText(ElementLocater);
		case "xpath":
			return By.xpath(ElementLocater);
		case "name":
			return By.name(ElementLocater);
		case "classname":
			return By.className(ElementLocater);
		case "tagname":
			return By.tagName(ElementLocater);
		case "partiallinktext":
			return By.partialLinkText(ElementLocater);
		case "css":
			return By.cssSelector(ElementLocater);
		}
		throw new FrameworkException("Incorrect Locate By for the element.");
	}
}
