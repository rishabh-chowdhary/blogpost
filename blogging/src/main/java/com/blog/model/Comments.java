package com.blog.model;

import com.blog.dto.request.PostCommentRequest;
import com.blog.dto.request.UserAgent;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Date;

/**
 * POJO object representing comment.
 * A Blog object can have multiple comments.
 * TODO -A future task could be to associate further engagement properties with this object - Views, likes, etc.
 * TODO - A comment should have restriction on the max length so we don't inflate our DB storage.
 */

@Document
@Data
public class Comments {

	public String getId() {
		return id;
	}

	@Id
	private String id;
	private String userName;
	private String data;
	private Date createdTime;
	private String blogId;

	public static Comments create(PostCommentRequest postCommentRequest, UserAgent userAgent) {
		if (postCommentRequest == null || userAgent == null) {
			return null;
		}
        if(userAgent.getLoggedInUser() == null) {
            return null;
        }
		Comments comment = new Comments();
		comment.setBlogId(postCommentRequest.getBlogId());
		comment.setData(postCommentRequest.getComments());
		comment.setCreatedTime(Calendar.getInstance().getTime());
		comment.setUserName(userAgent.getLoggedInUser().getName());
		return comment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
}
