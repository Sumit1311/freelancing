package com.emotion.lib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class MyOrderHistory extends LoadableComponent<MyOrderHistory> {
	
	
	@FindBy (name="order_query")
	@CacheLookup
	public WebElement bttnRecentsOrder;
	
	@FindBy (partialLinkText="deliver")
	@CacheLookup
	public WebElement linkdeliver;
	
		
	WebDriver driver =null;
    public MyOrderHistory(WebDriver driver) {
		this.driver =  driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		get();
	}
	
	@Override
	protected void isLoaded() throws Error {
		//assertEquals(driver.getTitle(), "Media Management On Demand -  My order History");
		
	}
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
	public  Selectdeliverymethodpage myOrder() {
		bttnRecentsOrder.click();
		//linkdeliver.click();
		driver.findElement(By.xpath("//a[text()='Deliver']")).click();
		return new Selectdeliverymethodpage(driver);
		
	}  
   }






