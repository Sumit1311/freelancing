package com.emotion.lib;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 24 NOV 2014
 */

import java.util.List;

import com.emotion.core.Browser;
import com.emotion.core.By;
import com.emotion.core.FrameworkException;
import com.emotion.core.Locater;
import com.emotion.core.Select;
import com.emotion.external.ElementTag;
import com.emotion.core.PageElements;

public class CollectionManager extends Locater implements IMMOD.ICollectionManager
{
	private Browser browser = null;

	public CollectionManager(Browser browser)
	{
		this.browser=browser;
	}
	
	@Override
	public void createCollection(String name,String description,
			String organisation,String captionType,boolean qualityCheck,
			String thumbnailText,String thumbnailbg,boolean watermark,String image,
			String position,String tile, String bluechannel)
	{
		browser.getElement(ElementTag.bttnCreateCollection).click();
		addCollectionDetails(name, description, organisation, captionType, 
				qualityCheck, thumbnailText, 
				thumbnailbg, watermark, image, position, tile, bluechannel);
		browser.getElement(ElementTag.bttnAddCollection).click();
	}
	
	private void fillCreateCollectionDetails(String name,String description,
			String organisation,String captionType,boolean qualityCheck,
			String thumbnailText,String thumbnailbg,boolean watermark,
			String image,String position,String tile, String bluechannel)
	{
		if(name!=null){
			browser.getElement(ElementTag.txtNewCollectionName).clear();
			browser.getElement(ElementTag.txtNewCollectionName).sendKeys(name);
		}
		if(description!=null || description!=""){
			browser.getElement(ElementTag.txtAddDescription).sendKeys(description);
		}
		if(organisation!=null){
			Select select =  new Select(browser.getElement(ElementTag.selectOrganization));
			select.selectByVisibleText(organisation);
		}
		if(captionType!=null || captionType!=""){
			browser.manageWait();
			Select select =  new Select(browser.getElement(ElementTag.selectCaptiontype));
			select.selectByVisibleText(captionType);
		}
		if(qualityCheck){
			if(browser.getElement(ElementTag.chkboxQualityCheck).isSelected()==false){
			browser.getElement(ElementTag.chkboxQualityCheck).click();
			}
		}else{
			if(browser.getElement(ElementTag.chkboxQualityCheck).isSelected()==true){
			browser.getElement(ElementTag.chkboxQualityCheck).click();
			}
		}
		if(thumbnailText!=null || thumbnailText!=""){
			browser.getElement(ElementTag.linkAddThumbnailText).click();
			browser.getElement(ElementTag.ContainerThumbnailText).sendKeys(thumbnailText);
			browser.getElement(ElementTag.bttnSaveThumbnailText).click();
			
		}
		if (thumbnailbg!=null || thumbnailbg!=""){
			browser.getElement(ElementTag.txtThumbnailBgColor).sendKeys(thumbnailbg);
		}
		if(watermark){
			if(browser.getElement(ElementTag.chkboxWatermarkEnable).isSelected()==false){
				browser.getElement(ElementTag.chkboxWatermarkEnable).click();
			}
			if (image!=null){
				Select select =  new Select(browser.getElement(ElementTag.selectWatermarkImage));
				select.selectByVisibleText(image);
			}
			if (position!=null){
				Select select =  new Select(browser.getElement(ElementTag.selectWatermarkPosition));
				select.selectByVisibleText(position);
			}
			if (tile!=null){
				Select select =  new Select(browser.getElement(ElementTag.selectWatermarkTile));
				select.selectByVisibleText(tile);
			}
			if (bluechannel!=null){
				Select select =  new Select(browser.getElement(ElementTag.selectWatermarkBlueChannel));
				select.selectByVisibleText(bluechannel);
			}
		}else{
			if(browser.getElement(ElementTag.chkboxWatermarkEnable).isSelected()==true){
				browser.getElement(ElementTag.chkboxWatermarkEnable).click();
			}
		}
	}
	
	private void fillEditCollectionDetails(String name,String description,String archive,String license, 
			String controlTable,String deliveryType,boolean qualityCheck,String thumbnailbg,
			String thumbnailText,boolean watermark,
			String image,String position,String tile, String bluechannel)
	{
		if(name!=null){
			browser.getElement(ElementTag.txtEditCollectionName).clear();
			browser.getElement(ElementTag.txtEditCollectionName).sendKeys(name);
		}
		if(description!=null || description!=""){
			browser.getElement(ElementTag.txtEditDescription).clear();
			browser.getElement(ElementTag.txtEditDescription).sendKeys(description);
		}
		if (!archive.isEmpty() || !archive.equals(null)){
			Select select =  new Select(browser.getElement(ElementTag.optArchiveList));
			select.selectByVisibleText(archive);
		}
		if (license!=null || license!=""){
			Select select =  new Select(browser.getElement(ElementTag.optLicenceTypeList));
			select.selectByVisibleText(license);
		}
		if (controlTable!=null || controlTable!=""){
			Select select =  new Select(browser.getElement(ElementTag.optControlTable));
			select.selectByVisibleText(controlTable);
		}
		if (deliveryType!=null || deliveryType!=""){
			Select select =  new Select(browser.getElement(ElementTag.optDeliveryType));
			select.selectByVisibleText(deliveryType);
		}
		
		if(qualityCheck){
			if(browser.getElement(ElementTag.chkboxQualityCheck).isSelected()==false){
			browser.getElement(ElementTag.chkboxQualityCheck).click();
			}
		}else{
			if(browser.getElement(ElementTag.chkboxQualityCheck).isSelected()==true){
			browser.getElement(ElementTag.chkboxQualityCheck).click();
			}
		}
		if (thumbnailbg!=null || thumbnailbg!=""){
			browser.getElement(ElementTag.txtThumbnailBgColor).clear();
			browser.getElement(ElementTag.txtThumbnailBgColor).sendKeys(thumbnailbg);
		}
		if(thumbnailText!=null || thumbnailText !=""){
			
			browser.getElement(ElementTag.linkAddThumbnailText).click();
			browser.getElement(ElementTag.ContainerThumbnailText).sendKeys(thumbnailText);
			browser.getElement(ElementTag.bttnSaveThumbnailText).click();
			
		}
				
		if(watermark){
			if(browser.getElement(ElementTag.chkboxWatermarkEnable).isSelected()==false){
				browser.getElement(ElementTag.chkboxWatermarkEnable).click();
			}
			if (image!=null){
				Select select =  new Select(browser.getElement(ElementTag.selectWatermarkImage));
				select.selectByVisibleText(image);
			}
			if (position!=null){
				Select select =  new Select(browser.getElement(ElementTag.selectWatermarkPosition));
				select.selectByVisibleText(position);
			}
			if (tile!=null){
				Select select =  new Select(browser.getElement(ElementTag.selectWatermarkTile));
				select.selectByVisibleText(tile);
			}
			if (bluechannel!=null){
				Select select =  new Select(browser.getElement(ElementTag.selectWatermarkBlueChannel));
				select.selectByVisibleText(bluechannel);
			}
		}else{
			if(browser.getElement(ElementTag.chkboxWatermarkEnable).isSelected()==true){
				browser.getElement(ElementTag.chkboxWatermarkEnable).click();
			}
		}
	}
	
	
	@Override
	public void addCollectionDetails(String name,String description,
			String organisation,String captionType,boolean qualityCheck,
			String thumbnailText,String thumbnailbg,boolean watermark,
			String image,String position,String tile, String bluechannel)
			{
					fillCreateCollectionDetails(name,
					description, organisation, captionType, qualityCheck, 
					thumbnailText, thumbnailbg,watermark, 
					image, position, tile, bluechannel);
		}
	

	public void updateCollectionDetails(String name,String description,String archive,String license, 
			String controlTable,String deliveryType,boolean qualityCheck,String thumbnailbg,
			String thumbnailText,boolean watermark,
			String image,String position,String tile, String bluechannel)
	{
		fillEditCollectionDetails(name, description,archive, license, controlTable, deliveryType, 
			qualityCheck, thumbnailbg,thumbnailText, watermark, image, position, tile, bluechannel);
	}
	
	public String selectCollection(String name)
	{
		List<PageElements> list = browser.getElement(ElementTag.tableCollection).getElements("tr",By.tagname);
		String xpath = browser.getElementLocater();
		int i= 1; 
		for (PageElements element : list) {
			String collection = element.getText();
			if (collection.contains(name))
			{
				return xpath + "/tr[" + i + "]";
			}
			i++;
		}
		throw new FrameworkException("No such collection present:" + name);
	}
	
	@Override
	public void deleteCollection(String name)
	{
		browser.getElement(selectCollection(name) + "/td[2]/input[3]",By.xpath).click();
		browser.getElement(ElementTag.bttnCommitDeleteCollection).click();
	}
	
	@Override
	public void editCollection(String name)
	{
		browser.getElement(selectCollection(name) + "/td[2]/input[1]",By.xpath).click();
	}
	
	@Override
	public void updateCollection()
	{
		browser.getElement(ElementTag.bttnUpdateCollection).click();
	}
	
	@Override
	public void editCollectionPermissions(String name)
	{
		browser.getElement(selectCollection(name) + "/td[2]/input[2]",By.xpath).click();
	}
	
	@Override
	public void addMembers(String [] addMembers)
	{
		browser.getElement(ElementTag.bttnAddMemberCollection).click();
		Select select =  new Select(browser.getElement(ElementTag.optSelectAddUserNamesCollection));
		for (String user : addMembers) {
			select.selectByVisibleText(user);
		}
		browser.getElement(ElementTag.bttnAddSelectedUsersCollection).click();
	}
	
	@Override
	public void removeMembers(String[] remMembers)
	{
		selectMember(remMembers);
		browser.getElement(ElementTag.bttnRemoveMemberCollection).click();
	}
	
	@Override
	public void selectMember(String[] selMembers)
	{
		Select select =  new Select(browser.getElement(ElementTag.selectCollectionUsers));
		for (String user : selMembers) {
			select.selectByVisibleText(user);
		}
	}
	
	@Override
	public void modifyUserPermissions(String username, boolean edit,
			boolean quality, boolean preview, boolean download, boolean order,
			boolean reassign, boolean transmit, boolean viewhistory,
			boolean managepublicurls, boolean delete) {
		
		selectMember(new String [] {username});
		browser.getElement(ElementTag.bttnModifyPermissionCollection).click();
		PageElements chkbox = browser.getElement(ElementTag.chkboxEditCollection);
		if(edit==true && chkbox.isSelected()==false){
			chkbox.click();
		}else if(edit==false && chkbox.isSelected()==true){
			chkbox.click();
		}
		chkbox = browser.getElement(ElementTag.chkboxQualityCollection);
		if(quality==true && chkbox.isSelected()==false){
			chkbox.click();
		}else if(quality==false && chkbox.isSelected()==true){
			chkbox.click();
		}
		chkbox = browser.getElement(ElementTag.chkboxPreviewCollection);
		if(preview==true && chkbox.isSelected()==false){
			chkbox.click();
		}else if(preview==false && chkbox.isSelected()==true){
			chkbox.click();
		}
		chkbox = browser.getElement(ElementTag.chkboxDownloadCollection);
		if(download==true && chkbox.isSelected()==false){
			chkbox.click();
		}else if(download==false && chkbox.isSelected()==true){
			chkbox.click();
		}
		chkbox = browser.getElement(ElementTag.chkboxOrderCollection);
		if(order==true && chkbox.isSelected()==false){
			chkbox.click();
		}else if(order==false && chkbox.isSelected()==true){
			chkbox.click();
		}
		chkbox = browser.getElement(ElementTag.chkboxReassignCollection);
		if(reassign==true && chkbox.isSelected()==false){
			chkbox.click();
		}else if(reassign==false && chkbox.isSelected()==true){
			chkbox.click();
		}
		chkbox = browser.getElement(ElementTag.chkboxTransmitCollection);
		if(transmit==true && chkbox.isSelected()==false){
			chkbox.click();
		}else if(transmit==false && chkbox.isSelected()==true){
			chkbox.click();
		}
		chkbox = browser.getElement(ElementTag.chkboxHistoryCollection);
		if(viewhistory==true && chkbox.isSelected()==false){
			chkbox.click();
		}else if(viewhistory==false && chkbox.isSelected()==true){
			chkbox.click();
		}
		chkbox = browser.getElement(ElementTag.chkboxPublicURLCollection);
		if(managepublicurls==true && chkbox.isSelected()==false){
			chkbox.click();
		}else if(managepublicurls==false && chkbox.isSelected()==true){
			chkbox.click();
		}
		chkbox = browser.getElement(ElementTag.chkboxDeleteCollection);
		if(delete==true && chkbox.isSelected()==false){
			chkbox.click();
		}else if(delete==false && chkbox.isSelected()==true){
			chkbox.click();
		}
		browser.getElement(ElementTag.bttnSavePermissionExitCollection).click();
	}
	
}
