package com.blog.utils;

import com.blog.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is used for adding/removing session attributes.
 */
public class SessionUtils {

	public static void addSession(HttpServletRequest request, User user) {
		request.getSession().setAttribute("sessionUser", user);
	}
	
	public static User getUser(HttpServletRequest request) {
		return (User)request.getSession().getAttribute("sessionUser");
	}

	public static void removeSession(HttpServletRequest request) {
		request.getSession().setAttribute("sessionUser",null);
	}
}
