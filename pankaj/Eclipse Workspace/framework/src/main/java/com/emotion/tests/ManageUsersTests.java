package com.emotion.tests;

import java.util.*;

import org.openqa.selenium.WebElement;
//import org.seleniumhq.jetty7.util.log.Log;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.emotion.core.ExpectedCondition;
import com.emotion.core.FrameworkException;
import com.emotion.core.SoftAssert;
import com.emotion.core.TestAssert;
import com.emotion.core.TestStatus;
import com.emotion.external.ElementTag;
import com.emotion.external.Fixtures;
import com.emotion.lib.IMMOD.IUsers;
import com.emotion.util.ExcelUtil;
import com.emotion.core.Log;


public class ManageUsersTests extends Fixtures
{
	private static IUsers users = null;
	private SoftAssert softassert = new SoftAssert();
	private TestAssert tassert = new TestAssert();

	@BeforeClass
	public void setUp(){
		setupFixture("Login as CreativeAuto for Manage Users Tests");
		
	}
	
/*	@Test(description = "TEST ID : 1 | verifySearchUser ", priority=1)
	public void verifySearchUser()
	{
		users = mmod.manageUsers();
		mmod.goToManageUsersPage();
		ArrayList<String> query = new ArrayList<String>();
		query.add("Manager");
		users.searchUser(query);
		
		//verify if exists
		boolean isPresent =users.isTextPresentOnPopUp("Manager");
		tassert.assertTrue(isPresent, "FAILED | TEST ID : 1 | Search user");
		TestStatus.pass("PASSED | TEST ID : 1 | Search user verified");
	}*/	
	
  	@DataProvider(name = "User_Details")  
   	public Object[][] addUser() throws Exception {  

        Object[][] retObjArr=ExcelUtil.getTableArray("testdata\\User Manager\\User_Details.xls",
                "New_User_Details", "addNewUserData");
        return(retObjArr);
    }
  	
	@Test(description = "TEST ID : 2 | verifyAddUser ", priority=2, dataProvider="User_Details")
	public void verifyAddUser(String Login,String Pwd,String ConfPwd,String First,String Middle,String Last,
			String Role,String Status,String Org,String Add1,String City,String State,String Zip ,String Country,
			String Email,String Phone){
		users = mmod.manageUsers();
		mmod.goToManageUsersPage();
		Log.info("CREATING A NEW USER WITH USER NAME: "+ Login);
			
		String addUserMsg = users.addUser( Login, Pwd, ConfPwd, First, Middle, Last, Role, Status, Org, Add1, City, State,
				Zip , Country, Email, Phone);
		if(addUserMsg.contains("Unable to create")){
			Log.info("FAILED | TEST ID : 2 |Add new User failed. Message displayed is: " + addUserMsg);
			throw new FrameworkException("FAILED | TEST ID : 2 |Add new User failed. Message displayed is: " + addUserMsg);
		}
		else
		{
			if((addUserMsg.contains("has been created successfully")))
			{
				Log.info(addUserMsg);
				Log.info("Now Searching the newly added user...");
				//search for this user
				mmod.goToManageUsersPage();
				users.searchUserByLoginName(Login);
				
				//verify if exists
				boolean isPresent = users.isTextPresentOnPopUp(Login);
				if (isPresent == true)
					Log.info("User: "+ Login +" is FOUND in the User list pop-up window.");
				
				tassert.assertTrue(isPresent, "FAILED | TEST ID : 2 | Add user for the following login name failed: "+ Login +
						". Message displayed is: "+addUserMsg);
				TestStatus.pass("PASSED | TEST ID : 2 | Add user verified. User is added: "+Login+ ". Message displayed is: "+ addUserMsg);
			}
			else{
				WebElement element =  (WebElement) browser.manageWait().explicitlyWait(ElementTag.labelWinAlert
						, ExpectedCondition.visibilityOfElementLocated, 5, null);
				if(element.isDisplayed()){
					String msg = element.getText();
					browser.getElement(ElementTag.bttnYuiAlert).click();
					Log.info("FAILED | TEST ID : 2 |Add new User failed. Message displayed is: " + msg);		
					throw new FrameworkException("FAILED | TEST ID : 2 |Add new User failed. Message displayed is: " + msg);
				}
			}
			
		}
		
		tassert.assertTrue(!addUserMsg.contains("Unable to create"), "FAILED | TEST ID : 2 | Add user for the following login name failed: "+ Login +
				". Message displayed is: "+addUserMsg);
		//login as new user
		//Click on some collection
		//check assets are visible

	}

	@AfterClass
	public void tearDown()
	{
		tearDownFixtureWithoutLogin();
	}
    
}
	


