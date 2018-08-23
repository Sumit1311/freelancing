package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 19 FEB 2015
 *	Method to parse xml file for Page object repository and Methods to retrieve its tag values and attributes
 */

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class PageObjectRepository 
{
	private  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	private String Attribute = null;
	private String ElmentValue = null;
	private  Document doc =null;
	private static String objectRepositoryFile = System.getProperty("user.dir")
			+ "\\repository\\PageRepository.xml" ; 
	/**
	 * Constructor to parse POM xml file, if file is present.
	 */
	public PageObjectRepository()
	{
		try {
			
			File file = new File(objectRepositoryFile);
			if (file.exists()==false)
			{
				TestStatus.fail("Object Repository File doesn't exists!!!");
			}
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc =  dBuilder.parse(new File(file.getAbsolutePath()));
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}
	
	/**
	 * Method to retrieve xml element as per @ElementTag
	 * @param ElementTag element tag form @ElementTag
	 */
	private void getXMLElement(String ElementTag)
	{
		try{
			NodeList nList=doc.getElementsByTagName(ElementTag);
			if (nList.getLength()!=0)
			{
				for (int i =0;i<nList.getLength();i++)
				{
					Node nNode = nList.item(i);
					if (nNode.getNodeName().equals(ElementTag))
					{
						if (nNode.getNodeType() == Node.ELEMENT_NODE)
						{
							Element eElement = (Element) nNode;
							setElmentValue(eElement.getTextContent());
							setAttribute(eElement.getAttribute("by"));
						}
					}
				}
			}else
			{
				TestStatus.fail("Element " + ElementTag + " not found in page object repository file");
			}
		}catch(Exception e)
		{
			Log.error(e.getMessage());
		}
	}
	
	/**
	 * Method to return element value
	 * @param ElementTag Element tag
	 * @return returns locater for element
	 */
	public String getElmentValue(String ElementTag) {
		getXMLElement(ElementTag);
		return ElmentValue;
	}
	/**
	 * Method to set element locater
	 * @param elmentValue element locater value
	 */
	private void setElmentValue(String elmentValue) {
		ElmentValue = elmentValue;
	}
	/**
	 * Method to get element attribute (by)
	 * @return element by (attribute)
	 */
	public String getAttribute() {
		return Attribute;
	}
	/**
	 * Method to set element locate by
	 * @param attribute element locate by
	 */
	private void setAttribute(String attribute) {
		Attribute = attribute;
	}
}
