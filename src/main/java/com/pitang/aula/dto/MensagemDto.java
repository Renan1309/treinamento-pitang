package com.pitang.aula.dto;

public class MensagemDto {
	
	private Long id;
	private String contentmsg ;
	private Long idusermsg ;
	private Long idusercontact ;
	private Boolean statusSend  ;
	private Boolean statusRecipient ;
	private String datareturn;
	
	public String getDatareturn() {
		return datareturn;
	}
	public void setDatareturn(String datareturn) {
		this.datareturn = datareturn;
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
	public Long getIdusermsg() {
		return idusermsg;
	}
	public void setIdusermsg(Long idusermsg) {
		this.idusermsg = idusermsg;
	}
	public Long getIdusercontact() {
		return idusercontact;
	}
	public void setIdusercontact(Long idusercontact) {
		this.idusercontact = idusercontact;
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
