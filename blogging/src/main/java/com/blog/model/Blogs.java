package com.blog.model;

import com.blog.dto.request.PostBlogRequest;
import com.blog.dto.request.UserAgent;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * POJO object representing the properties associated with a Blog.
 */
@Component
//@Document
@Data
public class Blogs {

	@Id
	private String id;
	private String title;
	private String description;
	private int[] tags;
	private String userId;
	private Date createdTime;
	private Date updatedTime;
    private String authorName;

    public Blogs() {

    }
    /**
     * Static helper function to create a blog object.
     * @param request
     * @param userAgent
     */
	public static Blogs create(PostBlogRequest request, UserAgent userAgent) {

        if(request == null || userAgent == null) {
            return null;
        }
        if(userAgent.getLoggedInUser() == null) {
            return null;
        }
		Blogs blog = new Blogs();
		blog.setCreatedTime(Calendar.getInstance().getTime());
		blog.setUpdatedTime(blog.createdTime);
		blog.setTags(request.getTags());
		blog.setTitle(request.getTitle());
		blog.setDescription(request.getDescription());
        blog.setUserId(userAgent.getLoggedInUser().getId());
        blog.setAuthorName(userAgent.getLoggedInUser().getName());
        return blog;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int[] getTags() {
		return tags;
	}

	public void setTags(int[] tags) {
		this.tags = tags;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
