package com.emotion.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.emotion.external.Fixtures;

public class Annotations extends Fixtures
{
	private static final String mpadmin = null;
 @BeforeClass
	public void setUp(){
		setupFixture("Manage Users");
    }
@Test(description = "TEST ID : 1 | annotations ", priority=1)

public class annotations {
	
	   }
@AfterClass
public void tearDown()
{
	tearDownFixtureWithoutLogin();
}
}
