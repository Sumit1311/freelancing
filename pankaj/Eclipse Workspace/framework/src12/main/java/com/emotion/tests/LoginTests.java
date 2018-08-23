package com.emotion.tests;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 20 NOV 2014
 */

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.emotion.core.TestAssert;
import com.emotion.core.TestStatus;
import com.emotion.external.Fixtures;
import com.emotion.lib.PageTitles;
import com.emotion.util.ExcelUtil;

public class LoginTests extends Fixtures
{
	private TestAssert tassert = new TestAssert();
			
   	@DataProvider(name = "Credentials")  
   	public Object[][] createData() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Login\\Login_TestData.xls",
                "Login_TestData", "testdata");
        return(retObjArr);
    }
	
	
	@BeforeClass
	public void setUp()
	{
		setupFixtureWithoutLogin("Login Test class");
	}
	
	@Test(description = "TEST ID : 1 | Authentication Test", priority=1, dataProvider="Credentials")
	public void verifyInvalidLogins(String username, String password)
	{
		mmod.authentication().login(username,password);
		tassert.assertEquals(browser.driver().getTitle(), PageTitles.Login,
				"FAILED | TEST ID : 1 | User is logged in with invalid crediantials.");
		TestStatus.pass("PASSED | TEST ID : 1 | User is not able to login with invalid crediantials");
	}
	
	@Test(description = "TEST ID : 2 | Authentication Test", priority=2)
	public void verifyValidLogin()
	{
		mmod.authentication().login();
		tassert.assertEquals(browser.driver().getTitle(), PageTitles.Home,
				"FAILED | TEST ID : 2 | User is not logged in with valid crediantials.");
		TestStatus.pass("PASSED | TEST ID : 2 | User is able to login with valid crediantials.");
	}
	
	@AfterClass
	public void tearDown()
	{
		tearDownFixtureWithoutLogin();
	}
}
