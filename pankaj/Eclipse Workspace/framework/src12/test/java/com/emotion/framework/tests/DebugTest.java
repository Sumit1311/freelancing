package com.emotion.framework.tests;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 21 NOV 2014
 */

import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.emotion.external.Fixtures;

public class DebugTest extends Fixtures
{
	@BeforeClass
	public void setUp()
	{
		setupFixture("Test class");
	}
	
	@Test
	public void verify() throws IOException
	{
//		browser.getElement("Example Collection", By.linktext).click();
//		browser.getElement("uploadAssetButton", By.id).click();
//		browser.getElement("dropContainer", By.id).sendKeys("D:\\links.txt");
//		UplaodUtils.dragAndDrop();
		mmod.collection().selectCollection("QA_collection");
		String msg = mmod.collection().copyAssets(new String[] {"space-irons.jpg","ConX.jpg"}, null, "t", false, null, null);
		System.out.println(msg);
	}
	
	@AfterClass
	public void tearDown()
	{
		//tearDownFixture();
	}
}
