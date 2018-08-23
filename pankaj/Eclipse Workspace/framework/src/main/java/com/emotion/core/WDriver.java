package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 12 NOV 2014
 *	Class contains sub classes to initialize different browser drivers.
 */

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

abstract class WDriver 
{
	protected abstract WebDriver driver();
	protected DesiredCapabilities dc = null;
}

class Firefox extends WDriver
{
	@Override
	protected WebDriver driver() {
		Log.info("Starting Firefox driver...");
		dc= DesiredCapabilities.firefox();
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		firefoxProfile.setAcceptUntrustedCertificates(true);
		firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
		firefoxProfile.setPreference("browser.download.folderList", 2);
		firefoxProfile.setPreference("browser.download.dir",
				System.getProperty("user.dir")+"\\testdata\\Downloads\\");
		firefoxProfile.setPreference(
				"browser.helperApps.neverAsk.saveToDisk", "video/mp4,text/plain");
		dc.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
		return new FirefoxDriver(dc);
	}
}

class IE extends WDriver
{
	private void setIEDriverPath()
	{
		//Changed code to start IEdriver for 32bit even for 64 bit machines as it works slow for IE10. Date : 30 Sep 14
		File file =null;
        if (new File("C:\\Program Files (x86)").isDirectory()) {
        	file= new File(System.getProperty("user.dir") + File.separator + "src\\main\\resources" + File.separator + "IEDriverServer_x86.exe");
        } else {
        	file= new File(System.getProperty("user.dir") + File.separator + "src\\main\\resources" + File.separator + "IEDriverServer_x86.exe");
        }
        System.setProperty("webdriver.ie.driver",file.getAbsolutePath());
	}

	@Override
	protected WebDriver driver() {
		Log.info("Starting Internet Explorer driver...");
		setIEDriverPath();
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);
		dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
		dc.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
		dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
		dc.setJavascriptEnabled(true); 
		return new InternetExplorerDriver(dc);
	}
	
}

class Chromium extends WDriver
{
	@Override
	protected WebDriver driver() {
		Log.info("Starting chrome driver...");
		File file =null;
		file= new File(System.getProperty("user.dir") + File.separator + "src\\main\\resources" + File.separator + "chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
		return new ChromeDriver();
	}
}
