package com.emotion.framework.tests;

import com.emotion.core.Browser;
import com.emotion.core.BrowserTypes;
import com.emotion.core.By;
import com.emotion.core.Log;
import com.emotion.core.WebBrowser;

public class TestMainClass {

	public static void main(String[] args) {
		Log l = new Log();
		Browser wb = new WebBrowser(BrowserTypes.InternetExplorer);
		wb.manageWait().implicitlyWait(20);
		wb.driver().get("http://beta.mediapartner.com");
		wb.getElement("username", By.name).sendKeys("mpadmin");
		wb.getElement("password", By.name).sendKeys("password");
		wb.getElement("enter", By.id).click();
		
		/*		Map<String, String> userDetails = new HashMap<String, String>();
		userDetails.put("Login", "TestAutoUser1");*/
		//wb.quit();
	}
}
