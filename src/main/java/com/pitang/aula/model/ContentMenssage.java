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
@Table(name = "content_menssage")
public class ContentMenssage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Size(max = 255)
	@Column(name = "content_msg")
	private String contentmsg ;
	
	@NotNull
	@Column(name = "id_user_msg")
	private Long idusermsg ;
	
	@NotNull
	@Column(name = "id_user_contact")
	private String idfonecontact ;
	
	@NotNull
	@Column(name = "status_msg")
	private Boolean statusmsg;
	
	// @Temporal(TemporalType.TIMESTAMP)
	// private Date cadastro; 
	
	

	public Boolean getStatusmsg() {
		return statusmsg;
	}

	public void setStatusmsg(Boolean statusmsg) {
		this.statusmsg = statusmsg;
	}

	public Long getIdusermsg() {
		return idusermsg;
	}

	public void setIdusermsg(Long idusermsg) {
		this.idusermsg = idusermsg;
	}

	public String getIdfonecontact() {
		return idfonecontact;
	}

	public void setIdfonecontact(String idfonecontact) {
		this.idfonecontact = idfonecontact;
	}

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

	
	
	
	
}
