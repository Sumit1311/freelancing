package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 FEB 2015
 *	Interface to define expected conditions for explicitly wait for Webdriver
 */

public interface ExpectedCondition
{
	/**
	 * Use this when element needs to be present & can be clicked
	 */
	public static final String elementToBeClickable= "elementToBeClickable";
	/**
	 * Use this when element invisibility condition needs to expected
	 */
	public static final String invisibilityOfElementLocated= "invisibilityOfElementLocated";
	/**
	 * Use this condition when element needs to selected
	 */
	public static final String elementToBeSelected= "elementToBeSelected";
	/**
	 * Use this condition when element must be present 
	 */
	public static final String presenceOfElementLocated= "presenceOfElementLocated";
	/**
	 * Use this when element is invisible and with certain text needs to present
	 */
	public static final String invisibilityOfElementWithText= "invisibilityOfElementWithText";
	/**
	 * Use this when element is present with specific text
	 */
	public static final String textToBePresentInElementLocated= "textToBePresentInElementLocated";
	/**
	 * Use this when element visibility condition needs to expected
	 */
	public static final String visibilityOfElementLocated= "visibilityOfElementLocated";
	/**
	 * Use this when certain text needs to present in element value attribute
	 */
	public static final String textToBePresentInElementValue= "textToBePresentInElementValue";
	/**
	 * Use this for visibility of all the elements needs to located on page.
	 */
	public static final String visibilityOfAllElementsLocatedBy= "visibilityOfAllElementsLocatedBy";
	/**
	 * Use this for presence of all the elements needs to located on page.
	 */
	public static final String presenceOfAllElementsLocatedBy= "presenceOfAllElementsLocatedBy";
}
