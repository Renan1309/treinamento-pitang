package com.pitang.aula.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contentMenssage")
public class ContentMenssage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Size(max = 255)
	@Column(name = "id_msg")
	private String contentmsg ;
	
	@NotNull
	@Column(name = "id_user_msg")
	private Long id_user_msg ;
	
	@NotNull
	@Column(name = "id_user_contact")
	private Long id_user_contact ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContentmsg() {
		return contentmsg;
	}

	public void setContentmsg(String contentmsg) {
		this.contentmsg = contentmsg;
	}

	public Long getId_user_msg() {
		return id_user_msg;
	}

	public void setId_user_msg(Long id_user_msg) {
		this.id_user_msg = id_user_msg;
	}

	public Long getId_user_contact() {
		return id_user_contact;
	}

	public void setId_user_contact(Long id_user_contact) {
		this.id_user_contact = id_user_contact;
	}
	
	
	
}
