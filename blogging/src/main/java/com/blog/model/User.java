package com.blog.model;

import com.blog.constants.UserStatus;
import com.blog.dto.request.RegisterUserRequest;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 *
 */
@Data
@Document

/**
 * POJO representing the user object.
 */
public class User {

	@Id
	private String id;
	
	private String email;
	private String name;
	private String password;
	private int status;
	private Date createdTime;
	private Date updatedTime;

    /**
     * Static helper method to create a User POJO .
     * @param registerUserRequest
     * @return the created User object.
     */
	public static User create(RegisterUserRequest registerUserRequest) {
        if (registerUserRequest == null) {
            return null;
        }
        if(registerUserRequest.getEmail() == null) {
            return null;
        }
		User user = new User();
		user.setCreatedTime(new Date());
		user.setUpdatedTime(user.getCreatedTime());
		user.setName(registerUserRequest.getName());
		user.setStatus(UserStatus.ACTIVE.getStatus());
        user.setPassword(registerUserRequest.getPassword());
        user.setEmail(registerUserRequest.getEmail().toLowerCase());
		return user;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}
