package com.bimalsahay.blog.model;

public enum BlogStatus {
	DRAFT("DRAFT"),
	TRASH("TRASH"),
	PUBLISHED("PUBLISHED");
	
	String status = null;
	
	BlogStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
