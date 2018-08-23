package com.emotion.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.emotion.core.Log;
import com.emotion.core.TestAssert;
import com.emotion.core.TestStatus;
import com.emotion.external.ElementTag;
import com.emotion.external.Fixtures;
import com.emotion.lib.IMMOD.ICollection;
import com.emotion.lib.Messages;
import com.emotion.util.ExcelUtil;

public class AssetOperationsTests extends Fixtures{
	private static ICollection collection = null;
	private TestAssert tassert = new TestAssert();

	@BeforeClass
	public void setUp(){
		setupFixture("Login as CreativeAuto for Asset operations Tests");
		collection = mmod.collection();
		mmod.goToHomePage();		
	}
	
   	@DataProvider(name = "downloadAsset")  
   	public Object[][] downloadAsset() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection\\AssetOperations.xls",
                "Download", "downloadasset");
        return(retObjArr);
    }
	@Test(description = "TEST ID : 1 | verifyDownloadAsset ", priority=1, dataProvider="downloadAsset")
	public void verifyDownloadAsset(String collection_name,String caption_ids,String view, String transformation)
	{
		collection.selectCollection(collection_name);
					
		String[] captionids= caption_ids.split(",");
		
		Log.info("Starting Download for Caption Ids: ".toUpperCase()+ caption_ids + " in transformation type: "+transformation);
		String msg = collection.downloadAssets(captionids, view, transformation);
				
		//verification if moves to download status page
		String url= browser.driver().getCurrentUrl();
		boolean onDownloadStatusPage =url.contains("/downloadstatus.aspx");
		if (onDownloadStatusPage == true){
			Log.info("Reached Download Status page and reading transaction id...");
			
			String id = collection.getTransactionId();
			Log.info("Transaction Id: "+id+" was created. Verifying it in PickUps...");
			
			browser.getElement(ElementTag.linkPickup).click();
			browser.getElement(ElementTag.linkPickupDownLoads).click();
			
			boolean isVisible = collection.isVisibleInPickup(id);
			String status = collection.getStatusInPickup(id);
			boolean expectedResult = isVisible && !status.contains("failed");

			tassert.assertTrue(expectedResult, "FAILED | TEST ID :1 | Download assets FAILED for Caption Ids: " + 
											caption_ids + ". Id = "+ id +" either not present in Pickup downloads OR its status shows = "+status);
			
			TestStatus.pass("PASSED | TEST ID : 1 | Download assets verified for Caption Ids: " + caption_ids
					+ "\n\t\t\t\t\t\t\t\t\t\t Transaction "+id+" found in Pickup in status = "+ status);
		}
		else{
		Log.info("Transaction Id was not created for the transformation type: " +transformation);
		boolean isError = msg.contains("error");
		
		tassert.assertTrue(!isError, "FAILED | TEST ID :1 | Download assets FAILED for Caption Ids: " + 
										caption_ids + ". And the error message displayed is: "+ msg.toUpperCase());
		
		
		TestStatus.pass("PASSED | TEST ID : 1 | Download assets verified for Caption Ids: " + caption_ids);
		
		}

	}
	
   	@DataProvider(name = "transmitAsset")  
   	public Object[][] transmitAsset() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection\\AssetOperations.xls",
                "Transmit", "transmitAsset");
        return(retObjArr);
    }
   	
	@Test(description = "TEST ID : 2 | verifyTransmitAsset ", priority=2,  dataProvider="transmitAsset")
	public void verifyTransmitAsset(String collection_name,String caption_ids,String view,
			 String externalEmailId,String ccSelf, String subject, String message, String toUsers,String transformation)
	{
		collection.selectCollection(collection_name);
		String[] captionids= caption_ids.split(",");
				
		boolean boolCCSelf=false;
		if (ccSelf.toLowerCase().contains("yes"))
			boolCCSelf= true;
		
		if (toUsers.isEmpty()|| toUsers== " "){
			toUsers= null;
			Log.info("Transmitting assets for caption ids ".toUpperCase()+ caption_ids + " in transformation type: "+transformation +
					" to external email ids: " + externalEmailId );
			}
		else
			Log.info("Transmitting assets for caption ids: ".toUpperCase()+ caption_ids + " in transformation type: "+transformation +
					" to internal users' email ids: " + toUsers );
					
		String msg = collection.transmitAssets(captionids, view, externalEmailId, boolCCSelf,
				subject, message, toUsers, transformation);
		String url= browser.driver().getCurrentUrl();
		boolean onStatus =url.contains("/transmiterror.aspx");
		if (onStatus == true){
			Log.info("Reached Transmit Status page and reading message...");
					
			tassert.assertTrue(msg.contains(Messages.TransmitSuccess),
							"FAILED | TEST ID :2 | Transmit assets FAILED for Caption Ids: " + caption_ids + 
							".\n\t\t\t\t\t\t\t\t\t Displays the message: "+ msg );
			TestStatus.pass("PASSED | TEST ID : 2 | Transmit asset verified for Caption Ids: " + caption_ids +". Sent to: "+externalEmailId+
					".\n\t\t\t\t\t\t\t\t\t\t\t Displays the message: "+ msg);
		}
		else
			tassert.assertTrue(onStatus,"FAILED | TEST ID :2 | Transmit assets FAILED for Caption Ids: " + caption_ids + 
							". Did not reach Transmit Status page. Displays the message: "+ msg );

	}

   	@DataProvider(name = "ExportMetadata")  
   	public Object[][] ExportMetadata() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection\\AssetOperations.xls",
                "Export_Metadata", "exportmetadata");
        return(retObjArr);
    }
	@Test(description = "TEST ID : 3 | VerifyExportMetadata ", priority=3,  dataProvider="ExportMetadata")
	public void verifyExportMetadata(String collection_name,String caption_ids,String view, String filename)
	{
		collection.selectCollection(collection_name);
		String[] captionids= caption_ids.split(",");
		
		Log.info("Exporting assets for caption ids ".toUpperCase()+ caption_ids);
		collection.exportMetadata(captionids, view, filename);

		Log.info("Reached Export Status page and reading status and export id...");
			
		boolean showsSuccessMessage = browser.getElement(ElementTag.labelExportStatus).getText().toLowerCase()
				.contains(Messages.ExportSuccess.toLowerCase());
		
		String exportId = collection.getExportId();
		
		Log.info("Export Id: "+exportId+" was created. Verifying it in PickUps...");
		browser.getElement(ElementTag.linkPickup).click();
		browser.getElement(ElementTag.linkPickupExportedMetadata).click();
		boolean isIdVisibleInPickup = collection.isVisibleInPickup(exportId);		

		tassert.assertTrue(showsSuccessMessage && isIdVisibleInPickup, "FAILED | TEST ID :3 | Export Metadata FAILED for Caption Ids: " + 
				caption_ids + ". Id = "+exportId+" not present in Pickup exported metadata.");
		String status = collection.getStatusInPickup(exportId);
		TestStatus.pass("PASSED | TEST ID : 3 | Export Metadata verified for Caption Ids: " + caption_ids
				+ "\n\t\t\t\t\t\t\t\t\t\t Transaction "+exportId+" found in Pickup in status = "+ status);
	}
	
   	@DataProvider(name = "ExportAllMetadata")  
   	public Object[][] ExportAllMetadata() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection\\AssetOperations.xls",
                "Export_All", "exportAllMetadata");
        return(retObjArr);
    }
	@Test(description = "TEST ID : 4 | VerifyExportAllMetadata ", priority=4,  dataProvider="ExportAllMetadata")
	
	public void verifyExportAllMetadata(String collection_name,String view, String filename)
	{
		collection.selectCollection(collection_name);
		
		Log.info("Exporting All Metadata...");
		collection.exportAllMetadata(filename);
		
		Log.info("Reached Export Status page and reading status and export id...");
			
		boolean showsSuccessMessage = browser.getElement(ElementTag.labelExportStatus).getText().toLowerCase()
				.contains(Messages.ExportSuccess.toLowerCase());
		
		String exportId = collection.getExportId();
		
		Log.info("Transaction Id: "+exportId+" was created. Verifying it in PickUps...");
		browser.getElement(ElementTag.linkPickup).click();
		browser.getElement(ElementTag.linkPickupExportedMetadata).click();
		boolean isIdVisibleInPickup = collection.isVisibleInPickup(exportId);		

		tassert.assertTrue(showsSuccessMessage && isIdVisibleInPickup, "FAILED | TEST ID :4 | Export All Metadata FAILED. Id = "+exportId+" not present in Pickup exported metadata.");
		String status = collection.getStatusInPickup(exportId);
		TestStatus.pass("PASSED | TEST ID : 4 | Export All Metadata verified" +
		"\n\t\t\t\t\t\t\t\t\t\t ExportId: "+exportId+" found in Pickup in status = "+ status);
	}
	
   	@DataProvider(name = "OrderAsset")  
   	public Object[][] OrderAsset() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection\\AssetOperations.xls",
                "Order", "orderAsset");
        return(retObjArr);
    }
   	@Test(description = "TEST ID : 5 | verifyOrderAssets ", priority=5,  dataProvider="OrderAsset")
	
	public void verifyOrderAssets(String collection_name,String caption_ids,String view, String transformation,
			String answer1, String answer2, String answer3, String answer4, String delivery, String project )
	{
		collection.selectCollection(collection_name);
		String[] captionids= caption_ids.split(",");
		
		Log.info("STARTING ORDER FOR CAPTION IDS: "+ caption_ids + " in transformation type: "+transformation +
				" and delivery mode as: "+delivery);
		
		collection.orderAssets(captionids, view, transformation, answer1, answer2, answer3,answer4, delivery, project);
		
		
		String url= browser.driver().getCurrentUrl();
		boolean onProjectPage =url.contains("materials.aspx?project_id=");
		if (onProjectPage == true){
			tassert.assertTrue(onProjectPage,"FAILED | TEST ID :5 | Order assets FAILED for Caption Ids: " + 
					caption_ids + ". Did not reach the Project page. Asset is not added to project");
		TestStatus.pass("PASSED | TEST ID : 5 | Order assets verified for Caption Ids: " + caption_ids
				+ ". Assets are added to the project: " + project);
		}
		else{
			boolean onShipmentStatus =url.contains("/shipmentstatus.aspx");
			if (onShipmentStatus == true){
				Log.info("Reached Download Status page and reading transaction id...");
			
				String shipmentNumber = browser.getElement(ElementTag.labelShipmentNumber).getText().split("shipment")[1].trim();
			
				Log.info("Shipment number: "+shipmentNumber+" was created. Verifying it in PickUps...");
			
					
				browser.getElement(ElementTag.linkPickup).click();
				browser.getElement(ElementTag.linkPickupOrder).click();

				boolean isVisibleInPickUp = collection.isVisibleInPickup(shipmentNumber);
				String status = collection.getStatusInPickup(shipmentNumber);
				tassert.assertTrue(onShipmentStatus && isVisibleInPickUp, "FAILED | TEST ID :5 | Order assets FAILED for Caption Ids: " + 
													caption_ids + ". Id = "+ shipmentNumber + " either not present in Pickup Orders OR its status shows = "+status);

				TestStatus.pass("PASSED | TEST ID : 5 | Order assets verified for Caption Ids: " + caption_ids
							+ "\n\t\t\t\t\t\t\t\t\t\t Shipment " + shipmentNumber + " found in Pickup in status = "+ status);

			}
			else
				tassert.assertTrue(onShipmentStatus,"FAILED | TEST ID :5 | Order assets FAILED for Caption Ids: " + 
				caption_ids + ". Did not reach the Shipment Status page");
			}
	}
	
   	@DataProvider(name = "AddToCartAndOrder")  
   	public Object[][] AddToCartAndOrder() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection\\AssetOperations.xls",
                "AddToCart_Order", "addToCartAndOrder");
        return(retObjArr);
    }
	@Test(description = "TEST ID : 6 | verifyAddToCartAndOrder ", priority=6,  dataProvider="AddToCartAndOrder")
	public void verifyAddToCartAndOrder(String collection_name,String caption_ids,String view, String transformation,
			String answer1, String answer2, String answer3, String answer4, String delivery, String project )
	{
		collection.selectCollection(collection_name);
		String[] captionids= caption_ids.split(",");
		
		Log.info("Starting Order for Caption Ids: "+ caption_ids + " in transformation type: "+transformation +
				" and delivery mode as: "+delivery);
		
		collection.AddToCartAndOrder(captionids, view, transformation, answer1, answer2, answer3,answer4, delivery, project);
		
		
		String url= browser.driver().getCurrentUrl();
		boolean onProjectPage =url.contains("materials.aspx?project_id=");
		if (onProjectPage == true){
			tassert.assertTrue(onProjectPage,"FAILED | TEST ID :5 | Order assets FAILED for Caption Ids: " + 
					caption_ids + ". Did not reach the Project page. Asset is not added to project");
		TestStatus.pass("PASSED | TEST ID : 5 | Order assets verified for Caption Ids: " + caption_ids
				+ ". Assets are added to the project: " + project);
		}
		else{
		boolean onShipmentStatus =url.contains("/shipmentstatus.aspx");
		if (onShipmentStatus == true){
			Log.info("Reached Download Status page and reading transaction id...");
			
			String shipmentNumber = browser.getElement(ElementTag.labelShipmentNumber).getText().split("shipment")[1].trim();
			
			Log.info("Shipment number: "+shipmentNumber+" was created. Verifying it in PickUps...");
			
					
			browser.getElement(ElementTag.linkPickup).click();
			browser.getElement(ElementTag.linkPickupOrder).click();

			boolean isVisibleInPickUp = collection.isVisibleInPickup(shipmentNumber);
			String status = collection.getStatusInPickup(shipmentNumber);
			tassert.assertTrue(onShipmentStatus && isVisibleInPickUp, "FAILED | TEST ID :6 | Add to Cart and Order assets FAILED for Caption Ids: " + 
													caption_ids + ". Id = "+ shipmentNumber + " either not present in Pickup Orders OR its status shows = "+status);
			
			TestStatus.pass("PASSED | TEST ID : 6 | Add to Cart and Order assets verified for Caption Ids: " + caption_ids
							+ "\n\t\t\t\t\t\t\t\t\t\t Shipment " + shipmentNumber + " found in Pickup in status = "+ status);
			}
			else
				tassert.assertTrue(onShipmentStatus,"FAILED | TEST ID :6 | Add to Cart and Order assets FAILED for Caption Ids: " + 
						caption_ids + ". Did not reach the Shipment Status page");
		}
	}


   	@DataProvider(name = "Reindex")  
   	public Object[][] Reindex() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection\\AssetOperations.xls",
                "Reindex", "reindex");
        return(retObjArr);
    }
	@Test(description = "TEST ID : 10 | verifyReindex ", priority=10,  dataProvider="Reindex")
	public void verifyReindex(String collection_name,String caption_ids,String view)
	{
		collection.selectCollection(collection_name);
		String[] captionids= caption_ids.split(",");
		
		Log.info("Starting Re-Indexing for Caption Ids: "+ caption_ids);
		
		String msg = collection.reIndexAssets(captionids, view);
		
		tassert.assertTrue(msg.contains(Messages.ReindexSuccess), "FAILED | TEST ID :10 |  Reindex assets FAILED for Caption Ids: " + 
				caption_ids + " Message displayed:- " + msg);
		TestStatus.pass("PASSED | TEST ID : 10 | Reindex assets verified for Caption Ids: " + caption_ids
				+ "\n\t\t\t\t\t\t\t\t\t\t Message Displayed: "+ msg);
		}
	
   	@DataProvider(name = "Delete")  
   	public Object[][] Delete() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection\\AssetOperations.xls",
                "Delete", "delete");
        return(retObjArr);
    }
	@Test(description = "TEST ID : 11 | verifyDelete ", priority=11,  dataProvider="Delete")
	public void verifyDelete(String collection_name,String caption_ids,String view, String sendMsgToLightbox, 
				String sendMsgToCart, String sendMsgToProject,String lightboxMsg, String cartMsg,String projectMsg)
	{
		collection.selectCollection(collection_name);
		String[] captionids= caption_ids.split(",");
		
		Log.info("Starting Delete for Caption Ids: "+ caption_ids);
		
		boolean toLightbox =false;
		boolean toCart = false;
		boolean toProject = false;
		
		if (sendMsgToLightbox.toLowerCase().contains("yes")) toLightbox = true;
		if (sendMsgToCart.toLowerCase().contains("yes")) toCart = true;
		if (sendMsgToProject.toLowerCase().contains("yes")) toProject = true;

		String msg = collection.deleteAssets(captionids, view, toLightbox, toCart, toProject,lightboxMsg, cartMsg, projectMsg);
		
		tassert.assertTrue(msg.contains("deletion tasks were created"), "FAILED | TEST ID :11 |  Delete assets FAILED for Caption Ids: " + 
				caption_ids + " Message displayed:- " + msg);
		TestStatus.pass("PASSED | TEST ID : 11 | Delete assets verified for Caption Ids: " + caption_ids
				+ "\n\t\t\t\t\t\t\t\t\t\t Message Displayed: "+ msg);		
	}

   	@DataProvider(name = "copyAsset")  
   	public Object[][] copyAsset() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection\\AssetOperations.xls",
                "copyAsset", "copyAsset");
        return(retObjArr);
    }
	@Test(description = "TEST ID : 12 | copyAsset ", priority=12, dataProvider="copyAsset")
	public void verifyCopyAsset(String collection_name,String caption_ids,String view, String toCollection,
			String asShortcut, String taskName, String taskDecription)
	{
		collection.selectCollection(collection_name);
					
		String[] captionids= caption_ids.split(",");
		
		Log.info("Starting Copy for Caption Ids: ".toUpperCase()+ caption_ids + " FROM COLLECTION: "+collection_name +
				" to collection: "+ toCollection);
		boolean shortcut =false;
		if (asShortcut.toLowerCase().contains("yes"))
			shortcut =true;
			
		String msg = collection.copyAssets(captionids, view, toCollection, shortcut, taskName, taskDecription);

		boolean isError = msg.contains("will not be copied");
		
		tassert.assertTrue(!isError, "FAILED | TEST ID :12 | Copy assets FAILED for Caption Ids: " + 
										caption_ids + " Copy from: "+ collection_name +" to: "+ toCollection 
										+". And the error message displayed is: "+ msg.toUpperCase());
		
		
		TestStatus.pass("PASSED | TEST ID : 12 | Copy assets verified for Caption Ids: " + caption_ids
				+ ". Assets copied from: "+ collection_name + " to: "+ toCollection +" and the message displayed is: "+msg);
		
		}
	
   	@DataProvider(name = "moveAsset")  
   	public Object[][] moveAsset() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection\\AssetOperations.xls",
                "moveAsset", "moveAsset");
        return(retObjArr);
    }
	@Test(description = "TEST ID : 13 | moveAsset ", priority=13, dataProvider="moveAsset")
	public void verifyMoveAsset(String collection_name,String caption_ids,String view, String toCollection,
			String taskName, String taskDecription)
	{
		collection.selectCollection(collection_name);
					
		String[] captionids= caption_ids.split(",");
		
		Log.info("Starting Move for Caption Ids: ".toUpperCase()+ caption_ids + " FROM COLLECTION: "+collection_name +
				" to collection: "+ toCollection);
	
			
		String msg = collection.moveAssets(captionids, view, toCollection, taskName, taskDecription);

		boolean isError = msg.contains("will not be moved");
		
		tassert.assertTrue(!isError, "FAILED | TEST ID :13 | Move assets FAILED for Caption Ids: " + 
										caption_ids + " Move from: "+ collection_name +" to: "+ toCollection 
										+". And the error message displayed is: "+ msg.toUpperCase());
		
		
		TestStatus.pass("PASSED | TEST ID : 13 | Move assets verified for Caption Ids: " + caption_ids
				+ ". Assets moved from: "+ collection_name + " to: "+ toCollection +" and the message displayed is: "+msg);
		
		}
	
   	@DataProvider(name = "captionAsset")  
   	public Object[][] captionAsset() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\Collection\\AssetOperations.xls",
                "captionAsset", "captionAsset");
        return(retObjArr);
    }
	@Test(description = "TEST ID : 14 | captionAsset ", priority=14, dataProvider="captionAsset")
	public void verifyCaptionAsset(String collection_name,String caption_id,String view, String description, String category)
	{
		collection.selectCollection(collection_name);
		Log.info("Starting captioning of caption id: "+caption_id);
			
		String descriptionread = collection.captionAsset(collection_name,caption_id, view, description, category);
		
		tassert.assertTrue(descriptionread.trim().equalsIgnoreCase(description.trim()), "FAILED | TEST ID :15 | Caption asset FAILED for Caption Id: "+ caption_id+
				"Description through captioning is: "+ description +". Actual description foound is: "+descriptionread);;
		TestStatus.pass("PASSED | TEST ID : 15 | Caption Asset verified for Caption Id: " +caption_id+ " Description found on the screen is : "+descriptionread);
		}
	
	@AfterClass
	public void tearDown(){
		tearDownFixture();
	}

	}
