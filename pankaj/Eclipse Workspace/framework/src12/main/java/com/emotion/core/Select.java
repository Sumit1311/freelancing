package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 21 NOV 2014
 */

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Select {
	private PageElements element = null;
	private final boolean isMulti;
	
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
	
	public List<PageElements> getOptions(){
		return element.getElements("option", By.tagname);
	}
	
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
	
	public PageElements getFirstSelectedOption()
	{
	    for (PageElements option : getOptions()) {
	        if (option.isSelected()) {
	          return option;
	        }
	    }
	    throw new FrameworkException("No options are selected");
	}
	
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
	            if (text.equals(option.getText())) {
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
	
	public boolean isMultiple()
	{
		return isMulti;
	}
	
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
	
	public void deselectByIndex(int index)
	{
	    String match = String.valueOf(index);

	    for (PageElements option : getOptions()) {
	      if (match.equals(option.getAttribute("index")) && option.isSelected()) {
	        option.click();
	      }
	    }
	}
	
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
