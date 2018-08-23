package com.emotion.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.emotion.core.By;
import com.emotion.core.Log;
import com.emotion.core.TestAssert;
import com.emotion.core.TestStatus;
import com.emotion.external.Fixtures;
import com.emotion.lib.HomePage;
import com.emotion.lib.IMMOD.ICaptionType;
import com.emotion.util.ExcelUtil;

import static org.testng.Assert.*;

public class OrderManagementTests extends Fixtures {
	private TestAssert tassert = new TestAssert();
	
	HomePage homePage =null;
		
		@BeforeClass
		 public void setUp()
		  {
			setupFixture("Login for MMOD Test");
			homePage = new HomePage(browser.driver());
			//homePage.goToAdmin();
		  }	
	  @Test(description = "TEST ID : 1 | OMedia Management On Demand -  Redeliver Shipment", priority=1 )
	   public void RedeliverShipment()
	   {
		Log.info("Message found on page");		
		homePage.goToAdmin().goToRedeliverShipmentPage().reDeliver();
		  // String msg = homePage.goToAdmin().goToRedeliverShipmentPage().reDeliver();
		   //Log.info(msg);
		  // Log.info("Redeliver Shipment Details " );
       }
	 
		@DataProvider(name = "MyOrderHistory_CDROMdelivery")  
	   	public Object[][] recentMyOrderCDROM() throws Exception {  

	        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\OrderManagement\\MyOrderHistory_CDROMdelivery.xls",
	                "MyOrderHistory_CDROMdelivery", "recentMyOrderCDROM");
	        return(retObjArr);
	    }
	   @Test(description = "TEST ID : 2 | Recent Order CD ROM", priority=2,dataProvider= "MyOrderHistory_CDROMdelivery" )	  
	   public void recentMyOrderCDROM(String Shipto ,String Organization ,String Address ,String City ,String State ,String Country ,String ZipCode ,String AuxiliaryPostalCode ,String CarrierCode ,String CustomerPO ,String Specialinstructions ,String deliveryFormat)
	   {
		  // Log.info("Message found on page");
		   boolean isAdded = homePage.goToAdmin().gotoMyOrderHistory().myOrder().selectDeliveryType().detailsDeliveryType(Shipto, Organization, Address, City, State, Country, ZipCode, AuxiliaryPostalCode, CarrierCode, CustomerPO, Specialinstructions, deliveryFormat);
		   assertTrue(isAdded);	
		   homePage.goToAdmin();
	   }	
	    @DataProvider(name = "MyOrderHistory_Email")  
	   	public Object[][] searchMyOrderEMail() throws Exception {  

	        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\OrderManagement\\MyOrderHistory_CDROMdelivery.xls",
	                "MyOrderHistory_Email", "recentMyOrderEmail");
	        return(retObjArr);
	    }
	   
	   @Test(description = "TEST ID : 3 | Media Management On Demand -  Details for Delivery Method", priority=3,dataProvider= "MyOrderHistory_Email" )	  
	   public void searchMyOrderEMail(String Deliveryformat){		   
		   Log.info("Message found on page");
		   boolean isAdded = homePage.goToAdmin().gotoSearchmyorderhistorypage().searchmyorder(Deliveryformat);
		   assertTrue(isAdded);
	   }
	   
	   @DataProvider(name = "MyOrderHistory_Online")  
	   	public Object[][] searchMyOrderOnlineDelivery() throws Exception {  

	        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\OrderManagement\\MyOrderHistory_CDROMdelivery.xls",
	                "MyOrderHistory_Online", "recentMyOrderOnline");
	        return(retObjArr);
	    }
	   
	   @Test(description = "TEST ID : 4 | Media Management On Demand - Search My order History", priority=4,dataProvider= "MyOrderHistory_Online" )	  
	   public void searchMyOrderOnlineDelivery(String Deliveryformat){
		   Log.info("Message found on page");
		   boolean isAdded = homePage.goToAdmin().gotoOnlinedeliveryPage().onlinedelivery(Deliveryformat);
		   assertTrue(isAdded);
	   }
	   @DataProvider(name = "MyOrderHistory_Sourcedelivery")  
	   	public Object[][] searchMyOrderSourcedelivery() throws Exception {  
      Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\OrderManagement\\MyOrderHistory_CDROMdelivery.xls",
	                "MyOrderHistory_Sourcedelivery", "searchMyOrderSourcedelivery");
	        return(retObjArr);
	    }
	   
	   @Test(description = "TEST ID : 5 | Recent Order Source delivery", priority=5, dataProvider= "MyOrderHistory_Sourcedelivery" )	  
	   public void searchMyOrderSourcedelivery(String Shipto,String Organization,String Address,String City,String State,String Country,String ZipCode,String AuxiliaryPostalCode,String CarrierCode,String CustomerPO,String Specialinstructions,String Deliveryformat){
		  // Log.info("Message found on page");
		   boolean isAdded = homePage.goToAdmin().gotoSourcedeliveryPage().Sourcedelivery(Shipto, Organization, Address, City, State, Country, ZipCode, AuxiliaryPostalCode, CarrierCode, CustomerPO, Specialinstructions, Deliveryformat);
		   assertTrue(isAdded);
	   }
	   
	   @DataProvider(name = "MyOrderHistory_AddToProject")  
	   	public Object[][] searchMyOrderAddToProject() throws Exception {  
        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\OrderManagement\\MyOrderHistory_CDROMdelivery.xls",
	                "MyOrderHistory_AddToProject", "searchMyOrderAddToProject");
	        return(retObjArr);
	    }
	   @Test(description = "TEST ID : 6 | Recent Order Addto Project", priority=6, dataProvider= "MyOrderHistory_AddToProject" )	  
	   public void searchMyOrderAddToProject(String project) {
		  // Log.info("Message found on page");
		   boolean isAdded = homePage.goToAdmin().gotoAddtoprojectPage().addtoproject(project);
		   assertTrue(isAdded);
	   }
	   
	    @AfterClass
			public void tearDown(){
				tearDownFixture();
			}
		}


