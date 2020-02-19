package com.pitang.aula.controller;

import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.aula.dto.ContactDto;
import com.pitang.aula.mapper.ModelMapperComponent;
import com.pitang.aula.model.Contact;
import com.pitang.aula.model.ContentMenssage;
import com.pitang.aula.servc.ContactService;
import com.pitang.aula.servc.ContentMenssageService;

@RestController
public class ContentMenssageController {

	private ContentMenssageService contentMenssageService;
	
	public ContentMenssageController (ContentMenssageService contentMenssageService) {
		super();
		this.contentMenssageService = contentMenssageService ;
		
	}
	
	@RequestMapping(value = "/mensage", method = RequestMethod.GET)
	@ResponseBody
	 public ResponseEntity <List<ContentMenssage>>  listTodasMens() {
        List<ContentMenssage> msg = contentMenssageService.listAllMenssage() ;
		
		if(msg.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		

		
		return new ResponseEntity<>(msg,HttpStatus.OK);
		
		
	}
	
	
	@RequestMapping(value = "/mensagens/user/{id_user}/contact/{id_contact}/status/{statusmsg}", method = RequestMethod.GET)
	@ResponseBody
	 public ResponseEntity <List<ContentMenssage>>  listconversa(@PathVariable("id_user") Long id_user , @PathVariable("id_contact") Long id_contact ,@PathVariable("statusmsg") Boolean statusmsg ) {
		
		List<ContentMenssage> msg = contentMenssageService.listarMensagensAtivas(id_user, id_contact , statusmsg) ;
		
		
		if(msg.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		

		
		return new ResponseEntity<>(msg,HttpStatus.OK);
		
		
	}
}
