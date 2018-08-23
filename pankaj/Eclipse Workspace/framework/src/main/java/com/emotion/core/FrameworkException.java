package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 17 FEB 2015
 *	Automation framework class for runtime exception
 *	Can be use to throw exceptions while coding or extended to drive custom runtime exceptions
 *	The class can be used for unchecked exceptions only
 */

public class FrameworkException extends RuntimeException {
	
	private static final long serialVersionUID = 1234567878;

	public FrameworkException() {
		super();
	}
	
	public FrameworkException(String message)	{
		super(message);
	}
	
	public FrameworkException(Throwable cause) {
	    super(cause);
	  }

	public FrameworkException(String message, Throwable cause) {
	    super(message, cause);
	}
}
