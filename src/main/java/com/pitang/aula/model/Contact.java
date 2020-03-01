package com.pitang.aula.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "contact")
public class Contact {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@NotNull
	@Size(max = 70)
	@Column(name = "contact_name")
	private String name ;
	
	@NotNull
	//@Size(max = 15)
	@Column(name = "id_contact_user")
	private Long idUserContact ;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "user_id")
	private UserModel userModel ;


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
	

	public Long getIdUserContact() {
		return idUserContact;
	}


	public void setIdUserContact(Long idUserContact) {
		this.idUserContact = idUserContact;
	}


	public UserModel getUserModel() {
		return userModel;
	}


	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}


	


	
	

}