package com.pitang.aula.dto;

import com.pitang.aula.model.Contact;
import com.pitang.aula.model.ContentMenssage;

public class TalkDto  {
 
	private Contact contact ;
	private MensagemDto contentMenssage;
	
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public MensagemDto getContentMenssage() {
		return contentMenssage;
	}
	public void setContentMenssage(MensagemDto contentMenssage) {
		this.contentMenssage = contentMenssage;
	}
	
	
}
