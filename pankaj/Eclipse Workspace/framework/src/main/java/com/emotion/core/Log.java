package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 18 FEB 2015
 *	Framework logging class with different logging level. It is designed using Log4j functions
 */

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log
{

	private static Logger log = Logger.getLogger(Log.class);
	private static String configFilename = System.getProperty("user.dir")
			+ "\\config\\Logging.properties";
	/**
	 * Constructor to read log4j property file
	 */
	public Log() {
		PropertyConfigurator.configure(configFilename);
	}
	/**
	 * Log level Information
	 * @param message Message to be logged.
	 */
	public static void info(String message)
	{
		log.info(message);
	}
	/**
	 * Log level Warning 
	 * @param message Message to be logged.
	 */
	public static void warn(String message)
	{
		log.warn(message);
	}
	/**
	 * Log level Error
	 * @param message Message to be logged.
	 */
	public static void error(String message)
	{
		log.error(message);
	}
	/**
	 * Log level Debug only
	 * @param message Message to be logged.
	 */
	public static void debug(String message)
	{
		log.debug(message);
	}
	/**
	 * Log level Fatal error
	 * @param message Message to be logged.
	 */
	public static void fatal(String message)
	{
		log.fatal(message);
	}

}
