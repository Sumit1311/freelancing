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
		List<PageElements> colls = browser.getElement(ElementTag.listCollectionTree).getElements("a",By.tagname);
		for (PageElements element : colls) {
			if(element.getText().equalsIgnoreCase(name)){
				element.click();
				break;
			}
		}
	}

	@Override
	public void changeCollectionView(String view) {
		if(view.equals(CollectionViews.IMAGEONLY)){
			 browser.getElement(ElementTag.bttnImageView).click();
		}else if (view.equals(CollectionViews.IMAGETEXT)){
			browser.getElement(ElementTag.bttnImageTextView).click();
		}
	}

	@Override
	public void getPages() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goToPage(int page) {
		// TODO Auto-generated method stub
		
	}
	
	//TODO : Add code to select element as per collection view
	@Override
	public void selectAssets(String[] assetNames,String view) {
		for (String asset : assetNames) {
			getAssetCheckBox(getAssetNumber(asset, view)).click(); 
		}
	}
	
	protected PageElements getAssetCheckBox(int assetNumber)
	{
		getElementLocater(ElementTag.imgAssetCheckbox);
		return browser.getElement(getElementLocater() + (assetNumber - 1) , getBy());
	}
	
	@Override
	public void selectAsset(String assetName, String view) {
		selectAssets(new String[] {assetName}, view);
	}
	
	//TODO : Add code to select element as per collection view
	@Override
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
	
	@Override
	public void downloadAssets(String[] assetNames, String view, String transformType) {
		selectAssets(assetNames, view);
		selectOperationFromDropDown(AssetOperations.DOWNLOAD);
		Select select =  new Select(browser.getElement(ElementTag.optSelectTranformationType));
		select.selectByVisibleText(transformType);
		browser.getElement(ElementTag.bttnDoTransformation).click();
		//TODO : Handle Download window in case of some transformation type
	}

	@Override
	public void transmitAssets(String[] assetNames,String view,String to,boolean ccSelf,
			String subject,String textMessage,String [] ToUsers, String transformType) {
		selectAssets(assetNames, view);
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
			selectTransmitToUsers(ToUsers);
		}
		if (transformType!=null){
			selectAssetTranformation(transformType);
		}
		browser.getElement(ElementTag.bttnTransmitSend).click();
	}

	private void selectAssetTranformation(String transformType)
	{
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
	}
	
	private void selectTransmitToUsers(String [] Users)
	{
		String BaseWindowHandle=browser.switchTo().getBaseWindowHandle();
		browser.getElement(ElementTag.bttnTransmitSelectUsers).click();
		browser.switchTo().window("Select Users");
		Select select =  new Select(browser.getElement(ElementTag.optTransmitRecieverUsername));
		for (String user : Users) {
			select.selectByVisibleText(user);
		}
		browser.getElement(ElementTag.bttnTransmitAddMembers).click();
		browser.switchTo().parentWindow(BaseWindowHandle);
	}
	
	@Override
	public void orderAssets(String[] assetNames,String view, String tranformType) {
		selectAssets(assetNames, view);
		selectOperationFromDropDown(AssetOperations.ORDER);
	}

	@Override
	public void captionAssets(String[] assetNames,String view, String tranformType) {
		selectAssets(assetNames, view);
		selectOperationFromDropDown(AssetOperations.CAPTION);
	}

	@Override
	public void moveAssets(String[] assetNames,String view, String tranformType) {
		selectAssets(assetNames, view);
		selectOperationFromDropDown(AssetOperations.MOVE);
	}

	@Override
	public String copyAssets(String[] assetNames,String view,String toCollection, boolean asShortcut,String taskName,String taskDescription) {
		selectAssets(assetNames, view);
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
	public String deleteAssets(String[] assetNames,String view,boolean msgLightbox, 
			boolean msgCart, boolean msgProject,String lightboxMsg, String cartMsg,String projectMsg) {
		selectAssets(assetNames, view);
		selectOperationFromDropDown(AssetOperations.DELETE);
		PageElements checkbox = browser.getElement(ElementTag.chkboxSendLightBoxMessage);
		PageElements txtbox = browser.getElement(ElementTag.txtAssetDeleteLightboxMsg);
		setDeleteAssetSettings(msgLightbox, checkbox, txtbox, lightboxMsg);
		checkbox = browser.getElement(ElementTag.chkboxSendCartMessage);
		txtbox = browser.getElement(ElementTag.txtAssetDeleteCartMsg);
		setDeleteAssetSettings(msgCart, checkbox, txtbox, cartMsg);
		checkbox = browser.getElement(ElementTag.chkboxSendProjectMessage);
		txtbox = browser.getElement(ElementTag.txtAssetDeleteProjectMsg);
		setDeleteAssetSettings(msgProject, checkbox, txtbox, projectMsg);
		browser.getElement(ElementTag.bttnTrashItems).click();
		return browser.getElement(ElementTag.labelDeleteAssetMsg).getText();
	}

	@Override
	public String exportMetadata(String[] assetNames,String view,String fileName) {
		selectAssets(assetNames, view);
		selectOperationFromDropDown(AssetOperations.EXPORT_METADATA);
		browser.getElement(ElementTag.txtExportName).clear();
		browser.getElement(ElementTag.txtExportName).sendKeys(fileName);
		browser.getElement(ElementTag.bttnExportProceed).click();
		WebElement element =  (WebElement) browser.manageWait().explicitlyWait(ElementTag.lableExportStatus
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
		WebElement element =  (WebElement) browser.manageWait().explicitlyWait(ElementTag.lableExportStatus
				, ExpectedCondition.visibilityOfElementLocated, 5, null);
		if(element.isDisplayed()){
			return element.getText();
		}
		throw new FrameworkException("Export All MetaData failed!");
	}

	@Override
	public String reIndexAssets(String[] assetNames,String view) {
		selectAssets(assetNames, view);
		selectOperationFromDropDown(AssetOperations.REINDEX);
		WebElement element =  (WebElement) browser.manageWait().explicitlyWait(ElementTag.labelWinAlert
				, ExpectedCondition.visibilityOfElementLocated, 5, null);
		if(element.isDisplayed()){
			String msg = element.getText();
			browser.getElement(ElementTag.bttnYuiAlert).click();
			return msg;
		}
		throw new FrameworkException("Export All MetaData failed!");
	}

	@Override
	public void createBrowsable(String[] assetNames,String view) {
		selectAssets(assetNames, view);
		selectOperationFromDropDown(AssetOperations.CREATE_BROWSABLES);
		
	}

	@Override
	public void printContactSheet(String[] assetNames,String view) {
		selectAssets(assetNames, view);
		selectOperationFromDropDown(AssetOperations.PRINT_CONTACT_SHEET);
		//browser.getElement(ElementTag.linkTransactionIdContactSheet).click();
	}

	@Override
	public void selectAllAssets() {
		browser.getElement(ElementTag.bttnCollectionSelectAllTop).click();
	}

}
