package com.bimalsahay.blog.model;

import java.io.Serializable;
import java.util.List;

import com.bimalsahay.model.AccountUser;

public class BlogResponse implements Serializable,Cloneable {
	
	private static final long serialVersionUID = -7605052698976876820L;
	
	private List<Blog> blogs;
	private AccountUser user;
	private String creationTime;
	
	public List<Blog> getBlogs() {
		return blogs;
	}
	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
	public AccountUser getUser() {
		return user;
	}
	public void setUser(AccountUser user) {
		this.user = user;
	}
	public String getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
 
}
