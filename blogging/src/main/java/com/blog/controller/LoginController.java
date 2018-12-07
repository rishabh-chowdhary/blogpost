package com.blog.controller;

import com.blog.dto.exceptions.InvalidCredentialException;
import com.blog.dto.request.LoginRequest;
import com.blog.model.User;
import com.blog.service.UserService;
import com.blog.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@Slf4j
/**
 * This controller class controls the user login/logout operations.
 * TODO - The controller class can coordinate with other service operation layers for:
 *
 */
public class LoginController {

	@Autowired
	private UserService userService;

    /**
     *
     * @return Redirects the user to login.jsp page.
     */
	@GetMapping("/login")
	public String login() {
		return "login";
	}

    /**
     *
     * @param httpRequest The associated httpservlet request.
     * @param request - The loginrequest for the user - Comprises of name, email & password.
     * @param bindingResult - Spring framework's binding result associated with this login request.
     * @return - If the username/password combination is invalid, the application redirects user back to the login page.
     * Clients can utilize checking for "error" object set in the httpRequest to display a meaningful msg to the user.
     * TODO - Currently, the password is send as plain-text,
     * TODO - future ask should be to utilize a MD5 hash + salt to hash the plain-text password.
     */
	@PostMapping("/login")
	public String login(HttpServletRequest httpRequest, @Valid LoginRequest request, BindingResult bindingResult) {
		try {
            if(request != null && request.isValid()) {
                User loggedInUser = userService.login(request);
                SessionUtils.addSession(httpRequest, loggedInUser);
                log.debug("User:",loggedInUser);
                return "redirect:index";
            }

		} catch (InvalidCredentialException e) {
			bindingResult.addError(new ObjectError("username", "Invalid credentials"));
			log.error("error occured", e);
		}
		httpRequest.setAttribute("error", "Invalid username or password");
		return "login";
	}

    /**
     *
     * @param httpRequest
     * @return This function removes the session & returns the user to the login page.
     */
	@GetMapping("/logout")
	public String logout(HttpServletRequest httpRequest) {
		SessionUtils.removeSession(httpRequest);
		return "redirect:login";
	}
}
