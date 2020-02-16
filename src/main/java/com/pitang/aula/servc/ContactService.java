package com.pitang.aula.servc;

import java.util.List;
import java.util.Optional;

import com.pitang.aula.model.Contact;
import com.pitang.aula.model.UserModel;


public interface ContactService {
	
	public List<Contact> listcontact() ;
  
	public Contact creatContact (Contact contact);
	
	public UserModel findUserByContact(Long id);
	
	public List<Contact> userContacts(Long id);
	
	public String deleteContact(Long id);
}
