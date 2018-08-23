package com.emotion.lib;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.emotion.core.Browser;
import com.emotion.core.By;
import com.emotion.core.ExpectedCondition;
import com.emotion.core.Locater;
import com.emotion.core.Log;
import com.emotion.core.PageElements;
import com.emotion.core.Select;
import com.emotion.external.ElementTag;

public class CaptionType extends Locater implements IMMOD.ICaptionType{
		
		private Browser browser = null;
		private MMOD mmod = null;
		
		public CaptionType(Browser browser) {
			this.browser=browser;
			mmod = new MMOD(browser);
		}

		@Override
		public void createCaptionType(String name) {
			browser.getElement(ElementTag.txtNewCaptionType).sendKeys(name);
			browser.getElement(ElementTag.bttnCreateCaptionType).click();
		}
		public boolean checkCaptionTypeExists(String captiontype) {
            Select select =  new Select(browser.getElement(ElementTag.listExistingCaptionType));
            boolean exists = false;
            List<PageElements> alloptions = select.getOptions();
            for (PageElements element : alloptions)
            {
                  if(element.getText().equalsIgnoreCase(captiontype)){
                        exists =true;
                        break;
                  }                       
            }     
            return exists;    
      }
		
        public void selectCaptionType(String captiontype) {
            Select select =  new Select(browser.getElement(ElementTag.listExistingCaptionType));
            boolean exists = false;
            List<PageElements> alloptions = select.getOptions();
            for (PageElements element : alloptions)
            {
                  if(element.getText().equalsIgnoreCase(captiontype)){
                        element.click();
                        break;
                  } 
              	
            }     
               
      }
        @Override
		public void deleteCaptioType(String captionType) {
        	  
    	 browser.getElement(ElementTag.bttnDeleteCaptionType).click();
    	 browser.driver().switchTo().alert().accept();
    	/* mmod.captiontype().checkAlertDeleteCaptionType();
    	 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    
    	 /*try {
    	        Browser webDriver = null;
				Alert alert = webDriver.switchTo().alert();
                  // check if alert exists
    	        // TODO find better way
    	        alert.getText();
                  // alert handling
    	        log().info("Alert detected: {}" + alert.getText());
    	        alert.accept();
    	    } catch (Exception e) {
    	 }*/
    	 
			
		}

		private Log log() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void shareCaptioType() {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void metadataAddfieldCaptioType(String label,String description,
				String method ,String size ,String sortable ,String metadatatype, String Metadataproperties, String enterdefault) {
			String BaseWindowHandle=browser.switchTo().getBaseWindowHandle();
			browser.getElement(ElementTag.bttnMetadataCaptionType).click();
			browser.getElement(ElementTag.bttnMetadataAddnewField).click();
			browser.switchTo().window("Add a Field to a Caption Type");
			browser.getElement(ElementTag.txtAddNewMetaDatafield).sendKeys(label);
			browser.getElement(ElementTag.txtDescriptionMetaDataAddfield).sendKeys(description);
			Select select =  new Select(browser.getElement(ElementTag.listMethodSelectMetaDataAddfield));
	      /*  boolean exists = false; */
	            List<PageElements> alloptions = select.getOptions();
	            for (PageElements element : alloptions)
	            {
	                  if(element.getText().equalsIgnoreCase(method)){
	                        element.click();
	                        break;
	                  } 
	                } 
	        Select select1 = new Select(browser.getElement(ElementTag.listSizeSelectMetaDataAddfield));    
	        /*  boolean exists1 = false; */
            List<PageElements> alloptions1 = select1.getOptions();
            for (PageElements element : alloptions1)
            {
                  if(element.getText().equalsIgnoreCase(size)){
                        element.click();
                        break;
                  } 
                }
            Select select2 = new Select(browser.getElement(ElementTag.listSortableSelectMetaDataAddfield));    
            /*  boolean exists2 = false; */
            List<PageElements> alloptions2 = select2.getOptions();
            for (PageElements element : alloptions2)
            {
                  if(element.getText().equalsIgnoreCase(sortable)){
                        element.click();
                        break;
                  } 
                }
            Select select3 = new Select(browser.getElement(ElementTag.listMetadatatypeSelectMetaDataAddfield));    
	       /*  boolean exists3 = false; */
            List<PageElements> alloptions3 = select3.getOptions();
            for (PageElements element : alloptions3)
            {
                  if(element.getText().equalsIgnoreCase(metadatatype)){
                        element.click();
                        break;
                  } 
                }
            Select select4 = new Select(browser.getElement(ElementTag.listMetadataPropertieSelect));    
 	       /*  boolean exists4 = false; */
             List<PageElements> alloptions4 = select4.getOptions();
             for (PageElements element : alloptions4)
             {
                   if(element.getText().equalsIgnoreCase(Metadataproperties)){
                         element.click();
                         break;
                   } 
                 }
            browser.getElement(ElementTag.txtDefaultEnterMetaDatafield).sendKeys(enterdefault);
            browser.getElement(ElementTag.chkboxNumberFieldValidation).click();
            browser.getElement(ElementTag.optTextAreaMetadaAddfield).click();
            browser.getElement(ElementTag.bttnOkMetadaAddfield).click();
			browser.switchTo().parentWindow(BaseWindowHandle);
					
        browser.getElement(ElementTag.bttnSaveMetadataAddnewField).click();	
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        browser.getElement(ElementTag.bttnSaveCloneCaptionType).click();
        browser.getElement(ElementTag.linkACaptionTypeManager).click();
        browser.getElement(ElementTag.bttnRegenerateAllViewsAndSearchPage).click();
    	try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}

		@Override
		public void cloneCaptionType(String clonecaptiontypename) {
			browser.getElement(ElementTag.bttnCloneCaptionType).click();
			browser.getElement(ElementTag.bttnSaveCloneCaptionType).click();
			}

		@Override
		public String editCaptionType(String view, String template ,String allfields) {
		    Log.info("Navigate to Caption Manager Page.");
			browser.getElement(ElementTag.bttnCaptionTypeEdit).click();
	        browser.getElement(ElementTag.bttnViews).click();
	        String BaseWindowHandle=browser.switchTo().getBaseWindowHandle();
	        switch(view){
	        	case("Images/Text"):
	        		browser.getElement(ElementTag.bttnEditImagesText).click();
	        	case("imageonly"):
	        		//browser.getElement(ElementTag.bttnEditImagesText).click();
	        		
	                    }
	     	browser.switchTo().window("Edit a view");
	     	Select select =  new Select(browser.getElement(ElementTag.listTemplateSelect));
	        boolean exists = false;
	            List<PageElements> alloptions = select.getOptions();
	            for (PageElements element : alloptions)
	            {
	                  if(element.getText().equalsIgnoreCase(template)){
	                        element.click();
	                        break;
	                  } 
	                }    
	    		Select select1 = new Select(browser.getElement(ElementTag.selectViewAllFields));
	    		boolean exists1 = false;
	            List<PageElements> alloptions1 = select1.getOptions();
	            for (PageElements element : alloptions1)
	            {
	                  if(element.getText().equalsIgnoreCase(allfields)){
	                        element.click();
	                        break;
	                  } 
	                }     
	    		browser.getElement(ElementTag.bttnViewAddtoArea1).click();
	    		
	    	 	try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	 	
	    		browser.getElement(ElementTag.bttnOkTextViewPage).click();
	    		browser.switchTo().parentWindow(BaseWindowHandle);
	        	browser.getElement(ElementTag.bttnSaveEditViews).click(); 
	        	String message ="";
	        	try{
	        		message = browser.getElement("message", By.classname).getText();
	        	}
	        	catch(Exception e){
	        		message="error message after save caption type";
	        		Log.info("Error while editing caption Type.");
	        	}
	        	browser.getElement(ElementTag.bttnSaveEditingCaptionType).click();
	        		   
	        	browser.getElement(ElementTag.linkACaptionTypeManager).click();
	        	browser.getElement(ElementTag.bttnRegenerateAllViewsAndSearchPage).click();
	        	try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return message;
	    	}

		@Override
		public void recentMyOrderCDROM(String Shipto, String Organization,
				String Address, String City, String State, String Country,
				String ZipCode, String AuxiliaryPostalCode, String CarrierCode,
				String CustomerPO, String Specialinstructions) {
			// TODO Auto-generated method stub
			
		}

		
}
