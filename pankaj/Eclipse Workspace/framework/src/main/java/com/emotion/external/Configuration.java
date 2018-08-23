package com.emotion.external;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 14 NOV 2014
 *	Class to read configuration file for framework 
 */

import java.io.FileInputStream;
import java.util.Properties;

import com.emotion.core.Log;

public class Configuration 
{
	private static String PropertyFilePath=System.getProperty("user.dir")
			+ "/config/config.properties";
	private static Properties props= new Properties();
	
	public static final String BROWSER = "Browser";
	public static final String URL = "URL";
	public static final String USERNAME = "Username";
	public static final String PASSWORD = "password";
	public static final String HOSTNAME = "Hostname";
	public static final String PORT = "Port";
	public static final String DB_MACHINE = "DB_machine";
	public static final String DB_PORT = "DB_Port";
	public static final String DB_NAME = "DB_name";
	public static final String DB_USERNAME = "DB_username";
	public static final String DB_PASSWORD = "DB_password";

	
	public static String getProperty(String Property)
	{
		try {
			props.load(new FileInputStream(PropertyFilePath));
			return props.getProperty(Property);
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
		return null; 
	}
}
