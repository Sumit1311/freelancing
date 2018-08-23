package com.emotion.lib;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.emotion.core.By;
import com.emotion.core.Log;

public class OnlinedeliveryPage extends LoadableComponent<OnlinedeliveryPage> { 

	@FindBy (name="order_query")
	@CacheLookup
	public WebElement bttnRecentsOrder;
	
	@FindBy (xpath="//a[text()='Deliver']")
	@CacheLookup
	public WebElement linkdeliver;
	
	/*@FindBy (partialLinkText="deliver")
	@CacheLookup
	public WebElement linkdeliver;*/
	
	@FindBy (xpath=".//*[@id='m_PanelMain']/table/tbody/tr/td/form/ul/table/tbody/tr[4]/td[1]/input")
	@CacheLookup
	public WebElement onlineDeli;
	
	@FindBy (name="go")
	@CacheLookup
	public WebElement bttnContinue;
	
	@FindBy (name="search_results_deliveryformat__0")
	@CacheLookup
	public WebElement selectdeliveryFormat;
	
	@FindBy (name="go")
	@CacheLookup
	public WebElement bttnordercontinue;
	
	@FindBy (xpath="//h4")
	@CacheLookup
	public WebElement DetailsshipmentMSG;
	
	@FindBy (xpath=".//*[@id='m_PanelMain']/table/tbody/tr/td/table/tbody/tr/td/form/table[1]/tbody/tr[1]/td[2]")
	@CacheLookup
	public WebElement completeStatus;
	
	
	
	WebDriver driver =null;
    public OnlinedeliveryPage(WebDriver driver) {
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

public boolean onlinedelivery(String Deliveryformat){
	
	bttnRecentsOrder.click();
    linkdeliver.click();
	//driver.findElement(By.xpath("//a[text()='Deliver']")).click();
	onlineDeli.click();
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	bttnContinue.click();
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	Select df = new Select(selectdeliveryFormat);
	df.selectByValue(Deliveryformat);
	bttnordercontinue.click();
	try {
		Thread.sleep(18000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String message = DetailsshipmentMSG.getText();
	Log.info("Message displayed on the Screen is: "+ message);
	
	String complete = completeStatus.getText();
	//String message = DetailsshipmentMSG.getText();
	try {
		Thread.sleep(200000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if (driver.getPageSource().contains(complete)){
		Log.info("Details for shipment Status : " + complete);
		return true;		
	}	
	else
		return false;
	}
}













