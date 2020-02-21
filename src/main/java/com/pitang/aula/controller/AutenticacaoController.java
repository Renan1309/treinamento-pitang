package com.pitang.aula.controller;

import java.util.List;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.aula.dto.LoginForm;
import com.pitang.aula.dto.TokenDto;
import com.pitang.aula.dto.UserDto;
import com.pitang.aula.dto.UsuarioForm;
import com.pitang.aula.mapper.ModelMapperComponent;
import com.pitang.aula.model.UserModel;
import com.pitang.aula.servc.ContactService;
import com.pitang.aula.servc.UserService;

import io.jsonwebtoken.Jwts;

@RestController
public class AutenticacaoController {
	
	private UserService userService;
	
	
	public  AutenticacaoController (UserService userService ) {
		super();
		this.userService = userService ;
		
	}
	
		@RequestMapping(value = "/autentica", method = RequestMethod.POST)
		@ResponseBody
		                   
		public ResponseEntity<TokenDto> autentica(@RequestBody UsuarioForm userform) {
			
			
			
			String token =  userService.authentication(userform);
			TokenDto tokenDto = new TokenDto();
			tokenDto.setType("Bearer");
			tokenDto.setToken(token);
			return new ResponseEntity<>(tokenDto, HttpStatus.OK);
			
			
			
		}

	
	
	
	
}
