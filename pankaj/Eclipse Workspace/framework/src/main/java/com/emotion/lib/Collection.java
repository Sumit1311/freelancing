package com.emotion.lib;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 DEC 2014
 */

import java.util.List;

import org.openqa.selenium.WebElement;

import com.emotion.core.Browser;
import com.emotion.core.By;
import com.emotion.core.ExpectedCondition;
import com.emotion.core.FrameworkException;
import com.emotion.core.Locater;
import com.emotion.core.Log;
import com.emotion.core.PageElements;
import com.emotion.core.Select;
import com.emotion.external.ElementTag;

public class Collection extends Locater implements IMMOD.ICollection{
	
	private Browser browser = null;
	private MMOD mmod = null;
	
	public Collection(Browser browser) {
		this.browser=browser;
		mmod = new MMOD(browser);
	}
		
	@Override
	public void selectCollection(String name) {
		mmod.goToHomePage();
		boolean collectionFound =false;
		List<PageElements> colls = browser.getElement(ElementTag.listCollectionTree).getElements("a",By.tagname);
		for (PageElements element : colls) {
			if(element.getText().equalsIgnoreCase(name)){
				element.click();
				collectionFound = true;
				break;
			}
		}
		if(collectionFound==false)
			throw new FrameworkException("Collection not found: " + name);
	}
	@Override
	public String getTransactionId() {
		PageElements status = browser.getElement(ElementTag.tableTransactionDetails);
		String text = status.getText();
		String id = null;
		if(text.contains("Id : ")){
			String[] params = text.split("\n");
			for(String param : params){
				if (param.startsWith("Id")){
					id = param.substring(param.indexOf("Id :")+4).trim();				
					break;
				}
			}
		}			
	return id;
	}

	@Override
	public String getExportId(){
		String exportDetails = browser.getElement(ElementTag.labelStatusDetails).getElement("h4", By.tagname).getText();
		String[] exportId = exportDetails.split(":");
		return exportId[1].trim();
	}

	@Override
	public void changeCollectionView(String view) {
		//if(view.toLowerCase().equals(CollectionViews.IMAGEONLY)){
			//browser.getElement(ElementTag.bttnImageView).click();
		//}else if (view.toLowerCase().equals(CollectionViews.IMAGETEXT)){
			browser.getElement(ElementTag.bttnImageTextView).click();
		//}
	}


	@Override
	public void getPages() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToPage(int page) {
		// TODO Auto-generated method stub
		
	}
	
/*	//TODO : Add code to select element as per collection view
	@Override
	public void selectAssets(String[] assetNames,String view) {
		for (String asset : assetNames) {
			getAssetCheckBox(getAssetNumber(asset, view)).click(); 
		}
	}*/
	public void selectAssets(String [] captionIds, String view) {
		//if (!view.contains("imagetext")
		//changeCollectionView(view);
		for (String captionId : captionIds) {
			getAssetCheckBox(getAssetNumber(captionId, view)).click(); 
		}
	}
	
	protected PageElements getAssetCheckBox(int assetNumber)
	{
		getElementLocater(ElementTag.imgAssetCheckbox);
		return browser.getElement(getElementLocater() + (assetNumber - 1) , getBy());
	}
	
/*	@Override
	public void selectAsset(String assetName, String view) {
		selectAssets(new String[] {assetName}, view);
	}*/
	@Override
	public void selectAsset(String captionId, String view) {
		selectAssets(new String[] {captionId}, view);
	}
	//TODO : Add code to select element as per collection view
/*	@Override
	public int getAssetNumber(String assetName, String view) {
		PageElements element = browser.getElement(ElementTag.listAssets);
		List<PageElements> lielements = element.getElements("li",By.tagname);
		if (lielements.size()==1)
		{
			return 0;
		}
		int i=1;
		for (PageElements li : lielements) {
			String assetname = li.getText().trim();
			if(assetname.equalsIgnoreCase(assetName)){
				return i;
			}
			i++;
		}
		throw new FrameworkException("No Assest found with name " + assetName);
	}*/
	@Override
	public int getAssetNumber(String captionId, String view) {
		PageElements element = browser.getElement(ElementTag.listAssets);
		List<PageElements> lielements = element.getElements("li",By.tagname);
		if (lielements.size()==1)
		{
			return 0;
		}
		int i=1;
		for (PageElements li : lielements) {
			String caption = li.getText().trim();
			
			//if(caption.equalsIgnoreCase(captionId.toString())){
			if(caption.toLowerCase().contains("caption id")){
			if(caption.contains(captionId)){
				return i;
			}
			i++;
			}
			else throw new FrameworkException("The view - "+ view +" doesnot contain caption Id field.");
		}
		throw new FrameworkException("No Assest found with caption id: " + captionId);
	}
	
	@Override
	public void assetPowerbarOperation(String asset, String operation) {
		// TODO: Write code to handle asset powerbar operations
		
	}

	private void selectOperationFromDropDown(String Operation)
	{
		Select select =  new Select(browser.getElement(ElementTag.optAssestCommands));
		select.selectByVisibleText(Operation);
		browser.getElement(ElementTag.bttnRunCommand).click();
	}
	
	@SuppressWarnings("finally")
	@Override
	public String downloadAssets(String[] captionIds, String view, String transformType) {
		if(view.toLowerCase().equals(CollectionViews.IMAGEONLY))
			browser.getElement(ElementTag.bttnImageTextView).click(); //fetching caption type from imagetext view
		selectAssets(captionIds, view);
		selectOperationFromDropDown(AssetOperations.DOWNLOAD);
		Select select =  new Select(browser.getElement(ElementTag.optSelectTranformationType));
		if (!transformType.trim().startsWith("Original"))
			select.selectByVisibleText(transformType.trim());
		browser.getElement(ElementTag.bttnDoTransformation).click();
		//TODO : Handle Download window in case of some transformation type
		String msg=null;
		try{
		msg = browser.getElement(ElementTag.msgError).getText();
		}catch(Exception e){
			msg = "No message found";
			Log.info("Error Message not found on page");
			//throw new FrameworkException("Error message not found on page");
		}
		finally{
		return msg;
		}	
	}

	@Override
	public String transmitAssets(String[] captionIds,String view,String to,boolean ccSelf,
			String subject,String textMessage,String ToUsers, String transformType) {
		selectAssets(captionIds, view);
		selectOperationFromDropDown(AssetOperations.TRANSMIT);
		if(to!=null){
			PageElements ele=browser.getElement(ElementTag.txtTransmitTo);
			ele.clear();
			ele.sendKeys(to);
		}
		PageElements chkbox = browser.getElement(ElementTag.chkBoxTransmitCCSelf);
		if (ccSelf){
			if(!chkbox.isSelected()){
				chkbox.click();
			}
		}else{
			if(chkbox.isSelected()){
				chkbox.click();
			}
		}
		if (subject!=null){
			PageElements ele=browser.getElement(ElementTag.txtTransmitSubject);
			ele.clear();
			ele.sendKeys(subject);
		}
		if (textMessage!=null){
			PageElements ele=browser.getElement(ElementTag.txtTransmitMessage);
			ele.clear();
			ele.sendKeys(textMessage);
		}
		if (ToUsers!=null){
			String[] users = ToUsers.split(",");
			//selectTransmitToUsers(users);
		}
		if (transformType!=null){
			selectAssetTranformation(transformType);
		}
		browser.getElement(ElementTag.bttnTransmitSend).click();
		if(browser.getPageSource().contains(Messages.TransmitSuccess))
			return browser.getElement(ElementTag.labelTransmitAssetMsg).getText();
		else
			return browser.getElement("errorMess", By.classname).getText();
		
	}
	
//	Himanshus function :xpath formation not working
 private void selectAssetTranformation(String transformType)
 {
	 
	 String transformTypeId = null;
	 if (transformType.contains("Power Point"))
	 {
		 transformTypeId ="1004_image";
	 }
	 if (transformType.contains("Original"))
	 {
		 transformTypeId ="0_image";
	 }
	 if (transformType.contains("Print Comping"))
	 {
		 transformTypeId ="1003_image";
	 }
	 if (transformType.contains("Web Ready"))
	 {
		 transformTypeId ="1002_image";
	 }
	 if (transformType.contains("Print") && transformType.contains("Creative"))
	 {
		 transformTypeId ="10000_image";
	 }
	 if (transformType.contains("All Transformations"))
	 {
		 transformTypeId ="1000_image";
	 }
	 	 
	 String transfomXpath = "//input[@name='" + transformTypeId + "']";
		 
	 List<PageElements> checkboxes = browser.getElements(transfomXpath, By.xpath);
	 for (PageElements checkbox : checkboxes) {
		 checkbox.click();
	 } 
 }
	/*{
		//TODO: need to write code for selecting asset and transformation type
		//Select Assets first
		List<PageElements> elements = browser.getElements(ElementTag.chekboxTransmitAssets);
		for (PageElements ele : elements) {
			ele.click();
		}
		String transformTypeId = null;
		List<PageElements> captionIds = browser.getElements(ElementTag.labelTransmitAssetsId);
		for (PageElements captionId : captionIds) {
			String transfomXpath = "//input[@value='" + captionId + "_" + transformTypeId + "']";
			browser.getElement(transfomXpath, By.xpath).click();
		}
	}*/
	
	//selectTransmitToUsers not working
	private void selectTransmitToUsers(String [] Users)
	{
		String BaseWindowHandle=browser.switchTo().getBaseWindowHandle();
		browser.getElement(ElementTag.bttnTransmitSelectUsers).click();
		browser.switchTo().window("Select Users");
		Select select =  new Select(browser.getElement(ElementTag.optTransmitRecieverUsername));
		for (String user : Users) {
			select.selectUser(user);
			browser.getElement(ElementTag.bttnTransmitAddMembers).click();
		}
		
		browser.switchTo().parentWindow(BaseWindowHandle);
	}
	@Override
	public void fillAnswers(String answer1, String answer2, String answer3, String answer4){
		WebElement element =  (WebElement) browser.manageWait().explicitlyWait(ElementTag.txtQuestion1
				, ExpectedCondition.visibilityOfElementLocated, 5, null);
		
		if(element.isDisplayed()){
		browser.getElement(ElementTag.txtQuestion1).sendKeys(answer1);
		browser.getElement(ElementTag.txtQuestion2).sendKeys(answer2);
		browser.getElement(ElementTag.txtQuestion3).sendKeys(answer3);
		Select select =  new Select(browser.getElement(ElementTag.ddlQuestion4));
		select.selectByVisibleText(answer4);
		browser.getElement(ElementTag.bttnNext).click();
		}
	}
	@Override
	public void selectDelivery(String delivery, String project) {
/*		WebElement element =  (WebElement) browser.manageWait().explicitlyWait(ElementTag.optDeliveryMethodAddToProject
				, ExpectedCondition.visibilityOfElementLocated, 5, null);*/
/*		
		if(element.isDisplayed()){
		if (delivery.toLowerCase().trim().contains("addtoproject") && project !=null && !project.isEmpty() )
		{
			browser.getElement(ElementTag.optDeliveryMethodAddToProject).click();
			Select selectproject = new Select(browser.getElement(ElementTag.ddlProject));
			selectproject.selectByVisibleText(project);		
		}
		else
		{*/
			switch(delivery){
			case("cdrom"):{
				browser.getElement(ElementTag.optDeliveryMethodCDRom).click();
				break;}			
			case("email"):{
				browser.getElement(ElementTag.optDeliveryMethodEmail).click();
				break;}
			case("online"):{
				browser.getElement(ElementTag.optDeliveryMethodOnline).click();
				break;}
			case("source"):{
				browser.getElement(ElementTag.optDeliveryMethodSource).click();
				break;}
			//}			
		}
		browser.getElement(ElementTag.bttnContinue1).click();
		//}
	}
	@Override
	public void selectDeliveryFormat(String tranformType) {
		if (!tranformType.trim().startsWith("Original Format")){
		WebElement element =  (WebElement) browser.manageWait().explicitlyWait(ElementTag.ddlDeliveryFormat0
				, ExpectedCondition.visibilityOfElementLocated, 5, null);
		boolean isd =element.isDisplayed();
		if(element.isDisplayed()){
		Select selectDelivery0= new Select(browser.getElement(ElementTag.ddlDeliveryFormat0));
		selectDelivery0.selectByVisibleText(tranformType);
		Select selectDelivery1= new Select(browser.getElement(ElementTag.ddlDeliveryFormat1));
		selectDelivery1.selectByVisibleText(tranformType);
		}
		browser.getElement(ElementTag.bttnContinue2).click();
		}
			
	}
	
	@Override
	public void orderAssets(String[] captionIds,String view, String tranformType,
			String answer1, String answer2, String answer3, String answer4, String delivery, String project ) {
	
		selectAssets(captionIds, view);
		selectOperationFromDropDown(AssetOperations.ORDER);

		fillAnswers(answer1,answer2,answer3,answer4);
		
		//on the Select Delivery Method page
		browser.getElement(ElementTag.bttnOrderNow).click();
		selectDelivery(delivery, project);
		
		//next page
		if (!(delivery.toLowerCase().trim().contains("addtoproject")))
			selectDeliveryFormat(tranformType);		
	}

	@Override
	public void AddToCartAndOrder(String[] captionIds,String view, String tranformType,
			String answer1, String answer2, String answer3, String answer4, String delivery, String project ) {
		
		selectAssets(captionIds, view);
		selectOperationFromDropDown(AssetOperations.ORDER);
		fillAnswers(answer1,answer2,answer3,answer4);
		browser.getElement(ElementTag.bttnAddToCart).click();
		
		WebElement element =  (WebElement) browser.manageWait().explicitlyWait(ElementTag.linkSelectAllCart
					, ExpectedCondition.visibilityOfElementLocated, 5, null);
			
		if(element.isDisplayed())
			browser.getElement(ElementTag.linkSelectAllCart).click();
		
		WebElement element2 =  (WebElement) browser.manageWait().explicitlyWait(ElementTag.bttnOrderSelectedCart
					, ExpectedCondition.visibilityOfElementLocated, 5, null);
			
		if(element2.isDisplayed())
			browser.getElement(ElementTag.bttnOrderSelectedCart).click();
		selectDelivery(delivery, project);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!(delivery.toLowerCase().trim().contains("addtoproject")))
			selectDeliveryFormat(tranformType);	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getAssetDescription(String captionId, String view) {
		String description = "";
		PageElements element = browser.getElement(ElementTag.listAssets);
		List<PageElements> lielements = element.getElements("li",By.tagname);

		for (PageElements li : lielements) {
			String caption = li.getText().trim();
			
			//if(caption.equalsIgnoreCase(captionId.toString())){
			if(caption.toLowerCase().contains("caption id")){
			if(caption.contains(captionId)){
				String[] rows = caption.split("\n");
				for (String row: rows){
					if(row.startsWith("Description"))
						return row.substring(12);			
				}				
				return description;
			}
			}
			else throw new FrameworkException("The view - "+ view +" doesnot contain caption Id field.");
		}
		throw new FrameworkException("No Assest found with caption id: " + captionId);
	}

	@Override
	public String captionAsset(String collection_name, String captionId,String view, String description, String category) {
		selectAsset(captionId, view);
		selectOperationFromDropDown(AssetOperations.CAPTION);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		browser.getElement(ElementTag.txtCaptionDescription).getElement("textarea", By.tagname).clear();
		browser.getElement(ElementTag.txtCaptionDescription).getElement("textarea", By.tagname).sendKeys(description);
		
		
		Select select =  new Select(browser.getElement("small_02_"+captionId, By.id));
		select.selectByVisibleText(category);		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		browser.getElement(ElementTag.bttnCaptionGo).click();
		selectCollection(collection_name);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getAssetDescription(captionId, view);		
	}

	@Override
	public String moveAssets(String[] captionIds,String view,String toCollection,String taskName,String taskDescription) {
		selectAssets(captionIds, view);
		selectOperationFromDropDown(AssetOperations.MOVE);
		Select select =  new Select(browser.getElement(ElementTag.optSelectCopyCollection));
		select.selectByVisibleText(toCollection);
	
		browser.getElement(ElementTag.bttnCopyContinue).click();
		if (taskName!=null)	{
			PageElements ele =  browser.getElement(ElementTag.txtCopyTaskName);
			ele.clear();
			ele.sendKeys(taskName);
		}
		if (taskDescription!=null) {
			PageElements ele =  browser.getElement(ElementTag.txtCopyTaskDescription);
			ele.clear();
			ele.sendKeys(taskDescription);
		}
		browser.getElement(ElementTag.bttnCreateCopyTask).click();
		return browser.getElement(ElementTag.labelMsgMigration).getText();
	}

	@Override
	public String copyAssets(String[] captionIds,String view,String toCollection, boolean asShortcut,String taskName,String taskDescription) {
		selectAssets(captionIds, view);
		selectOperationFromDropDown(AssetOperations.COPY);
		Select select =  new Select(browser.getElement(ElementTag.optSelectCopyCollection));
		select.selectByVisibleText(toCollection);
		PageElements chkbox = browser.getElement(ElementTag.checkboxCopyAsShortcut);
		if (asShortcut)	{
			if (!chkbox.isSelected()){
				chkbox.click();
			}
		}else{
			if(chkbox.isSelected()) {
				chkbox.click();
			}
		}
		browser.getElement(ElementTag.bttnCopyContinue).click();
		if (taskName!=null)	{
			PageElements ele =  browser.getElement(ElementTag.txtCopyTaskName);
			ele.clear();
			ele.sendKeys(taskName);
		}
		if (taskDescription!=null) {
			PageElements ele =  browser.getElement(ElementTag.txtCopyTaskDescription);
			ele.clear();
			ele.sendKeys(taskDescription);
		}
		browser.getElement(ElementTag.bttnCreateCopyTask).click();
		return browser.getElement(ElementTag.labelMsgMigration).getText();
	}
	
	private void setDeleteAssetSettings(boolean set,PageElements checkbox,PageElements txtbox,String msg)
	{
		if(set){
			if(!checkbox.isSelected())
			{
				checkbox.click();
				if (msg!=null)
				{
					txtbox.clear();
					txtbox.sendKeys(msg);
				}
			}
		}else {
			if(checkbox.isSelected())
			{
				checkbox.click();
			}
		}
	}

	@Override
	public String deleteAssets(String[] captionIds,String view,boolean sendMsgToLightbox, 
			boolean sendMsgToCart, boolean sendMsgToProject,String lightboxMsg, String cartMsg,String projectMsg) {
		selectAssets(captionIds, view);
		selectOperationFromDropDown(AssetOperations.DELETE);
		PageElements checkbox = browser.getElement(ElementTag.chkboxSendLightBoxMessage);
		PageElements txtbox = browser.getElement(ElementTag.txtAssetDeleteLightboxMsg);
		setDeleteAssetSettings(sendMsgToLightbox, checkbox, txtbox, lightboxMsg);
		checkbox = browser.getElement(ElementTag.chkboxSendCartMessage);
		txtbox = browser.getElement(ElementTag.txtAssetDeleteCartMsg);
		setDeleteAssetSettings(sendMsgToCart, checkbox, txtbox, cartMsg);
		checkbox = browser.getElement(ElementTag.chkboxSendProjectMessage);
		txtbox = browser.getElement(ElementTag.txtAssetDeleteProjectMsg);
		setDeleteAssetSettings(sendMsgToProject, checkbox, txtbox, projectMsg);
		browser.getElement(ElementTag.bttnTrashItems).click();
		return browser.getElement(ElementTag.labelDeleteAssetMsg).getText();
	}

	@Override
	public String exportMetadata(String[] captionIds,String view,String fileName) {
		selectAssets(captionIds, view);
		selectOperationFromDropDown(AssetOperations.EXPORT_METADATA);
		browser.getElement(ElementTag.txtExportName).clear();
		browser.getElement(ElementTag.txtExportName).sendKeys(fileName);
		browser.getElement(ElementTag.bttnExportProceed).click();
		WebElement element =  (WebElement) browser.manageWait().explicitlyWait(ElementTag.labelExportStatus
				, ExpectedCondition.visibilityOfElementLocated, 5, null);
		if(element.isDisplayed()){
			return element.getText();
		}
		throw new FrameworkException("Export MetaData failed!");
	}

	@Override
	public String exportAllMetadata(String fileName) {
		selectOperationFromDropDown(AssetOperations.EXPORT_ALL_METADATA);
		browser.getElement(ElementTag.txtExportName).clear();
		browser.getElement(ElementTag.txtExportName).sendKeys(fileName);
		browser.getElement(ElementTag.bttnExportProceed).click();
		WebElement element =  (WebElement) browser.manageWait().explicitlyWait(ElementTag.labelExportStatus
				, ExpectedCondition.visibilityOfElementLocated, 5, null);
		if(element.isDisplayed()){
			return element.getText();
		}
		throw new FrameworkException("Export All MetaData failed!");
	}

	@Override
	public String reIndexAssets(String[] captionIds,String view) {
		selectAssets(captionIds, view);
		selectOperationFromDropDown(AssetOperations.REINDEX);
		WebElement element =  (WebElement) browser.manageWait().explicitlyWait(ElementTag.labelWinAlert
				, ExpectedCondition.visibilityOfElementLocated, 5, null);
		if(element.isDisplayed()){
			String msg = element.getText();
			browser.getElement(ElementTag.bttnYuiAlert).click();
			return msg;
		}
		throw new FrameworkException("Re Index Asset failed!");
	}

	@Override
	public void createBrowsable(String[] captionIds,String view) {
		selectAssets(captionIds, view);
		selectOperationFromDropDown(AssetOperations.CREATE_BROWSABLES);
		
	}

	@Override
	public void printContactSheet(String[] captionIds,String view) {
		selectAssets(captionIds, view);
		selectOperationFromDropDown(AssetOperations.PRINT_CONTACT_SHEET);
		//browser.getElement(ElementTag.linkTransactionIdContactSheet).click();
	}

	@Override
	public void selectAllAssets() {
		browser.getElement(ElementTag.bttnCollectionSelectAllTop).click();
	}
	
	@Override
	public boolean isVisibleInPickup(String id){
		boolean isPresent=false;
		List<PageElements> gridItems = browser.getElement(ElementTag.dgdataGrid).getElements("tr", By.tagname);//("gd_item",By.classname);("gd_alternate",By.classname);
		for (PageElements element : gridItems) {
			if(element.getText().startsWith(id)){
				isPresent=true;
				break;
			}
		}
		return isPresent;		
	}
	@Override
	public String getStatusInPickup(String id){
		String status= null;
		List<PageElements> gridItems = browser.getElement(ElementTag.dgdataGrid).getElements("tr", By.tagname);//("gd_item",By.classname);("gd_alternate",By.classname);
		for (PageElements element : gridItems) {
			String text = element.getText();	
			if(text.startsWith(id)){
				//String[] params = text.split(" ");
				//status = params[1];
				status=text;
				break;
				}
			}
		return status;
	}

/*	@Override
	public String CopyAsset(String collection_name, String[] captionIds,
			String destination_collection_name, String view) {
		selectAssets(captionIds, view);
		selectOperationFromDropDown(AssetOperations.COPY);
		
		
		return null;
	}*/
}

