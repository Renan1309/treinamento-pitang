package com.pitang.aula.controller;

import java.util.Date;
import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.aula.dto.ContactDto;
import com.pitang.aula.dto.ContentMenssageDto;
import com.pitang.aula.dto.MessageDto;
import com.pitang.aula.dto.TalkDto;
import com.pitang.aula.dto.UserDto;
import com.pitang.aula.exceptions.ExceptionBadRequest;
import com.pitang.aula.mapper.ModelMapperComponent;
import com.pitang.aula.model.Contact;
import com.pitang.aula.model.ContentMenssage;
import com.pitang.aula.model.UserModel;
import com.pitang.aula.servc.ContactService;
import com.pitang.aula.servc.ContentMenssageService;
import com.pitang.aula.servc.UserService;

@RestController
public class ContentMenssageController {

	private ContentMenssageService contentMenssageService;

	private UserService userService ;

	public ContentMenssageController(ContentMenssageService contentMenssageService , UserService userService ) {
		super();
		this.contentMenssageService = contentMenssageService;
		this.userService = userService ;

	}

	@RequestMapping(value = "/mensage", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ContentMenssage>> listTodasMens() {
		List<ContentMenssage> msg = contentMenssageService.listAllMenssage();

		if (msg.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@RequestMapping(value = "/mensagens/user/{id_user}/contact/{id_contact}/status/{statusmsg}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ContentMenssage>> listconversa(@PathVariable("id_user") Long id_user,
			@PathVariable("id_contact") Long id_contact, @PathVariable("statusmsg") Boolean statusmsg) {

		List<ContentMenssage> msg = contentMenssageService.listarMensagensAtivas(id_user, id_contact, statusmsg);

		if (msg.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(msg, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/mensagens/user/{id_user}/contact/{id_contact}/status/{statusmsg}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<List<TalkDto>> deletarConversaEmensage(@PathVariable("id_user") Long id_user,
			@PathVariable("id_contact") Long id_contact, @PathVariable("statusmsg") Boolean statusmsg) {

		List<TalkDto> msg = contentMenssageService.excluiConversa(id_user, id_contact, statusmsg);

		if (msg.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(msg, HttpStatus.OK);

	}
	

	@RequestMapping(value = "/mensage/new", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ContentMenssage> enviarMenssage(@RequestBody ContentMenssageDto contentMenssagedto) {
		if (contentMenssagedto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ContentMenssage contentMenssage = ModelMapperComponent.modelMapper.map(contentMenssagedto,
				new TypeToken<ContentMenssage>() {
				}.getType());
		 UserModel userowner =	userService.findByUserById(contentMenssagedto.getIdusermsg());
         if(userowner == null) {
         	throw new ExceptionBadRequest("Id de usuário inválido !");
         }
         UserModel usertarget =	userService.findByUserById(contentMenssagedto.getIdusercontact());
         if(usertarget == null) {
         	throw new ExceptionBadRequest("Id de usuário inválido !");
         }
         
         contentMenssage.setIdusermsg(userowner);
         contentMenssage.setIdusercontact(usertarget);
		
		ContentMenssage msg = contentMenssageService.enviarMenssage(contentMenssage);

		return new ResponseEntity<>(msg, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/message/user/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<ContentMenssage> deletarMensagemIndividual(@PathVariable("id") Long id ,@RequestBody MessageDto messageDto ) {
		
		if(messageDto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		
		ContentMenssage contentMenssage = ModelMapperComponent.modelMapper.map(messageDto,new TypeToken<ContentMenssage>() {}.getType());
		
		 UserModel userowner =	userService.findByUserById(messageDto.getIdusermsg());
         if(userowner == null) {
         	throw new ExceptionBadRequest("Id de usuário inválido !");
         }
         UserModel usertarget =	userService.findByUserById(messageDto.getIdusercontact());
         if(usertarget == null) {
         	throw new ExceptionBadRequest("Id de usuário inválido !");
         }
         
         contentMenssage.setIdusermsg(userowner);
         contentMenssage.setIdusercontact(usertarget);
         
		ContentMenssage msg = contentMenssageService.deletarMensagemIndividual(id , contentMenssage);		
		return new ResponseEntity<>( msg ,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/message/delete/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<ContentMenssage> deletarMesagemGeral(@PathVariable("id") Long id ,@RequestBody MessageDto messageDto ) {
		
		if(messageDto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		
		ContentMenssage contentMenssage = ModelMapperComponent.modelMapper.map(messageDto,new TypeToken<ContentMenssage>() {}.getType());
		
		 UserModel userowner =	userService.findByUserById(messageDto.getIdusermsg());
         if(userowner == null) {
         	throw new ExceptionBadRequest("Id de usuário inválido !");
         }
         UserModel usertarget =	userService.findByUserById(messageDto.getIdusercontact());
         if(usertarget == null) {
         	throw new ExceptionBadRequest("Id de usuário inválido !");
         }
         
         contentMenssage.setIdusermsg(userowner);
         contentMenssage.setIdusercontact(usertarget);
		
		ContentMenssage msg = contentMenssageService.deletarMensagem(id , contentMenssage);		
		return new ResponseEntity<>( msg ,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/conversas/{id_user}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<TalkDto>> listConversas(@PathVariable("id_user") Long id_user) {
		List<TalkDto> msg = contentMenssageService.listarConversas(id_user);

		if (msg.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(msg, HttpStatus.OK);

	}
}
