package com.emotion.external;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 DEC 2014
 */

public interface ElementTag 
{
	//Login Page elements
	public static final String txtUserName= "txtUserName";
	public static final String txtPassword= "txtPassword";
	public static final String bttnLogin= "bttnLogin";
	public static final String linkSignOut= "linkSignOut";
	public static final String labelLoginMessage= "labelLoginMessage";
	
	//Home Page elements
	public static final String linkHome= "linkHome";
	public static final String listCollectionTree= "listCollectionTree";

	//Collection Page elements
	public static final String optAssestCommands= "optAssestCommands";
	public static final String bttnRunCommand= "bttnRunCommand";
	public static final String listAssets= "listAssets";
	public static final String bttnCollectionSelectAllTop= "bttnCollectionSelectAllTop";
	public static final String imgAssetCheckbox= "imgAssetCheckbox";
	public static final String labelWinAlert= "labelWinAlert";
	public static final String bttnYuiAlert= "bttnYuiAlert";
	public static final String bttnImageView= "bttnImageView";
	public static final String bttnImageTextView= "bttnImageTextView";
	
	//Export Metadata Page elements
	public static final String optSelectTranformationType= "optSelectTranformationType";
	public static final String bttnDoTransformation= "bttnDoTransformation";
	public static final String bttnDownloadReturn= "bttnDownloadReturn";
	
	//Export Metadata Page elements
	public static final String txtExportName= "txtExportName";
	public static final String bttnExportProceed= "bttnExportProceed";
	public static final String bttnExportCancel= "bttnExportCancel";
	public static final String lableExportStatus= "lableExportStatus";
	
	//Delete Asset page elements
	public static final String bttnTrashItems= "bttnTrashItems";
	public static final String chkboxSendLightBoxMessage= "chkboxSendLightBoxMessage";
	public static final String chkboxSendCartMessage= "chkboxSendCartMessage";
	public static final String chkboxSendProjectMessage= "chkboxSendProjectMessage";
	public static final String txtAssetDeleteLightboxMsg= "txtAssetDeleteLightboxMsg";
	public static final String txtAssetDeleteCartMsg= "txtAssetDeleteCartMsg";
	public static final String txtAssetDeleteProjectMsg= "txtAssetDeleteProjectMsg";
	public static final String labelDeleteAssetMsg= "labelDeleteAssetMsg";
	
	//Print contact sheet page elements
	public static final String linkTransactionIdContactSheet= "linkTransactionIdContactSheet";
	
	//Copy asset page elements
	public static final String optSelectCopyCollection= "optSelectCopyCollection";
	public static final String checkboxCopyAsShortcut= "checkboxCopyAsShortcut";
	public static final String bttnCopyContinue= "bttnCopyContinue";
	public static final String txtCopyTaskName= "txtCopyTaskName";
	public static final String txtCopyTaskDescription= "txtCopyTaskDescription";
	public static final String bttnCreateCopyTask= "bttnCreateCopyTask";
	public static final String bttnCancelCopyTask= "bttnCancelCopyTask";
	public static final String labelMsgMigration= "labelMsgMigration";
	
	//Transmit Page elements
	public static final String txtTransmitTo= "txtTransmitTo";
	public static final String chkBoxTransmitCCSelf= "chkBoxTransmitCCSelf";
	public static final String txtTransmitSubject= "txtTransmitSubject";
	public static final String txtTransmitMessage= "txtTransmitMessage";
	public static final String bttnTransmitSend= "bttnTransmitSend";
	public static final String bttnTransmitCancel= "bttnTransmitCancel";
	public static final String bttnTransmitSelectUsers= "bttnTransmitSelectUsers";
	public static final String txtTransmitUserFilter= "txtTransmitUserFilter";
	public static final String optTransmitRecieverUsername= "optTransmitRecieverUsername";
	public static final String bttnTransmitAddMembers= "bttnTransmitAddMembers";
	public static final String bttnTransmitRemove= "bttnTransmitRemove";
	public static final String chekboxTransmitAssets="chekboxTransmitAssets";
	public static final String labelTransmitAssetsId="labelTransmitAssetsId";
	
	//Admin page elements
	public static final String linkAdmin= "linkAdmin";
	public static final String linkCollectionManager= "linkCollectionManager";
	//Manage Users
	public static final String linkManageUsers= "linkManageUsers";
	public static final String bttnSearch= "bttnSearch";
	//Collection Manager Page elements
	public static final String bttnCreateCollection= "bttnCreateCollection";
	public static final String tableCollection= "tableCollection";
	public static final String bttnDeleteCollection= "bttnDeleteCollection";
	public static final String bttnEditCollection= "bttnEditCollection";
	public static final String bttnEditPermissionsCollection= "bttnEditPermissionsCollection";
	public static final String bttnCommitDeleteCollection= "bttnCommitDeleteCollection";
	public static final String txtUsername="txtUsername";
	
	//Create Collection page elements
	public static final String txtNewCollectionName= "txtNewCollectionName";
	public static final String txtAddDescription= "txtAddDescription";
	public static final String selectOrganization= "selectOrganization";
	public static final String selectCaptiontype= "selectCaptiontype";
	public static final String chkboxQualityCheck= "chkboxQualityCheck";
	public static final String linkAddThumbnailText= "linkAddThumbnailText";
	public static final String txtThumbnailBgColor= "txtThumbnailBgColor";
	public static final String bttnAddCollection= "bttnAddCollection";
	public static final String chkboxWatermarkEnable= "chkboxWatermarkEnable";
	public static final String selectWatermarkImage= "selectWatermarkImage";
	public static final String selectWatermarkPosition= "selectWatermarkPosition";
	public static final String selectWatermarkTile= "selectWatermarkTile";
	public static final String selectWatermarkBlueChannel= "selectWatermarkBlueChannel";
	public static final String ContainerThumbnailText="ContainerThumbnailText";
	public static final String bttnSaveThumbnailText="bttnSaveThumbnailText";
	
	//Edit collection page elements
	public static final String txtEditCollectionName="txtEditCollectionName";
	public static final String txtEditDescription="txtEditDescription";
	public static final String optArchiveList= "optArchiveList";
	public static final String optLicenceTypeList= "optLicenceTypeList";
	public static final String optControlTable= "optControlTable";
	public static final String optDeliveryType= "optDeliveryType";
	public static final String bttnUpdateCollection= "bttnUpdateCollection";
	
	//Collection permission page elements
	public static final String selectCollectionUsers= "selectCollectionUsers";
	public static final String bttnAddMemberCollection= "bttnAddMemberCollection";
	public static final String txtFilterUserCollection= "txtFilterUserCollection";
	public static final String optSelectAddUserNamesCollection= "optSelectAddUserNamesCollection";
	public static final String bttnAddSelectedUsersCollection= "bttnAddSelectedUsersCollection";
	public static final String bttnRemoveMemberCollection= "bttnRemoveMemberCollection";
	public static final String bttnModifyPermissionCollection= "bttnModifyPermissionCollection";
	public static final String chkboxEditCollection= "chkboxEditCollection";
	public static final String chkboxQualityCollection= "chkboxQualityCollection";
	public static final String chkboxPreviewCollection= "chkboxPreviewCollection";
	public static final String chkboxDownloadCollection= "chkboxDownloadCollection";
	public static final String chkboxOrderCollection= "chkboxOrderCollection";
	public static final String chkboxReassignCollection= "chkboxReassignCollection";
	public static final String chkboxTransmitCollection= "chkboxTransmitCollection";
	public static final String chkboxHistoryCollection= "chkboxHistoryCollection";
	public static final String chkboxPublicURLCollection= "chkboxPublicURLCollection";
	public static final String chkboxDeleteCollection= "chkboxDeleteCollection";
	public static final String bttnSavePermissionCollection= "bttnSavePermissionCollection";
	public static final String bttnSavePermissionExitCollection= "bttnSavePermissionExitCollection";

	
	
}
