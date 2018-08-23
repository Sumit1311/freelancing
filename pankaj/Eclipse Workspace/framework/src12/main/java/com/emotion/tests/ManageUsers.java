package com.emotion.tests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.emotion.core.TestAssert;
import com.emotion.external.ElementTag;
import com.emotion.external.Fixtures;
import com.emotion.util.ExcelUtil;

public class ManageUsers extends Fixtures
{
	private static final String mpadmin = null;


	@BeforeClass
	public void setUp(){
		setupFixture("Manage Users");
	}
	
	@Test(description = "TEST ID : 1 | manageUser ", priority=1)
	public void manageUser()
	{
		browser.getElement(ElementTag.linkAdmin).click();
		browser.getElement(ElementTag.linkManageUsers).click();
		}
	@Test(description = "TEST ID : 2 | manageUser", priority=2)
	public void username()
	{
		browser.getElement(ElementTag.txtUsername).clear();
		browser.getElement(ElementTag.txtUsername).sendKeys("persistent");
	}
	@Test(description = "TEST ID : 3 | manageUser ", priority=3)
	public void Search()
	{
		browser.getElement(ElementTag.bttnSearch).click();
	}
	@AfterClass
	public void tearDown()
	{
		tearDownFixtureWithoutLogin();
	}
    
}
	


