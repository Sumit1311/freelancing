package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 15 DEC 2014
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.emotion.external.Configuration;

public class WebBrowser extends Locater implements Browser,Browser.HandleWindows,Browser.ManageWait
{
	private WebDriver driver;
	
	public WebBrowser(){
		intializeWebDriver(Configuration.getProperty(Configuration.BROWSER));
	}
	
	public WebBrowser(String BrowserTypes) {
		intializeWebDriver(BrowserTypes);
	}
	
	public WebBrowser(String BrowserTypes,String url) {
		intializeWebDriver(BrowserTypes);
		this.driver.manage().window().maximize();
		this.driver.navigate().to(url);
	}
	
	private WebDriver intializeWebDriver(String Browser)
	{
		Log.info("Starting Webdriver... " );
		if (Browser.equalsIgnoreCase(BrowserTypes.FireFox))
		{
			driver = new Firefox().driver();
		}else if(Browser.equalsIgnoreCase(BrowserTypes.InternetExplorer))
		{
			driver =  new IE().driver();
		}else if(Browser.equalsIgnoreCase(BrowserTypes.Chrome))
		{
			driver = new Chromium().driver();
		}
		Log.info("Webdriver initialized...");
		return driver;
	}

	@Override
	public WebDriver driver() {
		return this.driver;
	}
	
	@Override
	public void quit() {
		this.driver.quit();
	}
	
	@Override
	public void refresh() {
		this.driver.navigate().refresh();		
	}
	
	@Override
	public String getPageSource() {
		return this.driver.getPageSource();
	}
	
	@Override
	public void maximize() 
	{
		this.driver.manage().window().maximize();
	}
		
	@Override
	public PageElements getElement(String ElementTag) {
		if (ElementTag != null)
		{
			PageElements ele =  new PageElement();
			ele.setElement(driver.findElement(locater(ElementTag)));
			return ele;
		}
		throw new FrameworkException("Invalid Element Tag : " + ElementTag);
	}
	
	@Override
	public PageElements getElement(String locater,String By)
	{
		if (locater != null)
		{
			PageElements ele =  new PageElement();
			ele.setElement(driver.findElement(locater(locater,By)));
			return ele;
		}
		throw new FrameworkException("Invalid locater : " + locater);
	}
	
	@Override
	public List<PageElements> getElements(String locater,String By) {
		if (locater != null)
		{
			List<WebElement> list = driver.findElements(locater(locater,By));
			List<PageElements> list2 = new ArrayList<PageElements>();
			
			for (WebElement webElement : list) {
				PageElements ele =  new PageElement();
				ele.setElement(webElement);
				list2.add(ele);
				ele=null;
			}
			return list2;
		}
		throw new FrameworkException("Invalid Element Tag : " + locater);
	}
	
	@Override
	public List<PageElements> getElements(String ElementTag) {
		if (ElementTag != null)
		{
			List<WebElement> list = driver.findElements(locater(ElementTag));
			List<PageElements> list2 = new ArrayList<PageElements>();
			for (WebElement webElement : list) {
				PageElements ele =  new PageElement();
				ele.setElement(webElement);
				list2.add(ele);
				ele=null;
			}
			return list2;
		}
		throw new FrameworkException("Invalid Element Tag : " + ElementTag);
	}
	
	
	//handle windows, alerts & frames
	
	@Override
	public HandleWindows switchTo() {
		return this;
	}
		
	public String getBaseWindowHandle()
	{
		return this.driver.getWindowHandle();
	}

	@Override
	public void window(String title) {
		String basewindowhandle= getBaseWindowHandle();
		Set<String> WindowHandles= driver.getWindowHandles();
		 for (String handle : WindowHandles) {
			 driver.switchTo().window(handle);
			if (basewindowhandle.equals(getBaseWindowHandle()))
			{
				 if (driver.getTitle().equals(title))
				 {
					 break;
				 }
			}
		 }
	}

	@Override
	public void closeWindow(String BaseWindowHandle) {
		if (!BaseWindowHandle.equals(getBaseWindowHandle()))
		{
			this.driver.close();
			this.driver.switchTo().window(BaseWindowHandle);
		}
	}

	@Override
	public void parentWindow(String ParentWindowHandle) 
	{
		this.driver.switchTo().window(ParentWindowHandle);
	}

	@Override
	public void popUpWindow() {
		String basewindowhandle= getBaseWindowHandle();
		this.driver.switchTo().window(driver.getWindowHandles().iterator().next().toString());
		if (basewindowhandle.equals(getBaseWindowHandle()))
		{
			TestStatus.fail("Popup window was not present");
		}
	}

	@Override
	public Alert alert() {
		return this.driver.switchTo().alert();
	}

	@Override
	public void frame(String locater) {
		this.driver.switchTo().frame(locater);
	}

	@Override
	public void frame(int index) {
		this.driver.switchTo().frame(index);
	}

	//Wait functions
	
	@Override
	public ManageWait manageWait() {
		return this;
	}

	@Override
	public void implicitlyWait() {
		implicitlyWait(10);
	}

	@Override
	public void threadWait(int seconds) {
		try {
			Thread.sleep((seconds*1000));
		} catch (InterruptedException e) {
			Log.debug(e.getMessage());
		}
	}

	@Override
	public void implicitlyWait(int timeInSeconds) {
		Log.debug("Webdriver implicit wait set to " + timeInSeconds + " Seconds");
		this.driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
	}

	@Override
	public Object explicitlyWait(String ElementTag, String By,
			String ExpectedCondition, int timeInSeconds, String Texttobepresent) {
		Log.debug("Webdriver explicit wait for " + timeInSeconds + " Seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		Object element =null;
		By Locater = null;
		if (By==null)
		{
			Locater =locater(ElementTag);
			By =  getBy();
		}else
		{
			Locater = locater(ElementTag,By);
		}
		
		try
		{
			switch (ExpectedCondition)
			{
			case "elementToBeClickable":
				element = wait.until(ExpectedConditions.elementToBeClickable(Locater));
				break;
			case "invisibilityOfElementLocated":
				element = wait.until(ExpectedConditions.invisibilityOfElementLocated(Locater));
				break;
			case "elementToBeSelected":
				element = wait.until(ExpectedConditions.elementToBeSelected(Locater));
				break;
			case "presenceOfElementLocated":
				element = wait.until(ExpectedConditions.presenceOfElementLocated(Locater));
				break;
			case "invisibilityOfElementWithText":
				element = wait.until(ExpectedConditions.invisibilityOfElementWithText(Locater, Texttobepresent));
				break;
			case "textToBePresentInElementLocated":
				element = wait.until(ExpectedConditions.textToBePresentInElementLocated(Locater, Texttobepresent));
				break;
			case "visibilityOfElementLocated":
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(Locater));
				break;
			case "textToBePresentInElementValue":
				element = wait.until(ExpectedConditions.textToBePresentInElementValue(Locater, Texttobepresent));
				break;
			case "visibilityOfAllElementsLocatedBy":
				element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locater));
				break;
			case "presenceOfAllElementsLocatedBy":
				element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locater));
				break;
			}
		}catch(Exception e)
		{
			TestStatus.fail("Unable to find element '" + Locater  + "' by '" + By +"'");
		}
		return element;
	}

	@Override
	public Object explicitlyWait(String ElementTags, String ExpectedCondition,
			int timeInSeconds, String Texttobepresent) {
		return explicitlyWait(ElementTags, null, ExpectedCondition, timeInSeconds, Texttobepresent);
	}
}

