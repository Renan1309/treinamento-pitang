package com.pitang.aula.controller;

import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.pitang.aula.mapper.ModelMapperComponent;
import com.pitang.aula.dto.ReturnData;
import com.pitang.aula.dto.UserDto;
import com.pitang.aula.dto.UsuarioForm;
import com.pitang.aula.model.UserModel;
import com.pitang.aula.servc.UserService;

@RestController
public class UsuarioController {
	
	private UserService userService;
	
	public UsuarioController(UserService userService) {
		super();
		this.userService = userService ;
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

}
