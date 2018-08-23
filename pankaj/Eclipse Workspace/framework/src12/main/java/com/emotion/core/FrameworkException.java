package com.emotion.core;

/**
 *	@author himanshu_upadhyay
 *	@version 1.0
 *	Last Updated: 12 NOV 2014
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
