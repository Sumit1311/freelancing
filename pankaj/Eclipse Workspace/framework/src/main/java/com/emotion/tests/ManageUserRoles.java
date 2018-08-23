package com.emotion.tests;

/**
 *	@author Bhavna Karanjekar
 *	@version 1.0
 *	Last Updated: 10 FEB 2015
 */

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.emotion.core.SoftAssert;
import com.emotion.core.TestAssert;
import com.emotion.core.TestStatus;
import com.emotion.external.Fixtures;
import com.emotion.lib.PageTitles;
import com.emotion.lib.IMMOD.IUserRoles;

public class ManageUserRoles extends Fixtures
{
	//declaring class variables	
	private static IUserRoles userroles = null;
	private static String newrole = null;
	private static String newrole_space = null;
	private static String clonedrole = null;
	private static String newdescription = null;
	private SoftAssert softassert = new SoftAssert();
	private TestAssert tassert = new TestAssert();
	
	@BeforeClass
	public void setUp()
	{
		setupFixture("Login as CreativeAuto for User Role Manage Tests");
		//initializing class variables
		userroles = mmod.userRole();
		newrole = "Automation100";
		clonedrole = "AutomationCloned";
		newdescription = "Automation100 Desc";
		newrole_space = "Automation 500";
		mmod.goToManageUserRolePage();
	}
	
	@Test(description = "TEST ID : 1448 | Add User Role", priority=1)
	public void verifyAddUserRole()
	{
		userroles.addUserRole(newrole, newdescription);
		
		//verify if added in the list
		boolean actual = userroles.checkUserRoleExists(newrole.toLowerCase());
		tassert.assertEquals(actual, true, "FAILED | TEST ID : 1448 | User Role is not added");
		TestStatus.pass("PASSED | TEST ID : 1448 | User Role is added");
		
		//verify description is as expected
		String actualDesc=userroles.getDescription(newrole.toLowerCase());
		tassert.assertEquals(actualDesc, newdescription, "FAILED | TEST ID : 1448 | User role Description is not as entered");
		TestStatus.pass("PASSED | TEST ID : 1448 | User Role is same as it was entered");
	}
	
	@Test(description = "TEST ID : 1449 | Clone User Role", priority=2)
	public void verifyCloneUserRole()
	{
		userroles.cloneUserRole(newrole.toLowerCase(), clonedrole);
		
		//verify cloned role exists
		boolean actual = userroles.checkUserRoleExists(clonedrole.toLowerCase());
		tassert.assertEquals(actual, true, "FAILED | TEST ID : 1449 | Cloned user role does not exist");
		TestStatus.pass("PASSED | TEST ID : 1449 | Cloned user role exists");

		//verify original role exists
		actual = userroles.checkUserRoleExists(newrole.toLowerCase());
		tassert.assertEquals(actual, true, "FAILED | TEST ID : 1449 | Original user roles does not exist after cloning");
		TestStatus.pass("PASSED | TEST ID : 1449 | Original user role exists after cloning");
	}
	
	@Test(description = "TEST ID : 1450 | Update User Role", priority=3)
	public void verifyUpdateUserRole()
	{
		String newdescription = "New Description";
		userroles.updateUserRole(clonedrole.toLowerCase(), newdescription);
		userroles.selectUserRole(clonedrole.toLowerCase());

		tassert.assertEquals(userroles.getDescription(clonedrole.toLowerCase()), newdescription.toLowerCase(),
				"FAILED | TEST ID : 1450 | User role is not upadated");
		TestStatus.pass("PASSED | TEST ID : 1450 | User Role is updated");
	}

	@Test(description = "TEST ID : 1451 | Delete User Role", priority=4)
	public void verifyDeleteUserRole()
	{
		userroles.deleteUserRole(clonedrole.toLowerCase());
				
		tassert.assertFalse(userroles.checkUserRoleExists(clonedrole.toLowerCase()), "FAILED | TEST ID : 1451 | User role is not deleted");
		TestStatus.pass("PASSED | TEST ID : 1451 | User role is deleted");
		
	}
	
	@Test(description = "TEST ID : 1452 | Verify Creation Of Existing Role", priority=5)
	public void verifyCreationOfExistingRole()
	{
//		userroles.selectUserRole(newrole.toLowerCase());
//		this.browser.getElement(ElementTag.bttnAddUserRole).click();
		//userroles.addUserRole(newrole, newdescription);

		//verify message is displayed
		
		
		//this.browser.switchTo().alert();
/*		String msg = this.browser.switchTo().alert().getText();
		this.browser.switchTo().alert().accept();*/
		
		//browser.getElement(ElementTag.bttnMessageOk).click();
		//tassert.assertEquals(userroles.readMessage(), "This User Role already exists. Please, enter another name.");
	}
	
	@Test(description = "TEST ID : 1456 | Verify Creation Of Role Name With Spaces", priority=6)
	public void verifyCreationOfRoleNameWithSpaces()
	{
		userroles.addUserRole(newrole_space, newdescription);
		//verify if added in the list
		boolean actual = userroles.checkUserRoleExists(newrole_space.toLowerCase());
			
		tassert.assertFalse(actual, "FAILED | TEST ID : 1456 | Role name with spaces was created");
		TestStatus.pass("PASSED | TEST ID : 1456 | Role name with spaces was not created");
		
	}
	
	@Test(description = "TEST ID : 1457 | Verify Creating Role Name With Blank", priority=7)
	public void verifyCreatingRoleNameWithBlank()
	{
		userroles.addUserRole("", newdescription);
		//verify if added in the list
		boolean actual = userroles.checkUserRoleExists("".toLowerCase());
			
		tassert.assertFalse(actual, "FAILED | TEST ID : 1456 | Role name with blank is created");
		TestStatus.pass("PASSED | TEST ID : 1456 | Role name with clanks is not created");
		
	}
	
	@Test(description = "TEST ID : 1458 | Verify Creation Of Role Description With Spaces", priority=8)
	public void verifyCreationOfRoleDescriptionWithSpaces()
	{
		userroles.addUserRole(newrole, newdescription);
		//verify if added in the list
		boolean actual = userroles.checkUserRoleExists(newrole.toLowerCase());
		tassert.assertEquals(actual, true, "FAILED | TEST ID : 1458 | Role does not exist");
		TestStatus.pass("PASSED | TEST ID : 1458 | Role exists");
		
		//verify description is as expected
		String actualDesc=userroles.getDescription(newrole.toLowerCase());
		
		tassert.assertEquals(actualDesc, newdescription, "FAILED | TEST ID : 1458 | Role Description with spaces was not created");
		TestStatus.pass("PASSED | TEST ID : 1458 | Role description with spaces was created");
		
		userroles.deleteUserRole(newrole.toLowerCase());
	}
	
	@Test(description = "TEST ID : 1459 | Verify Creating Role Description With Blank", priority=9)
	public void verifyCreatingRoleDescriptionWithBlank()
	{
		newdescription = "";
		userroles.addUserRole(newrole, newdescription);
		
		//verify if added in the list
		boolean actual = userroles.checkUserRoleExists(newrole.toLowerCase());
		
		tassert.assertFalse(actual, "FAILED | TEST ID : 1459 | Role Description With Blank was created");
		TestStatus.pass("PASSED | TEST ID : 1459 | Role Description With Blank was not created");
		
	}
	
	@AfterClass
	public void tearDown()
	{
		tearDownFixture();
	}

}
