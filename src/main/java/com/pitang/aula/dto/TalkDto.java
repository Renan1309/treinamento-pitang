package com.pitang.aula.dto;

import com.pitang.aula.model.Contact;
import com.pitang.aula.model.ContentMenssage;

public class TalkDto implements Comparable<TalkDto>  {
 
	private Contact contact ;
	private ContentMenssage contentMenssage;
	
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	public ContentMenssage getContentMenssage() {
		return contentMenssage;
	}
	public void setContentMenssage(ContentMenssage contentMenssage) {
		this.contentMenssage = contentMenssage;
	}
	
	
	 
	@Override
	public int compareTo(TalkDto arg0) {
		return this.getContentMenssage().getDatamsg().compareTo(getContentMenssage().getDatamsg());
	}  
	
	
	
	
}
