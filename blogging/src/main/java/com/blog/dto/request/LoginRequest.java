package com.blog.dto.request;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * This class is used by the Login controller for managing the login request of the user.
 */
@Data

public class LoginRequest {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValid() {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return false;
		}
		return true;
	}
}
