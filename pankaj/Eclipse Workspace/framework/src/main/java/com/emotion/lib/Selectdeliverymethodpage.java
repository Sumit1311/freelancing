package com.emotion.lib;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class Selectdeliverymethodpage extends LoadableComponent<Selectdeliverymethodpage>{
	
	@FindBy (name="deliverymethod")
	@CacheLookup
	public WebElement CDROMdelivery;
	
	@FindBy (name="go")
	@CacheLookup
	public WebElement bttnContinue;
	
	@FindBy (name="go")
	@CacheLookup
	public WebElement bttnordercontinue;
	
	
	
	WebDriver driver =null;
    public Selectdeliverymethodpage(WebDriver driver) {
		this.driver =  driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		get();
	}
	
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		//assertEquals(driver.getTitle(), "Media Management On Demand -  Select Delivery Method");
		
	}
	public Detailsdeliverymethodpage selectDeliveryType() {
		//CDROMdelivery.click();
		driver.findElement(By.xpath(".//*[@id='m_PanelMain']/table/tbody/tr/td/form/ul/table/tbody/tr[2]/td[1]/input")).click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bttnContinue.click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Detailsdeliverymethodpage(driver);
		}
	

}
