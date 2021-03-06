package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 26 FEB 2015
 * 	class to handle multiple asserts in one test case. this enables to save assertion results and without breaking the code 
 * 	at any assertions.
 */

import java.util.Map;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import com.beust.jcommander.internal.Maps;

public class SoftAssert extends Assertion{
	
private static Map<AssertionError, IAssert> m_errors = Maps.newHashMap();
	
  @Override
  public void executeAssert(IAssert a) {
	    try {
	      a.doAssert();
	    } catch(AssertionError ex) {
	    	m_errors.put(ex, a);
	    }
	  }
/**
 * Method to print the list of errors.
 */
  public void showResults()
  {
	  if (! m_errors.isEmpty()) {
	      StringBuilder sb =
	            new StringBuilder("The following asserts failed:\n");
	      boolean first = true;
	      for (Map.Entry<AssertionError, IAssert> ae : m_errors.entrySet()) {
	        if (first) {
	          first = false;
	        } else {
	          sb.append("\n");
	        }
	        sb.append(ae.getValue().getMessage()  + " Expected was '" + ae.getValue().getExpected() + "' but was '" + ae.getValue().getActual() + "'");
	      }
	     TestStatus.fail(sb.toString());
	  }
   }
  /**
   * Method to clean hash for errors
   */
  public static void clearErrors()
  {
	  m_errors.clear();
  }
  /**
   * method to merge to hash set of errors
   * @param softassert
   */
  public void merge(SoftAssert softassert)
  {
	  if (!SoftAssert.m_errors.isEmpty())
	  {
		  m_errors.putAll(SoftAssert.m_errors);
	  }
  }
}
