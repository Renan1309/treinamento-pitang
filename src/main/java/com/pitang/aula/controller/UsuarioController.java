package com.pitang.aula.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pitang.aula.dto.ReturnData;
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
      public ResponseEntity <List<UserModel>>  listUsuarios() {
           List<UserModel> users = userService.listUser() ;
		
		if(users.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(users,HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ResponseBody
      public ResponseEntity <UserModel>  listUsuario(@PathVariable("id") Long id ) {
           UserModel user = userService.findByUserById(id) ;
		
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		//ReturnData rt = new ReturnData<UserModel>();
		//rt.setData(user);
		//rt.setMensagemRetorno(mensagemRetorno);
		return new ResponseEntity<>(user,HttpStatus.OK);
		
		
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	                    //UsuarioForm
	public ResponseEntity<UserModel> cadastrarUsuario(@RequestBody UserModel user) {
		
		//realizar a logica para mapear o UsuarioForm para um Usuario e chamar o servico
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
		
		user  = userService.creatUser(user);
		return new ResponseEntity<>(user , HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<UserModel> changeUsuario(@PathVariable("id") Long id ,@RequestBody UserModel user ) {
		
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
		user = userService.updateUser(id, user);
		return new ResponseEntity<>(user , HttpStatus.OK);
		
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
