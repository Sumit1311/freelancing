package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 20 NOV 2014
 */

import java.util.List;

public interface Elements 
{
	PageElements getElement(String ElementTag);
	List<PageElements> getElements(String ElementTag);
	PageElements getElement(String locater,String By);
	List<PageElements> getElements(String locater,String By);
}
