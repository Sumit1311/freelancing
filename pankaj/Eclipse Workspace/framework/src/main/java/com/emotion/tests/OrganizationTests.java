package com.emotion.tests;
/**
 *	@author bhavna_karanjekar
 *	@version 1.0
 *	Last Updated: 5 May 2015
 */
import java.util.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.emotion.core.SoftAssert;
import com.emotion.core.TestAssert;
import com.emotion.core.TestStatus;
import com.emotion.external.ElementTag;
import com.emotion.external.Fixtures;
import com.emotion.lib.IMMOD.IOrganization;
import com.emotion.util.ExcelUtil;

public class OrganizationTests extends Fixtures{
	private static IOrganization organization = null;
	private SoftAssert softassert = new SoftAssert();
	private TestAssert tassert = new TestAssert();


	@BeforeClass
	public void setUp(){		
		setupFixture("Login as CreativeAuto for Manage Organization Tests");
		organization = mmod.manageOrganization();
		
	}
	
  	@DataProvider(name = "Organiztion_Details")  
   	public Object[][] addNewOrganization() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Organization Manager\\Organization_Details.xls",
                "New_Organization_Details", "addNewOrganizationData");
        return(retObjArr);
    }
	
	@Test(description = "TEST ID : 1 | verifyAddOrganization ", priority=1, dataProvider="Organiztion_Details")
	public void verifyAddOrganization(String name,String pointofContactFirst,String pointofContactMiddle, String pointofContactLast,
			String email,String phone,String fax,String billingAddress,String city,String state,String zip,
			String country,String shippingAddress,String city1,String state1,String zip1,String country1)
	{
		mmod.goToManageOrganizationPage();	
		browser.getElement(ElementTag.bttnAddNewOrganization).click();
		
		organization.addNewOrganization(name, pointofContactFirst, pointofContactMiddle, pointofContactLast,
				email, phone, fax, billingAddress, city, state, zip, country, shippingAddress, city1, state1, zip1, country1);
	
	
	//verify if this organization exists
	mmod.goToManageOrganizationPage();
	boolean isPresent = mmod.isTextPresentOnPage(name);
	tassert.assertTrue(isPresent, "FAILED | TEST ID : 1 | verify Add Organization");
	TestStatus.pass("PASSED | TEST ID : 2 | verify Add Organization");

	}
	@AfterClass
	public void tearDown()
	{
		tearDownFixtureWithoutLogin();
	}

}
