package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 Feb 2015
 *	Interface to define browser types supported by automation framework
 */

public interface BrowserTypes {
	/**
	 * Use in case tests need to run on Firefox
	 */
	public static String FireFox = "firefox";
	/**
	 * Use in case tests needs to be run on Internet explorer
	 */
	public static String InternetExplorer = "internetexplorer";
	/**
	 * Use in case tests needs to be run on Chrome
	 */
	public static String Chrome = "chrome";
}
