package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 12 NOV 2014
 *	Test assert class customized to do assertion and print custom failure message
 */

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

public class TestAssert extends Assertion{
	
 
	@Override
  public void executeAssert(IAssert a) {
	    try {
	      a.doAssert();
	    } catch(AssertionError ex) {
	      TestStatus.fail(a.getMessage() + " Expected was '" + a.getExpected() + "' but was '" + a.getActual() + "'");
	    }
	  }
}
