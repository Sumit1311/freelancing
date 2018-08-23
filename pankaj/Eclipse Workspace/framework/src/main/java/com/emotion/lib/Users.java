package com.emotion.lib;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emotion.core.Browser;
import com.emotion.core.By;
import com.emotion.core.Log;
import com.emotion.core.PageElements;
import com.emotion.core.Select;
import com.emotion.external.ElementTag;

public class Users implements IMMOD.IUsers
{
	private Browser browser = null;
	public Users(Browser browser){
		this.browser = browser;
	}

	@Override
	public String addUser(String login, String pwd, String confPwd,
			String first, String middle, String last, String role,
			String status, String org, String add1, String city,
			String state, String zip, String country, String email,
			String phone){
		//Click add user button on manage user page
		browser.getElement(ElementTag.bttnAddUser).click();
		//Fill form
		if(login!=null){
			browser.getElement(ElementTag.txtAddUserLogin).sendKeys(login);
		}
		if(pwd!=null || pwd!=""){
		browser.getElement(ElementTag.txtAddUserPwd).sendKeys(pwd);
		}
		if(confPwd!=null || confPwd!=""){
		browser.getElement(ElementTag.txtAddUserConfPassword).sendKeys(confPwd);
		}
		if(first!=null || first!=""){
		browser.getElement(ElementTag.txtAddUserFirst).sendKeys(first);
		}
		if(middle!=null || middle!=""){
		browser.getElement(ElementTag.txtAddUserMiddle).sendKeys(middle);
		}
		if(last!=null || last!=""){
		browser.getElement(ElementTag.txtAddUserLast).sendKeys(last);
		}
		if(role!=null || role!=""){
		Select select =  new Select(browser.getElement(ElementTag.ddlAddUserRole));
		select.selectByVisibleText(role);
		}
		if(status!=null || status!=""){
		Select select1 =  new Select(browser.getElement(ElementTag.ddlAddUserStatus));
		select1.selectByVisibleText(status);
		}
		if(org!=null || org!=""){
		Select select2 =  new Select(browser.getElement(ElementTag.ddlAddUserOrganization));
		select2.selectByVisibleText(org);
		}
		
		if(add1!=null || add1!=""){
		browser.getElement(ElementTag.txtAddUserAddress1).sendKeys(add1);
		}
		if(city!=null || city!=""){
		browser.getElement(ElementTag.txtAddUserCity).sendKeys(city);
		}
		if(state!=null || state!=""){
		browser.getElement(ElementTag.txtAddUserState).sendKeys(state);
		}
		if(zip!=null || zip!=""){
		browser.getElement(ElementTag.txtAddUserZip).sendKeys(zip);
		}
		if(country!=null || country!=""){		
		Select select3 =  new Select(browser.getElement(ElementTag.ddlAddUserCountry));
		select3.selectByVisibleText(country);
		}
		if(email!=null || email!=""){
		browser.getElement(ElementTag.txtAddUserEmail).sendKeys(email);
		}
		if(phone!=null || phone!=""){
		browser.getElement(ElementTag.txtAddUserPhone).sendKeys(phone);
		}
	
		browser.getElement(ElementTag.bttnAddUserSaveExit).click();
		String msg=null;
		try{
			msg = browser.getElement("message", By.classname).getText();
		}catch(Exception e){
			Log.info("Success Message not Found");
						
			List<PageElements> errors = browser.getElements("errorMess", By.classname);
			String errorMessage="";
			for (PageElements error : errors) {
				errorMessage = errorMessage + error.getText().trim();
				}
			msg = errorMessage;		
		}
		return msg;
	
	}
	
	public boolean isTextPresentOnPopUp(String text) {
		String manageUserPageHandle = browser.switchTo().getBaseWindowHandle();
		browser.switchTo().window("List Users");
//		String listUserPageHandle = browser.switchTo().getBaseWindowHandle();
		if (browser.getPageSource().contains(text))
		{
			browser.switchTo().parentWindow(manageUserPageHandle);
			return true;}
		else{
			browser.switchTo().parentWindow(manageUserPageHandle);
			return false; 
			}
	}

	@Override
	public void searchUser(List<String> searchquery) {
		// TODO Auto-generated method stub
		
		Select select =  new Select(browser.getElement(ElementTag.ddlCategory));
		select.selectByVisibleText(searchquery.get(0));
		browser.getElement(ElementTag.bttnSearch).click();		
		
	}
	public void searchUserByLoginName(String loginname) {
		// TODO Auto-generated method stub
		browser.getElement(ElementTag.txtUsername).clear();
		browser.getElement(ElementTag.txtUsername).sendKeys(loginname);
		browser.getElement(ElementTag.bttnSearch).click();		
	}

	@Override
	public void editUser(String UserLoginName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configurePasswordSecurity() {
		// TODO Auto-generated method stub
		
	}


}
