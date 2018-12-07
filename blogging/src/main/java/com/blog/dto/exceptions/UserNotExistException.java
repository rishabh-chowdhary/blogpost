package com.blog.dto.exceptions;

/**
 * This exception is thrown by the User controller when a delete operation is made for a user which doesn't exist.
 */
public class UserNotExistException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 8670178437268082816L;
	
	public UserNotExistException(String string) {
		super(string);
	}

}
