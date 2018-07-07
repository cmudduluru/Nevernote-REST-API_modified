package com.NevernoteRESTAPI.Model;

import java.time.LocalDateTime;

// Spring MVC Model layer for Note class
public class Note {
	
	//fields
	private String title;
	private String body;
	private String[] tags;
	private LocalDateTime createdTime;
	private LocalDateTime lastModifiedtime;
	
	public Note(String title, String body, String[] tags, LocalDateTime createdTime, LocalDateTime lastModifiedtime) {
		super();
		this.title = title;
		this.body = body;
		this.tags = tags;
		this.createdTime = createdTime;
		this.lastModifiedtime = lastModifiedtime;
	}
	
	public Note() {
		
	}
	
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String[] getTags() {
		return tags;
	}
	
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}
	
	public LocalDateTime getLastModifiedtime() {
		return lastModifiedtime;
	}
	
	public void setLastModifiedtime(LocalDateTime lastModifiedtime) {
		this.lastModifiedtime = lastModifiedtime;
		
	}
	
	

}
