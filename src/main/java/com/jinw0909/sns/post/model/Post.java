package com.jinw0909.sns.post.model;

import java.util.Date;

public class Post {
	private int id;
	private int userId;
	private String userName;
	private String content;
	private String imagePath;
	private Date createdAt;
	private Date updatdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatdAt() {
		return updatdAt;
	}
	public void setUpdatdAt(Date updatdAt) {
		this.updatdAt = updatdAt;
	}
	
	
}
