
package com.pitang.aula.dto;

public class UserDto {
	
	private Long Id ;
	private String name ;
	private String surname ;
	private String foneId ;
	private String email ;
	private String password ;
	private String status;
	private String pathImage;
	private byte[] imagebyte ;
	
	
	
	
	public byte[] getImagebyte() {
		return imagebyte;
	}
	public void setImagebyte(byte[] imagebyte) {
		this.imagebyte = imagebyte;
	}
	public String getPathImage() {
		return pathImage;
	}
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFoneId() {
		return foneId;
	}
	public void setFoneId(String foneId) {
		this.foneId = foneId;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	 
}
