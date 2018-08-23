package com.emotion.lib;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.emotion.core.Browser;
import com.emotion.core.Log;
import com.emotion.core.PageElements;
import com.emotion.external.ElementTag;

public class RedeliverShipmentPage extends LoadableComponent<HomePage> {
	
	private Browser browser = null;
	private MMOD mmod = null;
	public RedeliverShipmentPage(Browser browser) {
		this.browser=browser;
		mmod = new MMOD(browser);
	}
	
	@FindBy (id="SelectAllButtonTop")
	@CacheLookup
	public WebElement bttnSelectAllButton;
	
	@FindBy (partialLinkText="Re-deliver")
	@CacheLookup
	public WebElement linkReDeliver;
	
	@FindBy (xpath="//b")
	@CacheLookup
	public WebElement NoFailureMsg;
	
	
	/*@FindBy (xpath="//input[@ type='checkbox']")
	@CacheLookup
	public WebElement CancelMsg;*/
	
	WebDriver driver =null;
	public RedeliverShipmentPage(WebDriver driver){
		this.driver =  driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		get();
	}	
		
	@Override
	protected void isLoaded() throws Error {
		assertEquals(driver.getTitle(), "Media Management On Demand - Redeliver Shipment");
		}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		}
	public String reDeliver(){
		
		String message = NoFailureMsg.getText();
		if(driver.getPageSource().contains(Messages.RedeliverShipment)){
		Log.info("Message displayed on the Screen is: "+ message);
		return "test";
		}
		else 
			 bttnSelectAllButton.click();
		//linkReDeliver.click();
			//Thread.sleep(3000);
		
		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@ type='checkbox']"));
		int totalOrderstoRedeliver = allCheckboxes.size();
		Log.info("Total orders to re-deliver = " + totalOrderstoRedeliver );
		
			driver.findElement(By.xpath("//a[text()='Re-deliver']")).click();
		    Alert alert=driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.dismiss();
			
			List<WebElement> OrdersAfterCancel = driver.findElements(By.xpath("//input[@ type='checkbox']"));
			int orders = OrdersAfterCancel.size();
			if (totalOrderstoRedeliver == orders)
				Log.info("Total orders after CANCELLING redeliver are the same = " + orders );
			else
				Log.info("Total orders after CANCELLING are not same = " + orders );
			
			try {
				Thread.sleep(9000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElement(By.xpath("//a[text()='Re-deliver']")).click();
			Alert alert1=driver.switchTo().alert();
			System.out.println(alert1.getText());
			alert1.accept();
			
			return "Redelivering....";	
			
	}
	private String getText(WebElement cancleMsg2) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getText(Alert alert) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getText() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
