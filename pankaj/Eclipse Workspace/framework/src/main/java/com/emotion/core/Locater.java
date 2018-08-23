package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 18 FEB 2015
 *	Class to implement element locater common functions. Methods in this class contains page object repository reference
 *	and retrieves element locater and by. Also, Using switch case element is located. 
 */

import org.openqa.selenium.By;

public class Locater {
	private String by;
	private String ElementLocater;

	private PageObjectRepository repository = new PageObjectRepository();
		
	//Web Page Elements functions 
	/**
	 * Method to get element locate by
	 * @return Element locate by as @String
	 */
	public String getBy() {
		return this.by;
	}
	/**
	 * Method to set element locate by
	 * @param By @By locate by for e.g. id, xpath etc..
	 */
	private void setLocateBy(String By) {
		this.by = By;
	}
	/**
	 * Method to set element locater
	 * @param elementLocater Element locater
	 */
	private void setElementLocater(String elementLocater) {
		this.ElementLocater = elementLocater;
	}
	/**
	 * Method to get element locater 
	 * @return Element locater as @String
	 */
	public String getElementLocater() {
		return this.ElementLocater;
	}
	
	/**
	 * Method to retrieve element locater and by from page object repository 
	 * @param ElementTag Element tag from @ElementTag 
	 */
	public void getElementLocater(String ElementTag)
	{
		setElementLocater(repository.getElmentValue(ElementTag)); 
		setLocateBy(repository.getAttribute());
	}
	/**
	 * Method of locating element with only Element Tag know from repository
	 * @param ElementTag Element Tag form @ElementTag
	 * @return Selenium By class object
	 */
	protected By locater(String ElementTag) {
		getElementLocater(ElementTag);
		return locater(getElementLocater(), getBy());
	}
	/**
	 * Method to retrieve element with Element locater and by 
	 * @param ElementLocater Element Locater
	 * @param by Element Locate by @By
	 * @return Selenium By class object
	 */
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
	public void checkAlertDeleteCaptionType() {
		// TODO Auto-generated method stub
		
	}
	public void metadataAddfieldCaptioType(String label, String description,
			String method, String size, String sortable, String metadatatype,
			String Metadataproperties, String enterdefault) {
		// TODO Auto-generated method stub
		
	}
}
