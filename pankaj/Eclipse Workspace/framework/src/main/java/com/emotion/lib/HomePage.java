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
public class HomePage extends LoadableComponent<HomePage>{
	
	WebDriver driver =null;
	
	@FindBy (linkText="Admin")
	@CacheLookup
	public WebElement linkAdmin;
	
	
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
		get();
	}	
	
	
	@Override
	protected void isLoaded() throws Error {
		assertEquals(driver.getTitle(), "Media Management On Demand - Home");
		
	}

	@Override
	protected void load() {
		
	}
	
	public AdminPage goToAdmin(){
		linkAdmin.click();
		AdminPage admin = new AdminPage(driver);
		return admin;
	}
	
	

	

}
