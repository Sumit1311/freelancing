package com.emotion.lib;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

//import com.emotion.core.PageElements;
//import com.emotion.core.Select;//
//import com.emotion.external.ElementTag;
import org.openqa.selenium.support.ui.Select;

import com.emotion.core.Log;

public class Detailsdeliverymethodpage extends LoadableComponent<Detailsdeliverymethodpage>{
	WebDriver driver =null;
	
	
	@FindBy (name="shiptoname")
	@CacheLookup
	public WebElement textShipto;
	
	@FindBy (name="shiptoorganizationname")
	@CacheLookup
	public WebElement textOrganization;
	
	@FindBy (name="shiptoaddress1")
	@CacheLookup
	public WebElement textAddress;
	
	@FindBy (name="shiptocity")
	@CacheLookup
	public WebElement textCity;
	
	@FindBy (name="shiptostate")
	@CacheLookup
	public WebElement textState;
	
	
	@FindBy (name="shiptocountry")
	@CacheLookup
	public WebElement selectCountry;
	
	@FindBy (name="shiptozipcode")
	@CacheLookup
	public WebElement textZipCode;
	
	@FindBy (name="shiptoauxiliarypostalcode")
	@CacheLookup
	public WebElement textAuxiliaryPostalCode;
	
	@FindBy (name="shiptocarriercode")
	@CacheLookup
	public WebElement textCarrierCode;
	
	@FindBy (name="shiptocustomerpo")
	@CacheLookup
	public WebElement textCustomerPO;
	
	@FindBy (name="shiptospecialinstructions1")
	@CacheLookup
	public WebElement textSpecialinstructions;
	
	@FindBy (name="search_results_deliveryformat__0")
	@CacheLookup
	public WebElement selectdeliveryFormat;
	
	
	@FindBy (name="go")
	@CacheLookup
	public WebElement bttnContinue;
	
	@FindBy (xpath="//h4")
	@CacheLookup
	public WebElement DetailsshipmentMSG;
	
	@FindBy (xpath=".//*[@id='m_PanelMain']/table/tbody/tr/td/table/tbody/tr/td/form/table[1]/tbody/tr[1]/td[2]")
	@CacheLookup
	public WebElement completeStatus;
	
    public Detailsdeliverymethodpage(WebDriver driver) {
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
		// TODO Auto-generated method stub
		
	}
	public boolean detailsDeliveryType(String Shipto,String Organization,String Address,String City,String State,String Country,String ZipCode,String AuxiliaryPostalCode,String CarrierCode,String CustomerPO,String Specialinstructions,String deliveryFormat){
	
		textShipto.clear();
		textShipto.sendKeys(Shipto);
		textOrganization.clear();
		textOrganization.sendKeys(Organization);
		textAddress.sendKeys(Address);
		textCity.sendKeys(City);
		textState.sendKeys(State);
	    Select se = new Select(selectCountry);
		se.selectByValue(Country);
		//selectCountry.findElements(by Country);
		textZipCode.sendKeys(ZipCode);
		textAuxiliaryPostalCode.sendKeys(AuxiliaryPostalCode);
		textCarrierCode.sendKeys(CarrierCode);
		textCustomerPO.sendKeys(CustomerPO);
		textSpecialinstructions.sendKeys(Specialinstructions);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Select df = new Select(selectdeliveryFormat);
		df.selectByValue(deliveryFormat);
	try {
		Thread.sleep(6000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
	bttnContinue.click();
	try {
		Thread.sleep(6000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String message = DetailsshipmentMSG.getText();
	Log.info("Message displayed on the Screen is: "+ message);
	
	String complete = completeStatus.getText();
	//String message = DetailsshipmentMSG.getText();
	try {
		Thread.sleep(210000);
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
	
