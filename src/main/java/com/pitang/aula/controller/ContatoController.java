package com.pitang.aula.controller;

import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.aula.dto.ContactDto;
import com.pitang.aula.dto.UserDto;
import com.pitang.aula.mapper.ModelMapperComponent;
import com.pitang.aula.model.Contact;
import com.pitang.aula.servc.ContactService;

@RestController
public class ContatoController {
	
	private ContactService contactService ;
	
	public ContatoController (ContactService contactService) {
		super();
		this.contactService = contactService ;
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	@ResponseBody
      public ResponseEntity <List<ContactDto>>  listUsuarios() {
           List<Contact> contacts = contactService.listcontact() ;
		
		if(contacts.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ContactDto> contactDto = ModelMapperComponent.modelMapper.map(contacts, new TypeToken<List<ContactDto>>() {}.getType());

		
		return new ResponseEntity<>(contactDto,HttpStatus.OK);
		
		
	}
	
	
	
}

/*
 @RequestMapping(value = "/contact", method = RequestMethod.GET)
	@ResponseBody
      public ResponseEntity <List<Contact>>  listUsuarios() {
           List<Contact> contacts = contactService.listcontact() ;
		
		if(contacts.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		return new ResponseEntity<>(contacts,HttpStatus.OK);
		
		
	}
	*/