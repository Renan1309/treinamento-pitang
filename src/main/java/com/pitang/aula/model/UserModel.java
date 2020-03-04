package com.pitang.aula.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UserModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@NotNull
	@Size(max = 70)
	@Column(name = "user_name")
	private String name ;
	
	@NotNull
	@Size(max = 70)
	@Column(name = "user_surname")
	private String surname ;
	
	@NotNull
	@Size(max = 15)
	@Column(name = "user_fone")
	private String foneId ;
	
	@NotNull
	@Email
	@Size(max = 50)
	@Column(unique = true ,name = "user_email" )
	private String email ;
	

	@NotNull
	@Size(max = 100)
	@Column(name = "user_password")
	private String password ;
	
	@NotNull
	@Column(name = "user_status")
	private boolean status ;
	 
	
    @Column(name = "user_image")
	private String pathImage ;
    
   
	
    @OneToMany(fetch = FetchType.LAZY ,
			   cascade = CascadeType.ALL,
			   mappedBy = "userModel")
	private List<Contact> contact;
	
	@OneToMany(fetch = FetchType.LAZY ,
			   cascade = CascadeType.ALL,
			   mappedBy = "storyOwner")
	private List<Story> story;
	
	
	@OneToMany
    private List<ContentMenssage> contentMenssages;
	
	
	
	

	public List<ContentMenssage> getContentMenssages() {
		return contentMenssages;
	}



	public void setContentMenssages(List<ContentMenssage> contentMenssages) {
		this.contentMenssages = contentMenssages;
	}



	public String getPathImage() {
		return pathImage;
	}



	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}



	public List<Story> getStory() {
		return story;
	}



	public void setStory(List<Story> story) {
		this.story = story;
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	public String getFoneId() {
		return foneId;
	}



	public void setFoneId(String foneId) {
		this.foneId = foneId;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}



	public List<Contact> getContact() {
		return contact;
	}



	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}



	
	

}