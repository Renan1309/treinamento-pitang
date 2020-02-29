package com.pitang.aula.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.aula.dto.ContactDto;
import com.pitang.aula.dto.UserDto;
import com.pitang.aula.mapper.ModelMapperComponent;
import com.pitang.aula.model.Contact;
import com.pitang.aula.model.UserModel;
import com.pitang.aula.servc.ContactService;
import com.pitang.aula.servc.UserService;

@RestController
public class ContatoController {
	
	private ContactService contactService ;
	
	
	public ContatoController (ContactService contactService) {
		super();
		this.contactService = contactService ;
		
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	@ResponseBody
      public ResponseEntity <List<ContactDto>>  listContatosAll() {
           List<Contact> contacts = contactService.listcontact() ;
		
		if(contacts.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ContactDto> contactDto = ModelMapperComponent.modelMapper.map(contacts, new TypeToken<List<ContactDto>>() {}.getType());

		
		return new ResponseEntity<>(contactDto,HttpStatus.OK);
		
		
	}
	
	
	
	@RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
	@ResponseBody
      public ResponseEntity <List<ContactDto>>  listContatosUsuario( @RequestHeader("Authorization") String tokenjwt ,@PathVariable("id") Long id) {
		
		System.out.println("O token interceptado na roda /contato/id => " + tokenjwt);
           List<Contact> contacts = contactService.userContacts(id) ;
		
		if(contacts.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<ContactDto> contactDto = ModelMapperComponent.modelMapper.map(contacts, new TypeToken<List<ContactDto>>() {}.getType());

		
		return new ResponseEntity<>(contactDto,HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
	@ResponseBody
      public ResponseEntity <String>  DeleteContatoUsuario(@PathVariable("id") Long id) {
      
		if(id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		String retorno = contactService.deleteContact(id);
		return new ResponseEntity<>(retorno,HttpStatus.OK);
		
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
