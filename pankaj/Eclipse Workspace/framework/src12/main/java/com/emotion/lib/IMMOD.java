package com.emotion.lib;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 DEC 2014
 */

public interface IMMOD {
	
	public void goToCollectionManagerPage();
	public void goToHomePage();
	
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
		public int getAssetNumber(String assetName,String view);
		public void selectAssets(String[] assetNames,String view);
		public void selectAsset(String assetName,String view);
		public void assetPowerbarOperation(String asset,String operation);
		public void downloadAssets(String[] assetNames,String view,String transformType);
		public void transmitAssets(String[] assetNames,String view,String to,boolean ccSelf,String subject,String textMessage,String [] ToUsers,String transformType);
		public void orderAssets(String[] assetNames,String view,String tranformType);
		public void captionAssets(String[] assetNames,String view,String tranformType);
		public void moveAssets(String[] assetNames,String view,String tranformType);
		public String copyAssets(String[] assetNames,String view,String toCollection, boolean asShortcut,String taskName,String taskDescription);
		public String deleteAssets(String[] assetNames,String view,boolean msgLightbox, 
				boolean msgCart, boolean msgProject,String lightboxMsg, String cartMsg,String projectMsg);
		public String exportMetadata(String[] assetNames,String view,String fileName);
		public String exportAllMetadata(String fileName);
		public String reIndexAssets(String[] assetNames,String view);
		public void createBrowsable(String[] assetNames,String view);
		public void printContactSheet(String[] assetNames,String view);
		public void selectAllAssets();
		
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
}
