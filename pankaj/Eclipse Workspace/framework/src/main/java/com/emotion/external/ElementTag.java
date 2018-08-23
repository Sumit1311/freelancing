package com.emotion.external;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 DEC 2014
 *	Class to mention element tag (XML tags) for page object repository file
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
	public static final String linkCaptionTypeManager= "linkCaptionTypeManager";

	//Captiontype page
    public static final String txtNewCaptionType="txtNewCaptionType";
    public static final String bttnCreateCaptionType="bttnCreateCaptionType";
    public static final String listExistingCaptionType= "listExistingCaptionType";
    public static final String bttnCaptionTypeEdit= "bttnCaptionTypeEdit";
    public static final String bttnViews= "bttnViews";
    public static final String bttnEditImagesText= "bttnEditImagesText";
    public static final String listExistingfields= "listExistingfields";
    public static final String bttnAddToArea1= "bttnAddToArea1";
    public static final String bttnOkAddArea= "bttnOkAddArea";
    public static final String bttnSaveView= "bttnSaveView";
    public static final String bttnSaveEditCaptionType= "bttnSaveEditCaptionType";
    public static final String linkACaptionTypeManager= "linkACaptionTypeManager";
    public static final String listTemplateSelect= "listTemplateSelect";
    public static final String selectViewAllFields= "selectViewAllFields";
    public static final String bttnViewAddtoArea1= "bttnViewAddtoArea1";
    public static final String bttnOkTextViewPage= "bttnOkTextViewPage";
    public static final String bttnSaveEditViews= "bttnSaveEditViews";
    public static final String bttnSaveEditingCaptionType= "bttnSaveEditingCaptionType";
    public static final String bttnRegenerateAllViewsAndSearchPage="bttnRegenerateAllViewsAndSearchPage";
    public static final String bttnCloneCaptionType="bttnCloneCaptionType";
    public static final String bttnSaveCloneCaptionType="bttnSaveCloneCaptionType";
    public static final String bttnMetadataCaptionType= "bttnMetadataCaptionType";
    public static final String bttnMetadataAddnewField= "bttnMetadataAddnewField";
    public static final String txtAddNewMetaDatafield= "txtAddNewMetaDatafield";
    public static final String txtDescriptionMetaDataAddfield= "txtDescriptionMetaDataAddfield";
    public static final String listMethodSelectMetaDataAddfield= "listMethodSelectMetaDataAddfield";
    public static final String listSizeSelectMetaDataAddfield= "listSizeSelectMetaDataAddfield";
    public static final String listSortableSelectMetaDataAddfield= "listSortableSelectMetaDataAddfield";
    public static final String listMetadatatypeSelectMetaDataAddfield= "listMetadatatypeSelectMetaDataAddfield";
    public static final String listMetadataPropertieSelect= "listMetadataPropertieSelect";
    public static final String txtDefaultEnterMetaDatafield= "txtDefaultEnterMetaDatafield";
    public static final String chkboxRequiredFieldValidation= "chkboxRequiredFieldValidation";
    public static final String chkboxNumberFieldValidation= "chkboxNumberFieldValidation";
    public static final String chkboxDisplayURLsFieldValidation= "chkboxDisplayURLsFieldValidation";
    public static final String optTextEntryMetadaAddfield= "optTextEntryMetadaAddfield";
    public static final String optTextAreaMetadaAddfield= "optTextAreaMetadaAddfield";
    public static final String optRedioButtonMetadaAddfield= "optRedioButtonMetadaAddfield";
    public static final String optcheckboxMetadaAddfield= "optcheckboxMetadaAddfield";
    public static final String bttnOkMetadaAddfield= "bttnOkMetadaAddfield";
    public static final String bttnSaveMetadataAddnewField= "bttnSaveMetadataAddnewField";
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
	
	public static final String msgError= "msgError";
	//Export Metadata Page elements
	public static final String optSelectTranformationType= "optSelectTranformationType";
	public static final String bttnDoTransformation= "bttnDoTransformation";
	public static final String bttnDownloadReturn= "bttnDownloadReturn";
	
	//Export Metadata Page elements
	public static final String txtExportName= "txtExportName";
	public static final String bttnExportProceed= "bttnExportProceed";
	public static final String bttnExportCancel= "bttnExportCancel";
	public static final String labelExportStatus= "labelExportStatus";
	public static final String labelStatusDetails= "labelStatusDetails";
	
	
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
	public static final String labelTransmitAssetMsg="labelTransmitAssetMsg";
	
	//Captioning page
	public static final String txtCaptionDescription = "txtCaptionDescription";
	public static final String bttnCaptionGo = "bttnCaptionGo";
	
	//Order Assets Pages
	
	public static final String txtQuestion1="txtQuestion1";
	public static final String txtQuestion2="txtQuestion2";
	public static final String txtQuestion3="txtQuestion3";
	public static final String ddlQuestion4="ddlQuestion4";
	public static final String bttnNext="bttnNext";
	public static final String bttnOrderNow="bttnOrderNow";
	public static final String bttnAddToCart="bttnAddToCart";
	public static final String ddlProject="ddlProject";
	public static final String optDeliveryMethodAddToProject="optDeliveryMethodAddToProject";
	public static final String optDeliveryMethodCDRom="optDeliveryMethodCDRom";
	public static final String optDeliveryMethodEmail="optDeliveryMethodEmail";
	public static final String optDeliveryMethodOnline="optDeliveryMethodOnline";
	public static final String optDeliveryMethodSource="optDeliveryMethodSource";
	
	public static final String bttnContinue1="bttnContinue1";
	public static final String ddlDeliveryFormat0="ddlDeliveryFormat0";
	public static final String ddlDeliveryFormat1="ddlDeliveryFormat1";
	public static final String bttnContinue2="bttnContinue2";
	public static final String linkSelectAllCart="linkSelectAllCart";
	public static final String bttnOrderSelectedCart="bttnOrderSelectedCart";
	
	public static final String labelShipmentNumber="labelShipmentNumber";
	
	//Order Management 
	public static final String bttnRecentOrders="bttnRecentOrders";
	public static final String optCDRomDelivery="optCDRomDelivery";
	public static final String bttnContinueDelivery="bttnContinueDelivery";
	public static final String txtOrderShipTo="txtOrderShipTo";
	public static final String txtOrganizationOrder="txtOrganizationOrder";
	public static final String txtAddressOrder="txtAddressOrder";
	public static final String txtCityOrder="txtCityOrder";
	public static final String listStateOrder="listStateOrder";
	public static final String txtZipCodeOrder="txtZipCodeOrder";
	
	
	//Pickup page
	public static final String tableTransactionDetails="tableTransactionDetails";
	public static final String linkPickup="linkPickup";
	public static final String linkPickupOrder="linkPickupOrder";
	public static final String linkPickupDownLoads="linkPickupDownLoads";
	public static final String linkPickupExportedMetadata="linkPickupExportedMetadata";
	public static final String dgdataGrid="dgdataGrid";
	
	//Admin page elements
	public static final String linkAdmin= "linkAdmin";
	public static final String linkCollectionManager= "linkCollectionManager";


	//Collection Manager Page elements
	public static final String bttnCreateCollection= "bttnCreateCollection";
	public static final String tableCollection= "tableCollection";
	public static final String bttnDeleteCollection= "bttnDeleteCollection";
	public static final String bttnEditCollection= "bttnEditCollection";
	public static final String bttnEditPermissionsCollection= "bttnEditPermissionsCollection";
	public static final String bttnCommitDeleteCollection= "bttnCommitDeleteCollection";
	
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

	//Manage Users
	public static final String linkManageUsers= "linkManageUsers";
	public static final String bttnSearch= "bttnSearch";
	public static final String txtUsername = "txtUsername";
	public static final String txtFirstname = "txtFirstname";
	public static final String txtLastname = "txtLastname";
	public static final String ddlCategory = "ddlCategory";
	public static final String ddlStatus = "ddlStatus";
	public static final String ddlOrgId = "ddlOrgId";
	public static final String ddlCountry = "ddlCountry";
	public static final String ddlCity = "ddlCity";
	public static final String txtPhone = "txtPhone";
	
	//Add User
	public static final String bttnAddUser = "bttnAddUser";
	public static final String txtAddUserLogin = "txtAddUserLogin";
	public static final String txtAddUserPwd = "txtAddUserPwd";
	public static final String txtAddUserConfPassword = "txtAddUserConfPassword";
	public static final String txtAddUserFirst = "txtAddUserFirst";
	public static final String txtAddUserMiddle = "txtAddUserMiddle";
	public static final String txtAddUserLast = "txtAddUserLast";
	public static final String ddlAddUserRole = "ddlAddUserRole";
	public static final String ddlAddUserStatus = "ddlAddUserStatus";
	public static final String ddlAddUserOrganization = "ddlAddUserOrganization";
	public static final String txtAddUserAddress1 = "txtAddUserAddress1"; 
	public static final String txtAddUserCity = "txtAddUserCity";
	public static final String txtAddUserState = "txtAddUserState";
	public static final String txtAddUserZip = "txtAddUserZip";
	public static final String ddlAddUserCountry = "ddlAddUserCountry";
	public static final String txtAddUserEmail = "txtAddUserEmail";
	public static final String txtAddUserPhone = "txtAddUserPhone";
	public static final String bttnAddUserSaveExit = "bttnAddUserSaveExit";
		
	//User Role Manager
	
	public static final String txtUserCategoryName = "txtUserCategoryName";
	public static final String txtUserCategoryDesc = "txtUserCategoryDesc";
	public static final String linkUserRoleManager = "linkUserRoleManager";
	public static final String listExistingUserRoles = "listExistingUserRoles";
	public static final String bttnAddUserRole = "bttnAddUserRole";
	public static final String bttnCloneUserRole = "bttnCloneUserRole";
	public static final String bttnUpdateUserRole = "bttnUpdateUserRole";
	public static final String bttnDeleteUserRole = "bttnDeleteUserRole";
	public static final String winAlertMessage = "winAlertMessage";
	public static final String bttnMessageOk = "bttnMessageOk";
	
	//AccountManagement
	public static final String linkAccountManagement= "linkAccountManagement";
	public static final String linkManageOrganizations= "linkManageOrganizations";
	public static final String bttnAddNewOrganization= "bttnAddNewOrganization";
	public static final String txtName="txtName";
	public static final String txtPointofContactFirst="txtPointofContactFirst";
	public static final String txtPointofContactMiddle="txtPointofContactMiddle";
	public static final String txtPointofContactLast="txtPointofContactLast";
	public static final String txtContactEmail="txtContactEmail";
	public static final String txtContactPhone="txtContactPhone";
	public static final String txtContactFax="txtContactFax";
	public static final String txtBillingAddress="txtBillingAddress";
	public static final String txtCity="txtCity";
	public static final String txtState="txtState";
	public static final String txtZip="txtZip";
	public static final String ddlSelectCountry="ddlSelectCountry";
	public static final String bttnCopyBillingAddress="bttnCopyBillingAddress";
	public static final String txtShippingAddress="txtShippingAddress";
	public static final String txtCity1="txtCity1";
	public static final String txtState1="txtState1";
	public static final String txtZip1="txtZip1";
	public static final String ddlSelectCountry1="ddlSelectCountry1";
	public static final String bttnAdd="bttnAdd";
	//public static final String bttnSaveandanotherOrganization="bttnSaveandanotherOrganization";
	//<bttnSaveandanotherOrganization by="xpath">//input[@value="Save & Add Another Organization"]</bttnSaveandanotherOrganization>
	public static final String bttnAddNewUser="bttnAddNewUser";
	//Permissions
	public static final String txtNewPermissionName="txtNewPermissionName";
	public static final String bttnInsert="bttnInsert";
	public static final String ddlPermissionName="ddlPermissionName";
	public static final String bttnRemove="bttnRemove";
	public static final String txtSearchUser="txtSearchUser";
	public static final String optSelectName="optSelectName";
	public static final String bttnModifyPermissions="bttnModifyPermissions";
	public static final String bttnDeleteCaptionType="bttnDeleteCaptionType";
}
