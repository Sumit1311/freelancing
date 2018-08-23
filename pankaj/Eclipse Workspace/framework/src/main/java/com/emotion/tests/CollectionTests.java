package com.emotion.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.emotion.core.By;
import com.emotion.core.FrameworkException;
import com.emotion.core.Log;
import com.emotion.core.TestAssert;
import com.emotion.core.TestStatus;
import com.emotion.external.Fixtures;
import com.emotion.util.ExcelUtil;

public class CollectionTests extends Fixtures {
	
	private TestAssert tassert = new TestAssert();
	
	@BeforeClass
	public void setUp(){
		setupFixture("Collection Test Class");
	}
	
   	@DataProvider(name = "Collection_data")  
   	public Object[][] createCollection() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection Manager\\Collection_Data.xls",
                "Collection_Data", "createcollection");
        return(retObjArr);
    }
   	
 
	@Test(description = "TEST ID : 1 | Collection Creation Test", priority=1, dataProvider="Collection_data")
	public void verifyCreateCollection(String collection_name,String description,String orgnization,
			String Caption_type,String qualitycheck,String thumbnailtext, String thumbnailbg, String watermark,
			String image,String position,String tile, String bluechannel){

		mmod.goToCollectionManagerPage();
		Log.info("CREATING COLLECTION WITH NAME: " + collection_name);
		   try {
				Thread.sleep(3000);
				} catch (InterruptedException e1) {
				e1.printStackTrace();
				}

		mmod.manageCollection().createCollection(collection_name,description,orgnization,Caption_type,
				Boolean.parseBoolean(qualitycheck),thumbnailtext,thumbnailbg,Boolean.parseBoolean(watermark), 
				image, position, tile, bluechannel);
		   try {
				Thread.sleep(3000);
				} catch (InterruptedException e1) {
				e1.printStackTrace();
				}
		String message = browser.getElement("message", By.classname).getText();
		boolean success =message.contains("The collection") &&
							message.contains(collection_name) &&
							 message.contains("is added successfully");
		mmod.manageCollection().selectCollection(collection_name);
		   try {
				Thread.sleep(3000);
				} catch (InterruptedException e1) {
				e1.printStackTrace();
				}
		tassert.assertTrue(success, "FAILED | TEST ID :1 |Collection Creation Test| Collection: "+ collection_name+ " was not created. Message displayed is: "
				+ message);
		
		TestStatus.pass("PASSED | TEST ID : 1 |Collection Creation Test| Collection: "+ collection_name+ " was created successfully. Message displayed is: "
				+ message);
		
	}
	
  	@DataProvider(name = "Collection_edit")  
   	public Object[][] editCollection() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection Manager\\Collection_Data.xls",
                "Collection_Edit", "editdata");
        return(retObjArr);
    }
  	
	@Test(description="TEST ID : 2 | Collection Edit Test", priority=2, dataProvider="Collection_edit")
	public void verifyEditCollection(String collection_name,String description,String archive,String license,
			String controlTable,String deliveryType,String qualitycheck,
			String thumbnailbg,String thumbnailtext,  String watermark,String image,String position,String tile, String bluechannel){
		
		mmod.goToCollectionManagerPage();
		   try {
				Thread.sleep(3000);
				} catch (InterruptedException e1) {
				e1.printStackTrace();
				}
		Log.info("EDITING COLLECTION WITH NAME: " + collection_name);
		mmod.manageCollection().editCollection(collection_name);
		   try {
				Thread.sleep(3000);
				} catch (InterruptedException e1) {
				e1.printStackTrace();
				}
		
		mmod.manageCollection().updateCollectionDetails(collection_name, description,archive, license, controlTable, deliveryType, Boolean.parseBoolean(qualitycheck), 
				thumbnailbg,thumbnailtext,Boolean.parseBoolean(watermark), image, position, tile, bluechannel);
		mmod.manageCollection().updateCollection();
		   try {
				Thread.sleep(3000);
				} catch (InterruptedException e1) {
				e1.printStackTrace();
				}
		String message = browser.getElement("message", By.classname).getText();
		boolean success = message.contains("was updated");
		
		tassert.assertTrue(success, "FAILED | TEST ID :2 |Collection Edit Test| Collection: "+ collection_name+ " was not edited. Message displayed is: "
				+ message);
		
		TestStatus.pass("PASSED | TEST ID : 2 |Collection Edit Test| Collection: "+ collection_name+ " was edited successfully. Message displayed is: "
				+ message);
		
	}
	
	@DataProvider(name = "Collection_delete")  
   	public Object[][] deleteCollection() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection Manager\\Collection_data.xls",
                "Collection_Delete", "deletedata");
        return(retObjArr);
    }
	
	@Test(description="TEST ID : 3 | Collection Deletion Test", priority=3, dataProvider="Collection_delete")
	public void verifyDeleteCollection(String collection_name){
		mmod.goToCollectionManagerPage();
		Log.info("DELETING COLLECTION WITH NAME: " + collection_name);
		mmod.manageCollection().deleteCollection(collection_name);
		   try {
				Thread.sleep(3000);
				} catch (InterruptedException e1) {
				e1.printStackTrace();
				}
		boolean isDeleted=false;
		try{
		mmod.manageCollection().selectCollection(collection_name);
		}
		catch(FrameworkException f){
			isDeleted= true;
		}
				
		tassert.assertTrue(isDeleted, "FAILED | TEST ID :2 |Collection Delete Test| Collection: "+ collection_name+ " was not deleted.");
		
		TestStatus.pass("PASSED | TEST ID : 2 |Collection Delete Test| Collection: "+ collection_name+ " was deleted successfully. Collection not found. ");
	}	
	
	@AfterClass
	public void tearDown(){
		tearDownFixture();
	}

}
