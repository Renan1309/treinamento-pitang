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
import com.pitang.aula.dto.UserDto;
import com.pitang.aula.mapper.ModelMapperComponent;
import com.pitang.aula.model.UserModel;

import io.jsonwebtoken.Jwts;

@RestController
public class AutenticacaoController {
	
	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	@ResponseBody
      public ResponseEntity  autenticar(@RequestBody LoginForm loginform) {
		return null;
		//UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(loginform.getEmail(), loginform.getSenha());
	
		
	}
	
	
	
	
}
