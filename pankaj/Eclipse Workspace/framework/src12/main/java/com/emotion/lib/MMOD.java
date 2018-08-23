package com.emotion.lib;

import com.emotion.core.Browser;
import com.emotion.core.Log;
import com.emotion.external.ElementTag;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 05 DEC 2014
 */

public class MMOD implements IMMOD
{
	private Browser browser = null;
	public MMOD(Browser browser) {
		this.browser= browser;
	}

	public void goToCollectionManagerPage()
	{
		Log.info("Navigate to Collection Manager Page.");
		browser.getElement(ElementTag.linkAdmin).click();
		browser.getElement(ElementTag.linkCollectionManager).click();
	}
	
	@Override
	public void goToHomePage() {
		browser.getElement(ElementTag.linkHome).click();
	}

	@Override
	public IAuthentication authentication() {
		return new Authentication(browser);
	}
	
	@Override
	public ICollectionManager manageCollection() {
		return new CollectionManager(browser);
	}

	@Override
	public ICollection collection() {
		return new Collection(browser);
	}


}

