package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 FEB 2015
 *	Interface to define elements locate by types 
 */

public interface By {
	/**
	 * Use if element is to be located using xpath
	 */
	public static String xpath="xpath";
	/**
	 * Use if element is to be located using id
	 */
	public static String id="id";
	/**
	 * Use if element is to be located using css
	 */
	public static String css="css";
	/**
	 * Use if element is to be located using class name
	 */
	public static String classname="classname";
	/**
	 * Use if element is to be located using name
	 */
	public static String name="name";
	/**
	 * Use if element is to be located using tag name
	 */
	public static String tagname="tagname";
	/**
	 * Use if element is to be located using link text
	 */
	public static String linktext="linktext";
	/**
	 * Use if element is to be located using substring from link text or a part of link is known
	 */
	public static String partiallinktext="partiallinktext";
	org.openqa.selenium.By xpath(String string);
}
