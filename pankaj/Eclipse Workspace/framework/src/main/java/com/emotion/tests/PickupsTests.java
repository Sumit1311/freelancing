package com.emotion.tests;
import java.util.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.emotion.core.TestAssert;
import com.emotion.core.TestStatus;
import com.emotion.external.ElementTag;
import com.emotion.external.Fixtures;
import com.emotion.lib.IMMOD.ICollection;
import com.emotion.util.ExcelUtil;

public class PickupsTests extends Fixtures{
	private static ICollection collection = null;
	private TestAssert tassert = new TestAssert();

	@BeforeClass
	public void setUp(){
		setupFixture("Login as Admin for Asset operations Tests");
		collection = mmod.collection();
		mmod.goToHomePage();		
	}
	
	
   	@DataProvider(name = "Pickup")  
   	public Object[][] PickupOrders() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection\\AssetOperations.xls",
                "Pickup", "pickup");
        return(retObjArr);
    }
	@Test(description = "TEST ID : 7 | verifyPickupOrders ", priority=7,  dataProvider="Pickup")
	public void verifyPickupOrders(String Id)
	{
		browser.getElement(ElementTag.linkPickup).click();
		browser.getElement(ElementTag.linkPickupOrder).click();
		
		boolean isOrdered = collection.isVisibleInPickup(Id);
		String status = collection.getStatusInPickup(Id);
		tassert.assertTrue(isOrdered, "FAILED | TEST ID :7 | verifyPickupOrders - This Id is not in pickup Order: "+ Id);
		TestStatus.pass("PASSED | TEST ID : 7 | verifyPickupOrders - The Id: "+Id+" is in pickup orders in status: "+status);
	}
	
	@Test(description = "TEST ID : 8 | verifyPickupDownloads ", priority=8,  dataProvider="Pickup")
	public void verifyPickupDownloads(String Id)
	{
		browser.getElement(ElementTag.linkPickup).click();
		browser.getElement(ElementTag.linkPickupDownLoads).click();
		
		boolean isDownloaded = collection.isVisibleInPickup(Id);
		String status = collection.getStatusInPickup(Id);
		tassert.assertTrue(isDownloaded, "FAILED | TEST ID :8 | verifyPickupDownloads-This Id is not in pickup Downloads: "+ Id);
		TestStatus.pass("PASSED | TEST ID : 8 | verifyPickupDownloads - The Id: "+Id+" is in pickup Downloads in status: "+status);
	}

	@Test(description = "TEST ID : 9 | verifyPickupExportedMetadata ", priority=9,  dataProvider="Pickup")
	public void verifyPickupExportedMetadata(String Id)
	{
		browser.getElement(ElementTag.linkPickup).click();
		browser.getElement(ElementTag.linkPickupExportedMetadata).click();
		
		boolean isExported = collection.isVisibleInPickup(Id);
		String status = collection.getStatusInPickup(Id);
		
		tassert.assertTrue(isExported, "FAILED | TEST ID :9 | verifyPickupExportedMetadata-This Id is not in pickup Exported Metadata: "+ Id);
		TestStatus.pass("PASSED | TEST ID : 9 | verifyPickupExportedMetadata - The Id: "+Id+" is in pickup Exported Metadata in status: "+status);
	}


	@AfterClass
	public void tearDown(){
		tearDownFixture();
	}
}
