package com.emotion.lib;

import java.util.List;

import com.emotion.core.Browser;
import com.emotion.core.By;
import com.emotion.core.PageElements;
import com.emotion.core.Select;
import com.emotion.external.ElementTag;

public class UserRoles implements IMMOD.IUserRoles
{
	private Browser browser = null;
	public UserRoles(Browser browser){
		this.browser = browser;
	}

	@Override
	public void selectUserRole(String userrole) {
		Select sel =  new Select(browser.getElement(ElementTag.listExistingUserRoles));
		sel.selectByVisibleText(userrole);		
	}

	@Override
	public void addUserRole(String name, String description) {
		browser.getElement(ElementTag.txtUserCategoryName).clear();
		browser.getElement(ElementTag.txtUserCategoryDesc).clear();
		browser.getElement(ElementTag.txtUserCategoryName).sendKeys(name);
		browser.getElement(ElementTag.txtUserCategoryDesc).sendKeys(description);
		browser.getElement(ElementTag.bttnAddUserRole).click();
		}
		
	@Override
	public void cloneUserRole(String userrole, String newuserrole) {
		
		Select sel =  new Select(browser.getElement(ElementTag.listExistingUserRoles));
		sel.selectByVisibleText(userrole);		
		//change name
		browser.getElement(ElementTag.txtUserCategoryName).clear();
		browser.getElement(ElementTag.txtUserCategoryName).sendKeys(newuserrole);
		browser.getElement(ElementTag.txtUserCategoryDesc).clear();
		browser.getElement(ElementTag.txtUserCategoryDesc).sendKeys("Cloned Description");	
		browser.getElement(ElementTag.bttnCloneUserRole).click();
			
		}
		
	@Override
	public void updateUserRole(String userrole, String newdescription) {
		Select sel =  new Select(browser.getElement(ElementTag.listExistingUserRoles));
		sel.selectByVisibleText(userrole);		
		//change name
		browser.getElement(ElementTag.txtUserCategoryDesc).clear();
		browser.getElement(ElementTag.txtUserCategoryDesc).sendKeys(newdescription);		
		browser.getElement(ElementTag.bttnUpdateUserRole).click();	
		}
		
	@Override
	public void deleteUserRole(String userrole) {
		Select sel =  new Select(browser.getElement(ElementTag.listExistingUserRoles));
		sel.selectByVisibleText(userrole);		
		browser.getElement(ElementTag.bttnDeleteUserRole).click();
		}
		
	@Override
	public boolean checkUserRoleExists(String userrole) {
			Select select =  new Select(browser.getElement(ElementTag.listExistingUserRoles));
			boolean exists = false;
			List<PageElements> alloptions = select.getOptions();
			for (PageElements element : alloptions)
			{
				if(element.getText().equalsIgnoreCase(userrole)){
					exists =true;
					break;
				}				
			}	
			return exists;	
		}
	public String getDescription(String userrole)
	{
		if(userrole != null)
		{
		selectUserRole(userrole);
		String readDesc = browser.getElement(ElementTag.txtUserCategoryDesc).getValue();
		return readDesc;
		}else return "userrole was not null";
		
	}
	public String readMessage()
	{
		String msg = browser.getElement(ElementTag.winAlertMessage).getText();
		//browser.getElement(ElementTag.bttnMessageOk).click();		
		return msg;
	}
}
