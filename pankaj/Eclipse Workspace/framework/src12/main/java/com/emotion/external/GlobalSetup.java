package com.emotion.external;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 12 NOV 2014
 */

import com.emotion.core.Browser;
import com.emotion.core.Log;
import com.emotion.core.WebBrowser;

public class GlobalSetup 
{
	private boolean setup = false;
	private Browser browser = null;
	@SuppressWarnings("unused")
	private Log log = new Log();
	public void GlobalSetupMethod()
	{
		if(isSetup()==false)
		{
			Log.info("Framework logging Started...");
			Log.info("Running GlobalSetup Method...");
			setBrowser(new WebBrowser());
			Log.info("Completed GlobalSetup Method...");
			setSetup(true);
		}else
		{
			setBrowser(new WebBrowser());
		}
	}
	
	public Browser getBrowser() {
		GlobalSetupMethod();
		return browser;
	}
	
	private void setBrowser(Browser browser) {
		this.browser = browser;
	}

	private boolean isSetup() {
		return setup;
	}

	private void setSetup(boolean setup) {
		this.setup = setup;
	}

}
