package com.emotion.lib;

import com.emotion.core.Browser;
import com.emotion.core.Log;
import com.emotion.external.ElementTag;
import com.emotion.lib.IMMOD.ICaptionType;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 05 DEC 2014
 */

public class MMOD implements IMMOD
{
	private Browser browser = null;
	public MMOD(Browser browser) {
		this.browser= browser;
	}

	public void goToCollectionManagerPage()
	{
		Log.info("Navigate to Collection Manager Page.");
		browser.getElement(ElementTag.linkAdmin).click();
		browser.getElement(ElementTag.linkCollectionManager).click();
	}
	
	@Override
	public void goToHomePage() {
		browser.getElement(ElementTag.linkHome).click();
	}
	public void goToCaptionManagerPage()
    {
          Log.info("Navigate to Caption Manager Page.");
          browser.getElement(ElementTag.linkAdmin).click();
          browser.getElement(ElementTag.linkCaptionTypeManager).click();
   }
	
	public void goToManageUserRolePage() {
		browser.getElement(ElementTag.linkAdmin).click();
		browser.getElement(ElementTag.linkUserRoleManager).click();		
	}
	public void goToManageUsersPage() {
		browser.getElement(ElementTag.linkAdmin).click();
		browser.getElement(ElementTag.linkManageUsers).click();		
	}
	public void goToManageOrganizationPage(){
		browser.getElement(ElementTag.linkAdmin).click();
		browser.getElement(ElementTag.linkManageOrganizations).click();		
	}
	public void goToFunctionalPermissionsPage(){
		browser.getElement(ElementTag.linkAdmin).click();
		browser.getElement(ElementTag.linkManageOrganizations).click();	
	}

	@Override
	public IAuthentication authentication() {
		return new Authentication(browser);
	}
	
	@Override
	public ICollectionManager manageCollection() {
		return new CollectionManager(browser);
	}

	@Override
	public ICollection collection() {
		return new Collection(browser);
	}
	
	@Override
	public ICaptionType captiontype(){
		return new CaptionType(browser);
	}

	@Override
	public IUserRoles userRole() {
		return new UserRoles(browser);
	}

	@Override
	public IUsers manageUsers() {
		return new Users(browser);
	}

	@Override
	public IOrganization manageOrganization() {
		// TODO Auto-generated method stub
		return new Organization(browser);
	}

	@Override
	public IOrganizationAttribute manageOrganizationAttribute() {
		// TODO Auto-generated method stub
		return new OrganizationAttribute(browser);
	}

	@Override
	public IGroup manageGroup() {
		// TODO Auto-generated method stub
		return new Group(browser);
	}

	@Override
	public IPermission managePermission() {
		// TODO Auto-generated method stub
		return new Permission(browser);
	}

	@Override
	public IUserAttribute manageUserAttribute() {
		// TODO Auto-generated method stub
		return new UserAttribute(browser);
	}

	public void goToManageOrganizationPage1() {
		browser.getElement(ElementTag.linkAdmin).click();
		browser.getElement(ElementTag.linkManageOrganizations).click();		
		
	}
	
	public boolean isTextPresentOnPage(String text) {
		if (browser.getPageSource().contains(text))
			return true;
		else
			return false;
	}

	public ICaptionType orderMana() {
		// TODO Auto-generated method stub
		return null;
	}

}

