package com.pitang.aula.controller;

import java.time.LocalDateTime;
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
import com.pitang.aula.dto.MensagemDto;
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
	public ResponseEntity<List<MensagemDto>> listconversa(@PathVariable("id_user") Long id_user,
			@PathVariable("id_contact") Long id_contact, @PathVariable("statusmsg") Boolean statusmsg) {

		LocalDateTime dataexata = LocalDateTime.now();
		System.out.println("Essa data ===> "+dataexata);
		
		List<MensagemDto> msg = contentMenssageService.listarMensagensAtivas(id_user, id_contact, statusmsg);

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
	public ResponseEntity<MensagemDto> enviarMenssage(@RequestBody ContentMenssageDto contentMenssagedto) {
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
		
		MensagemDto mensagemdto = ModelMapperComponent.modelMapper.map(msg,new TypeToken<MensagemDto>() {}.getType());
		
		
		return new ResponseEntity<>(mensagemdto, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/message/user/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<MensagemDto> deletarMensagemIndividual(@PathVariable("id") Long id ,@RequestBody MensagemDto mensagemDto ) {
		
		if(mensagemDto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		
		ContentMenssage contentMenssage = ModelMapperComponent.modelMapper.map(mensagemDto,new TypeToken<ContentMenssage>() {}.getType());
		
		 UserModel userowner =	userService.findByUserById(mensagemDto.getIdusermsg());
         if(userowner == null) {
         	throw new ExceptionBadRequest("Id de usuário inválido !");
         }
         UserModel usertarget =	userService.findByUserById(mensagemDto.getIdusercontact());
         if(usertarget == null) {
         	throw new ExceptionBadRequest("Id de usuário inválido !");
         }
         
         contentMenssage.setIdusermsg(userowner);
         contentMenssage.setIdusercontact(usertarget);
         
		ContentMenssage msg = contentMenssageService.deletarMensagemIndividual(id , contentMenssage);
		
		MensagemDto mensagemdto = ModelMapperComponent.modelMapper.map(msg,new TypeToken<MensagemDto>() {}.getType());
		
		return new ResponseEntity<>( mensagemdto ,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/message/delete/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<MensagemDto> deletarMesagemGeral(@PathVariable("id") Long id ,@RequestBody MensagemDto mensagemDto ) {
		
		if(mensagemDto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		
		ContentMenssage contentMenssage = ModelMapperComponent.modelMapper.map(mensagemDto,new TypeToken<ContentMenssage>() {}.getType());
		
		 UserModel userowner =	userService.findByUserById(mensagemDto.getIdusermsg());
         if(userowner == null) {
         	throw new ExceptionBadRequest("Id de usuário inválido !");
         }
         UserModel usertarget =	userService.findByUserById(mensagemDto.getIdusercontact());
         if(usertarget == null) {
         	throw new ExceptionBadRequest("Id de usuário inválido !");
         }
         
         contentMenssage.setIdusermsg(userowner);
         contentMenssage.setIdusercontact(usertarget);
		
		ContentMenssage msg = contentMenssageService.deletarMensagem(id , contentMenssage);	
		
		MensagemDto mensagemdto = ModelMapperComponent.modelMapper.map(msg,new TypeToken<MensagemDto>() {}.getType());
		return new ResponseEntity<>( mensagemdto ,HttpStatus.OK);
		
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
