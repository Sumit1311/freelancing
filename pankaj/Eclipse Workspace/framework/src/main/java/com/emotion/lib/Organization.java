package com.emotion.lib;

import com.emotion.core.Browser;
import com.emotion.core.Select;
import com.emotion.external.ElementTag;

public class Organization implements IMMOD.IOrganization{

	private Browser browser = null;
	public Organization(Browser browser) {
		this.browser = browser;
	}

	@Override
	public void addNewOrganization(String name,String pointofContactFirst,String pointofContactMiddle, String pointofContactLast,
			String email,String phone,String fax,String billingAddress,String city,String state,String zip,
			String country,String shippingAddress,String city1,String state1,String zip1,String country1) {
		
		if(name!=null || name!=""){
			browser.getElement(ElementTag.txtName).sendKeys(name);
		}
		if(pointofContactFirst!=null || pointofContactFirst!=""){
		browser.getElement(ElementTag.txtPointofContactFirst).sendKeys(pointofContactFirst);
		}
		if(pointofContactMiddle!=null || pointofContactMiddle!=""){
			browser.getElement(ElementTag.txtPointofContactMiddle).sendKeys(pointofContactMiddle);
			}
		if(pointofContactLast!=null || pointofContactLast!=""){
			browser.getElement(ElementTag.txtPointofContactLast).sendKeys(pointofContactLast);
			}
		if(email!=null || email!=""){
			browser.getElement(ElementTag.txtContactEmail).sendKeys(email);
			}
		if(phone!=null || phone!=""){
			browser.getElement(ElementTag.txtContactPhone).sendKeys(phone);
			}
		if(fax!=null || fax!=""){
			browser.getElement(ElementTag.txtContactFax).sendKeys(fax);
			}
		if(billingAddress!=null || billingAddress!=""){
			browser.getElement(ElementTag.txtBillingAddress).sendKeys(billingAddress);
			}
		if(city!=null || city!=""){
			browser.getElement(ElementTag.txtCity).sendKeys(city);
		}
		if(state!=null || state!=""){
			browser.getElement(ElementTag.txtState).sendKeys(state);
			}
		if(zip!=null || zip!=""){
			browser.getElement(ElementTag.txtZip).sendKeys(zip);
			}
		if(country!=null || country!=""){
			Select select2 =  new Select(browser.getElement(ElementTag.ddlSelectCountry));
			select2.selectByVisibleText(country);
			}
		if(shippingAddress!=null || shippingAddress!=""){
			browser.getElement(ElementTag.txtShippingAddress).sendKeys(shippingAddress);
			}
		if(city1!=null || city1!=""){
			browser.getElement(ElementTag.txtCity1).sendKeys(city1);
			}
		if(state1!=null || state1!=""){
			browser.getElement(ElementTag.txtState1).sendKeys(state1);
			}
		if(zip1!=null || zip1!=""){
			browser.getElement(ElementTag.txtZip1).sendKeys(zip1);
			}
		if(country1!=null || country1!=""){
			Select select2 =  new Select(browser.getElement(ElementTag.ddlSelectCountry1));
			select2.selectByVisibleText(country1);
			}
		browser.getElement(ElementTag.bttnAdd).click();
		
	}

	@Override
	public void editOrganization(String name,String pointofContactFirst,String pointofContactMiddle, String pointofContactLast,
			String contactEmail,String contactPhone,String contactFax,String billingAddress,String city,String state,String zip,
			String country,String shippingAddress,String city1,String state1,String zip1,String country1) {		
		
	}

}
