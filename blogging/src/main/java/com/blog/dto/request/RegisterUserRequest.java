package com.blog.dto.request;

import org.springframework.util.StringUtils;

/**
 * This class is used by the User controller to register a new user.
 */
public class RegisterUserRequest {

	private String email;
	private String password;
	private String name;

	//TODO implementation
	private String confirmPassword;

	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public final void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RegisterUserRequest [email=" + email + ", password=" + password + ", name=" + name + "]";
	}
	
	
	public boolean isValid() {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password) || StringUtils.isEmpty(email)) {
			return false;
		}
		return true;
	}
	
	
}
