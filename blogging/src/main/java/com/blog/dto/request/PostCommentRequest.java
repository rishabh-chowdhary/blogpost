package com.blog.dto.request;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * This class is used by the CommentsController during an addition/creation of a new comment for the given Blog.
 *
 */
@Data
public class PostCommentRequest {
	
	private String blogId;
	private String comments;

	/**
	 * @return the blogId
	 */
	public final String getBlogId() {
		return blogId;
	}
	/**
	 * @param blogId the blogId to set
	 */
	public final void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	/**
	 * @return the comments
	 */
	public final String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public final void setComments(String comments) {
		this.comments = comments;
	}
	
	public boolean isValid() {
		if (StringUtils.isEmpty(blogId) || StringUtils.isEmpty(comments)) {
            return false;
        }
        return true;
	}

}
