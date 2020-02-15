package com.pitang.aula.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitang.aula.model.Contact;
import com.pitang.aula.repository.ContactModelRepository;
import com.pitang.aula.servc.ContactService;


@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactModelRepository contactRepository ;

	
	

	@Override
	public List<Contact> listcontact() {
		// TODO Auto-generated method stub
		return contactRepository.findAll();
	}
	
	
}
