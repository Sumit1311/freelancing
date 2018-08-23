package com.emotion.lib;

import static org.testng.Assert.assertEquals;

import java.util.List;
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

public class AddtoprojectPage extends LoadableComponent<AddtoprojectPage> { 

	@FindBy (name="order_query")
	@CacheLookup
	public WebElement bttnRecentsOrder;
	
	@FindBy (xpath="//a[text()='Deliver']")
	@CacheLookup
	public WebElement linkdeliver;
	
	@FindBy (name="project_id")
	@CacheLookup
	public WebElement selectProject;
	
	@FindBy (xpath=".//*[@id='m_PanelMain']/table/tbody/tr/td/form/ul/table/tbody/tr[1]/td[1]/input")
	@CacheLookup
	public WebElement addToProject;
	
	@FindBy (name="go")
	@CacheLookup
	public WebElement bttnContinue;
	
	@FindBy (xpath="//td [contains(text(),'.')]")
	@CacheLookup
	public WebElement assetName;
	

	WebDriver driver =null;
    public AddtoprojectPage(WebDriver driver) {
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

public boolean addtoproject(String project)
{
	bttnRecentsOrder.click();
    linkdeliver.click();
    addToProject.click();
    //selectProject.click();
    Select sp = new Select(selectProject);
    //List<WebElement> allOptions = sp.getOptions();
    sp.selectByVisibleText(project);
	//sp.selectByValue(project);
	
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	bttnContinue.click();
	try {
		Thread.sleep(6000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	//String assets = addassets.getAttribute();
	String assetAdded = assetName.getText();
	//String message = DetailsshipmentMSG.getText();
	
	if (driver.getPageSource().contains(assetAdded)){
		Log.info("Assets added successfully : " + assetAdded);
		return true;		
	}	
	else
		return false;
}



}









