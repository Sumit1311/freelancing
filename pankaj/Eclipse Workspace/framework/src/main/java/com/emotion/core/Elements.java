package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 FEB 2015
 *	Interface to define element find methods
 */

import java.util.List;

public interface Elements 
{
	/**
	 * Method to find element using only element tag from the @ElementTag class
	 * @param ElementTag element tag from the @ElementTag class
	 * @return Instance of the class where method or method containing interface is implemented. 
	 */
	PageElements getElement(String ElementTag);
	/**
	 * Method to find multiple elements using only element tag from the @ElementTag class
	 * @param ElementTag element tag from the @ElementTag class
	 * @return Instance of the class where method or method containing interface is implemented.
	 */
	List<PageElements> getElements(String ElementTag);
	/**
	 * Method to find element using its locater and locate by
	 * @param locater Element locater id, xpath etc..
	 * @param By Element locate by from @By
	 * @return Instance of the class where method or method containing interface is implemented.
	 */
	PageElements getElement(String locater,String By);
	/**
	 * Method to find multiple elements using its locater and locate by
	 * @param locater Element locater id, xpath etc..
	 * @param By Element locate by from @By
	 * @return Instance of the class where method or method containing interface is implemented.
	 */
	List<PageElements> getElements(String locater,String By);
}
