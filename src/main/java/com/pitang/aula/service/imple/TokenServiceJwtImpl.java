package com.pitang.aula.service.imple;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitang.aula.model.UserModel;
import com.pitang.aula.repository.UserModelRepository;
import com.pitang.aula.servc.TokenServiceJwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class TokenServiceJwtImpl  implements TokenServiceJwt{

	//@Autowired
	//private UserModelRepository userRepository ;
	
	private static final Long expirationTime = (long) 8900000;
	private String secretKey = "teste";
	
	@Override
	public String generateToken(UserModel user) {
		
		return Jwts.builder()
				
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject("teste api")
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
				
		}

}
