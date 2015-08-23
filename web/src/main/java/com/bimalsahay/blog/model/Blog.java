package com.bimalsahay.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.mongodb.BasicDBList;

public class Blog implements Serializable,Cloneable {
	
	private static final long serialVersionUID = -7605052698976876820L;
	@Id
    private String id;
	private String blogTitle;
	private String blogWebsite;
	private String blogYouTube;
	private String blogText;
	private String blogMoreText;
	private String blogName;
	private String blogDescription;
	private UUID blogUUID;
	private String userId;
	private String userFullName;
	private String userPhotoLink;
	private String imageId;
	private BlogStatus blogStatus;
	private Date creationTimeStamp;
	private BasicDBList comment;
	private BasicDBList likes;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getBlogDescription() {
		return blogDescription;
	}
	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public UUID getBlogUUID() {
		return blogUUID;
	}
	public void setBlogUUID(UUID blogUUID) {
		this.blogUUID = blogUUID;
	}
	public BlogStatus getBlogStatus() {
		return blogStatus;
	}
	public void setBlogStatus(BlogStatus blogStatus) {
		this.blogStatus = blogStatus;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogWebsite() {
		return blogWebsite;
	}
	public void setBlogWebsite(String blogWebsite) {
		this.blogWebsite = blogWebsite;
	}
	public String getBlogYouTube() {
		return blogYouTube;
	}
	public void setBlogYouTube(String blogYouTube) {
		this.blogYouTube = blogYouTube;
	}
	public String getBlogText() {
		return blogText;
	}
	public void setBlogText(String blogText) {
		this.blogText = blogText;
	}
	public String getBlogMoreText() {
		return blogMoreText;
	}
	public void setBlogMoreText(String blogMoreText) {
		this.blogMoreText = blogMoreText;
	}
	public Date getCreationTimeStamp() {
		return creationTimeStamp;
	}
	public void setCreationTimeStamp(Date creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getUserPhotoLink() {
		return userPhotoLink;
	}
	public void setUserPhotoLink(String userPhotoLink) {
		this.userPhotoLink = userPhotoLink;
	}
	public BasicDBList getComment() {
		return comment;
	}
	public void setComment(BasicDBList comment) {
		this.comment = comment;
	}
	public BasicDBList getLikes() {
		return likes;
	}
	public void setLikes(BasicDBList likes) {
		this.likes = likes;
	}

}
