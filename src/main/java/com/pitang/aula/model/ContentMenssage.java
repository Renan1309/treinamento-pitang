package com.pitang.aula.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

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
	@Column(name = "status_msg" )
	private Boolean statusmsg  = true;
	
	@NotNull
	@Column(name = "status_send" )
	private Boolean statusSend  = true ;
	
	@NotNull
	@Column(name = "status_recipient" )
	private Boolean statusRecipient = true;
	
	
	
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datamsg"  , columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date datamsg; 
	
	@OneToOne(fetch = FetchType.EAGER , optional = true)
    @JoinColumn(name = "id_user_msg" , nullable = false)
    private UserModel idusermsg;
	
	@OneToOne(fetch = FetchType.EAGER , optional = true)
    @JoinColumn(name = "id_user_contact" , nullable = false)
    private UserModel idusercontact;
	
	

	public UserModel getIdusermsg() {
		return idusermsg;
	}

	public void setIdusermsg(UserModel idusermsg) {
		this.idusermsg = idusermsg;
	}

	public UserModel getIdusercontact() {
		return idusercontact;
	}

	public void setIdusercontact(UserModel idusercontact) {
		this.idusercontact = idusercontact;
	}

	public Boolean getStatusmsg() {
		return statusmsg;
	}

	public Boolean getStatusSend() {
		return statusSend;
	}

	public void setStatusSend(Boolean statusSend) {
		this.statusSend = statusSend;
	}

	public Boolean getStatusRecipient() {
		return statusRecipient;
	}

	public void setStatusRecipient(Boolean statusRecipient) {
		this.statusRecipient = statusRecipient;
	}

	public void setStatusmsg(Boolean statusmsg) {
		this.statusmsg = statusmsg;
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

	public Date getDatamsg() {
		return datamsg;
	}

	public void setDatamsg(Date datamsg) {
		this.datamsg = datamsg;
	}

	

	
	

	

	
	
	
	
}
