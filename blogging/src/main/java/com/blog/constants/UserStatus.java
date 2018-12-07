package com.blog.constants;

public enum UserStatus {
	
	ACTIVE(1),
	INACTIVE(0);
	
	int status;
	
	UserStatus(int status) {
		this.status=status;
	}

	/**
	 * @return the status
	 */
	public final int getStatus() {
		return status;
	}
	

	
}
