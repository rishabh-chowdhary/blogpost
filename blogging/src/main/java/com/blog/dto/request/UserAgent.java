package com.blog.dto.request;

import com.blog.model.User;

import lombok.Data;

/**
 * This is a helper class to store the logged in user for the servlet request &
 * utilize the necessary info for any CRUD operations related to blog, comments.
 * The class also has future scale of IP, browser, device(make/model) for finessing the returned blogs/comments.
 */
@Data
public class UserAgent {
	
	private User loggedInUser;
	private String device;
	private String clientIp;
	private String browser;

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) { this.loggedInUser = loggedInUser; }

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}
}
