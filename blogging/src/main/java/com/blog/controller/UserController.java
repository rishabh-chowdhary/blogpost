package com.blog.controller;

import com.blog.dto.exceptions.UserAlreadyExistException;
import com.blog.dto.request.RegisterUserRequest;
import com.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}

    /**
     *
     * @param httpRequest The http servlet request
     * @param request The RegisterUserRequest obejct, containing the user registration info.
     * @param response
     * @return String for the client to redirect to the correct page. Throws a bad request if invalid params are provided,
     * or a registration attempt is made with an already existing email ID
     */
    @PostMapping("/users/save")
    public String addUser(HttpServletRequest httpRequest, @Valid RegisterUserRequest request, HttpServletResponse response) {
        log.debug("saving new user {}", request);
        String url = "login";
        try {
            userService.addUser(request);

        } catch (UserAlreadyExistException e) {
            log.error("error occured", e);
            httpRequest.setAttribute("error", "User Already exists");
            url = "registration";
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch(NullPointerException e) {
            log.error("error occured", e);
            httpRequest.setAttribute("error", "Invalid arguments supplied:" + request.toString());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            url = "registration";
        }
        return url;

    }
	//TODO
//	@GetMapping("/users/find")
//	public User findUser(@RequestParam String email) {
//		log.debug("finding user with email {}", email);
//		return userService.find(email);
//
//	}
//
//	@DeleteMapping("/users/delete")
//	public User delete(@RequestParam String email) throws  UserNotExistException {
//		log.debug("finding user with email {}", email);
//		return userService.deleteByEmail(email);
//
//	}
}
