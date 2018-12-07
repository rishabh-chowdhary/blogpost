package com.blog.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used by the CommentsController class to return a success/error creation of a comment.
 * Future use could be for other CRUD operations.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

	private String status;
	private String errorMsg;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
