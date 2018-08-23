package com.emotion.lib;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.emotion.core.PageElement;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 DEC 2014
 */

public interface IMMOD {


	public void goToHomePage();
	public void goToCollectionManagerPage();
	public void goToManageUserRolePage();
	
	IAuthentication authentication();
	interface IAuthentication
	{
		public void login();
		public void login(String username, String password);
		public boolean isUserLoggedIn(String username);
		public void logOut();
	}
	
	ICollection collection();
	interface ICollection{
		public void selectCollection(String name);
		public void changeCollectionView(String view);
		public void getPages();
		public void goToPage(int page);
		public int getAssetNumber(String captionId,String view);
		//public void selectAssets(String[] assetNames,String view);
		public void selectAsset(String captionId,String view);
		public void assetPowerbarOperation(String asset,String operation);
		public String downloadAssets(String[] captionIds,String view,String transformType);
		public String transmitAssets(String[] captionIds,String view,String to,boolean ccSelf,String subject,String textMessage,String ToUsers,String transformType);
		//public void orderAssets(String[] assetNames,String view,String tranformType);
		public String captionAsset(String collection_name, String captionId,String view,String description, String category);
		//public void moveAssets(String[] captionIds,String view,String tranformType);
		public String copyAssets(String[] captionIds,String view,String toCollection, boolean asShortcut,String taskName,String taskDescription);
		public String deleteAssets(String[] captionIds,String view,boolean sendMsgToLightbox, 
				boolean sendMsgToCart, boolean sendMsgToProject,String lightboxMsg, String cartMsg,String projectMsg);
		public String exportMetadata(String[] captionIds,String view,String fileName);
		public String exportAllMetadata(String fileName);
		public String reIndexAssets(String[] captionIds,String view);
		public void createBrowsable(String[] captionIds,String view);
		public void printContactSheet(String[] captionIds,String view);
		public void selectAllAssets();

		public void orderAssets(String[] captionIds, String view, String tranformType,
				String answer1, String answer2, String answer3, String answer4,
				String delivery, String project);
		public void AddToCartAndOrder(String[] captionIds, String view,
				String tranformType, String answer1, String answer2,
				String answer3, String answer4, String delivery, String project);
		public void fillAnswers(String answer1, String answer2, String answer3,
				String answer4);
		public void selectDelivery(String delivery, String project);
		public void selectDeliveryFormat(String tranformType);
		public boolean isVisibleInPickup(String id);
		public void selectAssets(String[] captionIds, String view);
		public String getTransactionId();
		public String getStatusInPickup(String id);
		public String getExportId();
		//public String CopyAsset(String collection_name, String[] captionids,
				//String destination_collection_name, String view);
		public String moveAssets(String[] captionIds, String view,
				String toCollection, String taskName, String taskDescription);
		public String getAssetDescription(String captionId, String view);
			
	}
	
	ICollectionManager manageCollection();
	interface ICollectionManager{
		public void createCollection(String name,String description,String organisation,
				String captionType,boolean qualityCheck,String thumbnailText,String thumbnailbg,boolean watermark,String image,String position,String tile, String bluechannel);
		public void addCollectionDetails(String name,String description,
				String organisation,String captionType,boolean qualityCheck,
				String thumbnailText,String thumbnailbg,boolean watermark,
				String image,String position,String tile, String bluechannel);
		public void updateCollectionDetails(String name,String description,String archive,String license, 
				String controlTable,String deliveryType,boolean qualityCheck,String thumbnailbg,
				String thumbnailText,boolean watermark,
				String image,String position,String tile, String bluechannel);
		public String selectCollection(String name);
		public void deleteCollection(String name);
		public void editCollection(String name);
		public void updateCollection();
		public void editCollectionPermissions(String name);
		public void addMembers(String [] addMembers);
		public void removeMembers(String[] remMembers);
		public void selectMember(String[] selMembers);
		public void modifyUserPermissions(String username,boolean edit,boolean quality,boolean preview,boolean download,
				boolean order,boolean reassign,boolean transmit,boolean viewhistory,boolean managepublicurls,boolean delete);
	}
	
	ICaptionType captiontype();
    interface ICaptionType {
        public void shareCaptioType();
    	public void createCaptionType(String name);
    	
    	public boolean checkCaptionTypeExists(String newCaptionType);
    	public void selectCaptionType(String CaptionType);
    	public String editCaptionType(String view, String template, String allfields);
		public void deleteCaptioType(String newCaptionType);
	    public void cloneCaptionType(String clonecaptiontypename);
		public void metadataAddfieldCaptioType(String label, String description,
				String method, String size, String sortable,
				String metadatatype, String Metadataproperties,
				String enterdefault);
		public void  recentMyOrderCDROM(String Shipto ,String Organization ,String Address ,String City ,String State ,String Country ,String ZipCode ,String AuxiliaryPostalCode ,String CarrierCode ,String CustomerPO ,String Specialinstructions); 
	
	
    } 

	
	IUserRoles userRole();
	interface IUserRoles{
		
		public void selectUserRole(String userrole);
		public void addUserRole(String name, String description);
		public void cloneUserRole(String userrole, String newuserrole);
		public void updateUserRole(String userrole, String newdescription);
		public void deleteUserRole(String userrole);
		public boolean checkUserRoleExists(String userrole);
		public String getDescription(String userrole);
		public String readMessage();
		
		
	}
	IUsers manageUsers();
	interface IUsers{
		//public void addUser(Map<String, String> userDetails);
		public boolean isTextPresentOnPopUp(String text);
		public void searchUser(List<String> searchquery);
		public void searchUserByLoginName(String loginname);
		public void editUser(String UserLoginName);
		public void configurePasswordSecurity();
		public String addUser(String login, String pwd, String confPwd,
				String first, String middle, String last, String role,
				String status, String org, String add1, String city,
				String state, String zip, String country, String email,
				String phone);
		}
	IOrganization manageOrganization();
	public interface IOrganization {
		
		void addNewOrganization(String name, String pointofContactFirst,
				String pointofContactMiddle, String pointofContactLast,
				String contactEmail, String contactPhone, String contactFax,
				String billingAddress, String city, String state, String zip,
				String country, String shippingAddress, String city1,
				String state1, String zip1, String country1);
		void editOrganization(String name, String pointofContactFirst,
				String pointofContactMiddle, String pointofContactLast,
				String contactEmail, String contactPhone, String contactFax,
				String billingAddress, String city, String state, String zip,
				String country, String shippingAddress, String city1,
				String state1, String zip1, String country1);
	}
	
	IOrganizationAttribute manageOrganizationAttribute();
	public interface IOrganizationAttribute {
		public void updateAttributes();
		public void addNewAttribute();
		public void cloneAttribute();
		public void editAttribut();
		public void deleteAttribute();
	}
	
	IGroup manageGroup();
	public interface IGroup {
		public void addNewGroup();
		public void editGroup();
		public void deleteGroup();
		public void addMembers();
		public void removeMember();
		public void modifyPermissions();
	}
	IPermission managePermission();
	public interface IPermission {
		public void addNewPermission();
		public void removePermission();
		public void modifyPermissions();
	}
	IUserAttribute manageUserAttribute();
	public interface IUserAttribute {
		public void addNewUserAttribute();
		public void cloneUserAttribute();
		public void editUserAttribute();
		public void deleteUserAttribute();

	}		
		
	}

