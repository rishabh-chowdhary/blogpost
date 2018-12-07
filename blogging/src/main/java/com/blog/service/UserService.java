package com.blog.service;

import com.blog.dto.exceptions.InvalidCredentialException;
import com.blog.dto.exceptions.UserAlreadyExistException;
import com.blog.dto.exceptions.UserNotExistException;
import com.blog.dto.request.LoginRequest;
import com.blog.dto.request.RegisterUserRequest;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import com.blog.utils.BlogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;
	private User loggedInUser;

	public boolean addUser(RegisterUserRequest registerUserRequest) throws UserAlreadyExistException {

		log.debug("attempting to register new user {}", registerUserRequest);
		User emailUser = find(registerUserRequest.getEmail());
		if (emailUser != null) {
			throw new UserAlreadyExistException("User already exists:"+registerUserRequest.getEmail());
		}
		User newUser = User.create(registerUserRequest);
		newUser.setPassword(BlogUtils.hash(registerUserRequest.getPassword()));
		userRepository.save(newUser);
        return true;
	}

	public User find(String email) {
		return userRepository.findByEmail(email.toLowerCase());
	}

	public User deleteByEmail(String email) throws UserNotExistException {
		User emailUser = find(email);
		if (emailUser == null) {
			throw new UserNotExistException("User with email doesn't exist");
		}
		userRepository.delete(emailUser.getId());
		return emailUser;
	}

	public User login(LoginRequest request) throws InvalidCredentialException {
		User emailUser = find(request.getUsername());
		if (emailUser == null) {
			throw new InvalidCredentialException("Invalid credentials");
		}
		if (!emailUser.getPassword().equals(BlogUtils.hash(request.getPassword()))) {
			log.debug("Invalid password for email {} ", request.getUsername());
			throw new InvalidCredentialException("Invalid credentials");
		}
		setLoggedInUser(emailUser);
		return emailUser;
	}


	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}
}
