package com.emotion.tests;
import static org.testng.Assert.*;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import com.emotion.core.TestAssert;
import com.emotion.external.ElementTag;
import com.emotion.lib.HomePage;
import com.emotion.lib.MMOD;

//
import com.emotion.external.Fixtures;

public class POTest extends Fixtures {
	HomePage homePage =null;
		
	@BeforeClass
	  public void setUp() {
		setupFixture("Login for PO Test");
		homePage = new HomePage(browser.driver());
	  }
	
	@Test
	  public void f() {
		homePage.goToAdmin().goToCaptionTypeManagerPage();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(browser.driver().getTitle(),"Media Management On Demand - Caption Type Manager");
		
	  }
	
	@Test
	  public void f2() {
		homePage.goToAdmin().goToRedeliverShipmentPage();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(browser.driver().getTitle(),"Media Management On Demand - Caption Type Manager");
		
	  }
	  
	  @AfterClass
	  public void afterClass() {
		  tearDownFixture();
	  }

	}
