package com.pitang.aula.dto;

public class StoryDto {
	
	private Long id;
	private String message ;
	private Long storyOwner;
	private byte[] imagebyte ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getImagebyte() {
		return imagebyte;
	}
	public void setImagebyte(byte[] imagebyte) {
		this.imagebyte = imagebyte;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Long getStoryOwner() {
		return storyOwner;
	}
	public void setStoryOwner(Long storyOwner) {
		this.storyOwner = storyOwner;
	}
	
	
	
}
