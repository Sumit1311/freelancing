package com.emotion.tests;

import com.emotion.core.Log;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.emotion.core.By;
import com.emotion.core.TestAssert;
import com.emotion.core.TestStatus;
import com.emotion.external.ElementTag;
import com.emotion.external.Fixtures;
import com.emotion.lib.PageTitles;
import com.emotion.lib.IMMOD.ICaptionType;
import com.emotion.util.ExcelUtil;

public class CaptionTypeTests extends Fixtures {
	private TestAssert tassert = new TestAssert();
	private static ICaptionType captiontype = null;
	
	@BeforeClass
	public void setUp(){
		setupFixture("Caption Test Class");
		captiontype = mmod.captiontype();
	}
	@DataProvider(name = "CaptionType_TestData")  
   	public Object[][] createCaptiontype() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\CaptionType\\CaptionType_data.xls",
                "CaptionType_TestData", "CreateCaptionType");
        return(retObjArr);
    }
   @Test(description = "TEST ID : 1 | Caption Type Creation Test", priority=1,dataProvider= "CaptionType_TestData" )
   public void verifyCreateCaptiontype(String newCaptionType){
	   mmod.goToCaptionManagerPage();
	   captiontype.createCaptionType(newCaptionType);
	   try {
		Thread.sleep(3000);
		} catch (InterruptedException e1) {
		e1.printStackTrace();
		}
	   Log.info("Creating new caption type: " + newCaptionType );
	   boolean isexiting= captiontype.checkCaptionTypeExists(newCaptionType);
	   try{
	   String message = browser.getElement("message", By.classname).getText();
	   boolean isSuccess = message.toLowerCase().contains("successfully created");
	   tassert.assertTrue(isexiting && isSuccess, "FAILED | TEST ID :1 | Caption Type Creation Test"
				+". And the error message displayed is: "+ message.toUpperCase());
	   TestStatus.pass("PASSED | TEST ID : 1 | Create caption type- "+newCaptionType+" was successfully. Message displayed is: "+ message.toUpperCase());
	   }
	   catch(Exception e){
		   Log.info("Error Message found on page");

		   String message = browser.getElement("errorMess", By.classname).getText();
		   TestStatus.fail("FAILED | TEST ID : 1 | Create caption type-"+newCaptionType+" Message displayed was: " + message);

	   }
	   
   }
   
   @DataProvider(name = "MetadataAddfieldCaptionType")  
	public Object[][] metadataCaptiontype() throws Exception {  

    Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\CaptionType\\CaptionType_data.xls",
            "MetadataCaptionType", "MetadataCaption");
    return(retObjArr);
}
@Test(description = "TEST ID : 2 | Metadata addField Caption Type Test", priority=2,dataProvider= "MetadataAddfieldCaptionType" )
public void metadataCaptionType(String metadatacaptiontypename,String label,String description,String method ,
		String size,String sortable,String metadatatype,String Metadataproperties,String enterdefault)
{
	   mmod.goToCaptionManagerPage();
	 Log.info("Selecting caption type: " + metadatacaptiontypename );
	   captiontype.selectCaptionType(metadatacaptiontypename);
	   try {
		Thread.sleep(3000);
		} catch (InterruptedException e1) {
		e1.printStackTrace();
		
		}
	browser.getElement(ElementTag.bttnCaptionTypeEdit).click();
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 captiontype.metadataAddfieldCaptioType(label,description, method,size,sortable,metadatatype,Metadataproperties,enterdefault);
   Log.info("Caption type: " + metadatacaptiontypename);

}
      
   @DataProvider(name = "EditCaptionTypeView_TestData")  
  	public Object[][] editCaptionTypeView() throws Exception {  
       Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\CaptionType\\EditCaptionTypeView_Data.xls",
               "EditCaptionTypeView_TestData", "EditCaptionTypeview");
       return(retObjArr);
   }
   
   
   @Test(description = "TEST ID : 3 | Edit Caption Type Test-edit view", priority=3,dataProvider= "EditCaptionTypeView_TestData" )
	   public void editCaptionTypeView(String newCaptionType, String view, String template, String allfields){
	   mmod.goToCaptionManagerPage();
	   Log.info("Selecting caption type: " + newCaptionType );
	   captiontype.selectCaptionType(newCaptionType);
	   try {
		Thread.sleep(3000);
		} catch (InterruptedException e1) {
		e1.printStackTrace();
		}
	   Log.info("Editing caption type: " + newCaptionType );
	   Log.info("Adding a Field to the view: " + view +" in template: "+template + " for caption type: " + allfields );
	   
	   String message = captiontype.editCaptionType(view,template, allfields);
	   try {
		Thread.sleep(3000);
		} catch (InterruptedException e1) {
		e1.printStackTrace();
		}
	  // try{
	   //String message = browser.getElement("message", By.classname).getText();
	   boolean isSuccess = message.toLowerCase().contains("successfully");
	   if(isSuccess==false){
	   String err = browser.getElement("errorMess", By.classname).getText();
	   TestStatus.fail("FAILED | TEST ID : 2 | Edit Caption Type Test-edit view-"+newCaptionType+
	   		 "Message displayed was: " + err);
	   }
	   
	   tassert.assertTrue(isSuccess, "FAILED | TEST ID :2 | Edit Caption Type Test-edit view"
				+". And the error message displayed is: "+ message.toUpperCase());
	   TestStatus.pass("PASSED | TEST ID : 2 | Edit Caption Type Test-edit view- "+newCaptionType+" was successfully. Message displayed is: "+ message.toUpperCase());
	   //}
	   //catch(Exception e){
		   //Log.info("Error Message found on page");
		   //String message = browser.getElement("errorMess", By.classname).getText();
		   //TestStatus.fail("FAILED | TEST ID : 2 | Edit Caption Type Test-edit view-"+newCaptionType);
		   		// Message displayed was: " + message);

	   //}
   }   
   @DataProvider(name = "CloneCaptionType")  
 	public Object[][] cloneCaptiontype() throws Exception {  

      Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\CaptionType\\CaptionType_data.xls",
              "CloneCaptionType", "CloneCaptionType");
      return(retObjArr);
  }
 @Test(description = "TEST ID : 4 | Clone Caption Type Test", priority=4,dataProvider= "CloneCaptionType" )
 public void cloneCaptionType(String clonecaptiontypename){
	 mmod.goToCaptionManagerPage();
	 Log.info("Selecting caption type: " + clonecaptiontypename );
	   captiontype.selectCaptionType(clonecaptiontypename);
	   try {
		Thread.sleep(3000);
		} catch (InterruptedException e1) {
		e1.printStackTrace();
		}
     captiontype.cloneCaptionType(clonecaptiontypename);
     Log.info("clone caption type: " + clonecaptiontypename);
 
 }
   
   @DataProvider(name = "DeleteCaptionType")  
  	public Object[][] deleteCaptionType() throws Exception {  
       Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\CaptionType\\CaptionType_Data.xls",
               "DeleteCaptionType", "DeleteCaptionType");
       return(retObjArr);
   }
   @Test(description = "TEST ID : 5 | delete Caption Type Test", priority=5,dataProvider= "DeleteCaptionType" )
   public void deleteCaptionType(String captiontypename){
   mmod.goToCaptionManagerPage();
   Log.info("Selecting caption type: " + captiontypename);
   mmod.captiontype().selectCaptionType(captiontypename);
   Log.info("Delete caption type: " + captiontypename);
   mmod.captiontype().deleteCaptioType(captiontypename);
  
   }  
   
   
   @AfterClass
	public void tearDown(){
		tearDownFixture();
	}
}

