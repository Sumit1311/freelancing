package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 26 FEB 2015
 *	Class to handle select element methods
 */

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Select {
	private PageElements element = null;
	private final boolean isMulti;
	/**
	 * parameterized constructor to pass element of select type/tag
	 * @param element element 
	 */
	public Select(PageElements element) {
		String tagName = element.getTagName();
		
		if (null == tagName || !"select".equals(tagName.toLowerCase())) {
		      throw new FrameworkException("Unexpected tag name" + tagName);
		    }
		    this.element = element;
		    String value = element.getAttribute("multiple");
		    // The atoms normalize the returned value, but check for "false"
		    isMulti = (value != null && !"false".equals(value));
	}
	/**
	 * Method to element with tag name options 
	 * @return list of elements
	 */
	public List<PageElements> getOptions(){
		return element.getElements("option", By.tagname);
	}
	
	/**
	 * Method to get all selected options form the list 
	 * @return returns list of element selected
	 */
	public List<PageElements> getAllSelectedOption()
	{
	    List<PageElements> toReturn = new ArrayList<PageElements>();

	    for (PageElements option : getOptions()) {
	      if (option.isSelected()) {
	        toReturn.add(option);
	      }
	    }
	    return toReturn;
	}
	/**
	 * Method to get first selected option form the list
	 * @return element selected as first in list
	 */
	public PageElements getFirstSelectedOption()
	{
	    for (PageElements option : getOptions()) {
	        if (option.isSelected()) {
	          return option;
	        }
	    }
	    throw new FrameworkException("No options are selected");
	}
	/**
	 * Method to select element by its index
	 * @param index index number
	 */
	public void selectByIndex(int index)
	{
	    String match = String.valueOf(index);

	    boolean matched = false;
	    for (PageElements option : getOptions()) {
	      if (match.equals(option.getAttribute("index"))) {
	        setSelected(option);
	        if (!isMultiple()) {
	          return;
	        }
	        matched = true;
	      }
	    }
	    if (!matched) {
	      throw new FrameworkException("Cannot locate option with index: " + index);
	    }
	}
	/**
	 * Method to select element by its value.
	 * @param value Element value
	 */
	public void selectByValue(String value)
	{
	    StringBuilder builder = new StringBuilder(".//option[@value =' ");
	    builder.append(value);
	    builder.append("']");
	    List<PageElements> options = element.getElements(builder.toString(),By.xpath);

	    boolean matched = false;
	    for (PageElements option : options) {
	      setSelected(option);
	      if (!isMultiple()) {
	        return;
	      }
	      matched = true;
	    }

	    if (!matched) {
	      throw new FrameworkException("Cannot locate option with value: " + value);
	    }
	}
	
	/**
	 * Method to select element by its visible text
	 * @param text visible text of element
	 */
	public void selectByVisibleText(String text)
	{
	    List<PageElements> options =
	            element.getElements(".//option[normalize-space(.) = '" + text + "']",By.xpath);

	        boolean matched = false;
	        for (PageElements option : options) {
	          setSelected(option);
	          if (!isMultiple()) {
	            return;
	          }
	          matched = true;
	        }

	        if (options.isEmpty() && text.contains(" ")) {
	          String subStringWithoutSpace = getLongestSubstringWithoutSpace(text);
	          List<PageElements> candidates;
	          if ("".equals(subStringWithoutSpace)) {
	            // hmm, text is either empty or contains only spaces - get all options ...
	            candidates = element.getElements("option",By.tagname);
	          } else {
	            // get candidates via XPATH ...
	            candidates =
	                element.getElements(".//option[contains(., '" +
	                    subStringWithoutSpace + "')]",By.xpath);
	          }
	          for (PageElements option : candidates) {
	        	  //if (text.equals(option.getText()) ---earlier condition
	            if (option.getText().trim().startsWith(text) || option.getText().trim().contains(text) ) {
	              setSelected(option);
	              if (!isMultiple()) {
	                return;
	              }
	              matched = true;
	            }
	          }
	        }

	        if (!matched) {
	          throw new FrameworkException("Cannot locate element with text: " + text);
	        }
	}
	public void selectUser(String text)
	{
	    List<PageElements> options =
	            element.getElements(".//option[normalize-space(.) = '" + text + "']",By.xpath);

	        boolean matched = false;
	        for (PageElements option : options) {
	          setSelected(option);
	          if (!isMultiple()) {
	            return;
	          }
	          matched = true;
	        }

	        //if (options.isEmpty() && text.contains(" ")) {
	          String subStringWithoutSpace = getLongestSubstringWithoutSpace(text);
	          List<PageElements> candidates;
	          if ("".equals(subStringWithoutSpace)) {
	            // hmm, text is either empty or contains only spaces - get all options ...
	            candidates = element.getElements("option",By.tagname);
	          } else {
	            // get candidates via XPATH ...
	            candidates =
	                element.getElements(".//option[contains(., '" +
	                    subStringWithoutSpace + "')]",By.xpath);
	          }
	          for (PageElements option : candidates) {
	        	  //if (text.equals(option.getText()) ---earlier condition
	            if (option.getText().trim().startsWith(text) || option.getText().trim().contains(text) ) {
	              setSelected(option);
	              if (!isMultiple()) {
	                return;
	              }
	              matched = true;
	            }
	          }
	       // }

	        if (!matched) {
	          throw new FrameworkException("Cannot locate element with text: " + text);
	        }
	}
	
	/**
	 * Method to check is list of multi-select or not 
	 * @return
	 */
	public boolean isMultiple()
	{
		return isMulti;
	}
	/**
	 * Method to deselect all elements in list
	 */
	public void deselectAll()
	{
	   if (!isMultiple()) {
		      throw new FrameworkException(
		          "You may only deselect all options of a multi-select");
		    }
	
		    for (PageElements option : getOptions()) {
		      if (option.isSelected()) {
		        option.click();
		      }
		    }
	}
	/**
	 * Method to deselect element by index
	 * @param index element index number 
	 */
	public void deselectByIndex(int index)
	{
	    String match = String.valueOf(index);

	    for (PageElements option : getOptions()) {
	      if (match.equals(option.getAttribute("index")) && option.isSelected()) {
	        option.click();
	      }
	    }
	}
	
	/** 
	 * Method to deselect element by its value
	 * @param value
	 */
	public void deselectByValue(String value)
	{
		StringBuilder builder = new StringBuilder(".//option[@value = '");
	    builder.append(value);
	    builder.append("']");
	    List<PageElements> options = element.getElements(builder.toString(), By.xpath);
	    for (PageElements option : options) {
	      if (option.isSelected()) {
	        option.click();
	      }
	    }
	}
	
	/**
	 * Method to deselect element by its visible text
	 * @param text
	 */
	public void deselectByVisibleText(String text)
	{
	    StringBuilder builder = new StringBuilder(".//option[normalize-space(.) = '");
	    builder.append(text);
	    builder.append("']");
	    List<PageElements> options = element.getElements(builder.toString(),By.xpath);
	    for (PageElements option : options) {
	      if (option.isSelected()) {
	        option.click();
	      }
	    }
	}
	
	/**
	 * Method to select option
	 * @param option
	 */
	  private void setSelected(PageElements option) {
		    if (!option.isSelected()) {
		      option.click();
		    }
	  }
	  
	  private String getLongestSubstringWithoutSpace(String s) {
		    String result = "";
		    StringTokenizer st = new StringTokenizer(s, " ");
		    while (st.hasMoreTokens()) {
		      String t = st.nextToken();
		      if (t.length() > result.length()) {
		        result = t;
		      }
		    }
		    return result;
		  }
}
