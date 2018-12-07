package com.blog.dto.request;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class PostBlogRequest {
	
	private String title;
	private String description;
	private int tags[];

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

	public boolean isValid() {
		if (StringUtils.isEmpty(title) || StringUtils.isEmpty(description)) {
			return false;
		}
		return true;
	}
}
