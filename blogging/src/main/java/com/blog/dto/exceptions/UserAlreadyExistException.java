package com.blog.dto.exceptions;

/**
 * This exception is thrown when an attempt to register the user with same login is made.
 */
public class UserAlreadyExistException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 8670178437268082816L;
	
	public UserAlreadyExistException(String string) {
		super(string);
	}

}
