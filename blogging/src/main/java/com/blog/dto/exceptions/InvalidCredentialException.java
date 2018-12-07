package com.blog.dto.exceptions;

/**
 * This exception is thrown by the User service & is to be handled by Login controller when an invalid login attempt is made.
 */
public class InvalidCredentialException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 8670178437268082816L;
	
	public InvalidCredentialException(String string) {
		super(string);
	}

}
