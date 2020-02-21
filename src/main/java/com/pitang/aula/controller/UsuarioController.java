package com.pitang.aula.controller;

import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.aula.dto.ContactDto;
import com.pitang.aula.dto.TokenDto;
import com.pitang.aula.dto.UserDto;
import com.pitang.aula.dto.UsuarioForm;
import com.pitang.aula.mapper.ModelMapperComponent;
import com.pitang.aula.model.Contact;
import com.pitang.aula.model.UserModel;
import com.pitang.aula.servc.ContactService;
import com.pitang.aula.servc.UserService;

@RestController
public class UsuarioController {
	
	private UserService userService;
	private ContactService contactService ;
	
	public UsuarioController(UserService userService , ContactService contactService) {
		super();
		this.userService = userService ;
		this.contactService = contactService ;
	}
	
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
      public ResponseEntity <List<UserDto>>  listUsuarios() {
           List<UserModel> users = userService.listUser() ;
		
		if(users.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<UserDto> usersDto = ModelMapperComponent.modelMapper.map(users, new TypeToken<List<UserDto>>() {}.getType());
		return new ResponseEntity<>(usersDto,HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ResponseBody
      public ResponseEntity <UserDto>  listUsuario(@PathVariable("id") Long id ) {
           UserModel user = userService.findByUserById(id) ;
           
        if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		//ReturnData rt = new ReturnData<UserModel>();
		//rt.setData(user);
		//rt.setMensagemRetorno(mensagemRetorno);
		UserDto usersDto = ModelMapperComponent.modelMapper.map(user, new TypeToken<UserDto>() {}.getType());

		return new ResponseEntity<>(usersDto,HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	                   
	public ResponseEntity<UserDto> cadastrarUsuario(@RequestBody UserDto userdto) {
		
		if(userdto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
		UserModel userModel = ModelMapperComponent.modelMapper.map(userdto, new TypeToken<UserModel>() {}.getType());
		
		userModel  = userService.creatUser(userModel);
		
		userdto = ModelMapperComponent.modelMapper.map(userModel, new TypeToken<UserDto>() {}.getType());
		
		return new ResponseEntity<>(userdto,HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/user/{id}/contact", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity <ContactDto>  cadastrarContatao(@PathVariable("id") Long id ,@RequestBody ContactDto contactdto) {
		
		UserModel user = contactService.findUserByContact(id);// verificando se o usuario do id tem se tiver adicionar ele em contato para fzr o relacionamento
		
		//pergunta a anderson sobre a validação do USER retornado
		
		Contact contact = ModelMapperComponent.modelMapper.map(contactdto, new TypeToken<Contact>() {}.getType());
		
		contact.setUserModel(user);//adicionando usuario ao contato para criar o relacionamento
		
		contact = contactService.creatContact(contact);
		
		if(contact == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		 contactdto = ModelMapperComponent.modelMapper.map(contact, new TypeToken<ContactDto>() {}.getType());
	    
		return new ResponseEntity<>(contactdto,HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<UserDto> changeUsuario(@PathVariable("id") Long id ,@RequestBody UserDto userdto ) {
		
		if(userdto == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		UserModel user =  ModelMapperComponent.modelMapper.map(userdto, new TypeToken<UserModel>() {}.getType());
		user = userService.updateUser(id, user);
		userdto = ModelMapperComponent.modelMapper.map(user, new TypeToken<UserDto>() {}.getType());
		return new ResponseEntity<>(userdto , HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<UserModel> changeUsuario(@PathVariable("id") Long id ) {
		if(id == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	//METODO DO AUTENTICCA
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	@ResponseBody
	                   
	public ResponseEntity<TokenDto> autentica(@RequestBody UsuarioForm userform) {
		
		
		
		String token =  userService.authentication(userform);
		TokenDto tokenDto = new TokenDto();
		tokenDto.setType("Bearer");
		tokenDto.setToken(token);
		return new ResponseEntity<>(tokenDto, HttpStatus.OK);
		
		
		
	}
    //METODO DO AUTENTICA
}
