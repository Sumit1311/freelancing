package com.emotion.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.emotion.core.TestAssert;
import com.emotion.external.Fixtures;
import com.emotion.util.ExcelUtil;

public class CollectionTests extends Fixtures {
	
	private TestAssert testassert = new TestAssert();
	
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

		mmod.manageCollection().createCollection(collection_name,description,orgnization,Caption_type,
				Boolean.parseBoolean(qualitycheck),thumbnailtext,thumbnailbg,Boolean.parseBoolean(watermark), 
				image, position, tile, bluechannel);
		
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
		mmod.manageCollection().editCollection(collection_name);
	
		mmod.manageCollection().updateCollectionDetails(collection_name, description,archive, license, controlTable, deliveryType, Boolean.parseBoolean(qualitycheck), 
				thumbnailbg,thumbnailtext,Boolean.parseBoolean(watermark), image, position, tile, bluechannel);
		mmod.manageCollection().updateCollection();
		
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
		mmod.manageCollection().deleteCollection(collection_name);
	}
	
	
	@AfterClass
	public void tearDown(){
		tearDownFixture();
	}

}
