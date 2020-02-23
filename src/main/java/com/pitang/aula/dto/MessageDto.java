package com.pitang.aula.dto;

public class MessageDto {

	private Long id;
	private Long idusermsg ;
	private Long idusercontact ;
	private Boolean statusSend  ;
	private Boolean statusRecipient ;
	
	public Long getIdusercontact() {
		return idusercontact;
	}
	public void setIdusercontact(Long idusercontact) {
		this.idusercontact = idusercontact;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdusermsg() {
		return idusermsg;
	}
	public void setIdusermsg(Long idusermsg) {
		this.idusermsg = idusermsg;
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
	
	
	
}
