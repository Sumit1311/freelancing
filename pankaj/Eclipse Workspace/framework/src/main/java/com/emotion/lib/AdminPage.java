
package com.emotion.lib;

import java.util.concurrent.TimeUnit;

import com.emotion.external.ElementTag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.*;
public class AdminPage extends LoadableComponent<AdminPage>{
	
	@FindBy (partialLinkText="Caption Type Manager")
	@CacheLookup
	public WebElement linkCaptionTypeManager;
	
	@FindBy (partialLinkText="Redeliver Shipment")
	@CacheLookup
	public WebElement linkRedeliverShipment;
	
	@FindBy (partialLinkText="My Order History")
	@CacheLookup
	public WebElement linkMyOrderHistory;
	
	/*@FindBy (partialLinkText="My Order History")
	@CacheLookup
	public WebElement linkMyOrderHistory;*/
	
	/*@FindBy (partialLinkText="Continue")
	@CacheLookup
	public WebElement linkContinue;*/
	
	
	WebDriver driver =null;
	public AdminPage(WebDriver driver){
		this.driver =  driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		get();
	}	
	
	@Override
	protected void isLoaded() throws Error {
		assertEquals(driver.getTitle(), "Media Management On Demand - Administration");
		
	}

	@Override
	protected void load() {
		
	}
	
	public void goToCaptionTypeManagerPage(){
		linkCaptionTypeManager.click();
		}
	public RedeliverShipmentPage goToRedeliverShipmentPage(){
		linkRedeliverShipment.click();
        return new RedeliverShipmentPage(driver);
	    }
	public MyOrderHistory gotoMyOrderHistory(){
	 linkMyOrderHistory.click();
		return new MyOrderHistory(driver);
		}
	public Searchmyorderhistorypage gotoSearchmyorderhistorypage(){
		linkMyOrderHistory.click();
		return new Searchmyorderhistorypage(driver);
	}
	public OnlinedeliveryPage gotoOnlinedeliveryPage(){
		linkMyOrderHistory.click();
		return new OnlinedeliveryPage(driver);
	}
	public SourcedeliveryPage gotoSourcedeliveryPage(){
		linkMyOrderHistory.click();
		return new SourcedeliveryPage(driver);
    }
	public AddtoprojectPage gotoAddtoprojectPage(){
		linkMyOrderHistory.click();
		return new AddtoprojectPage(driver);
    }
}
