package com.pitang.aula.service.imple;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitang.aula.model.UserModel;
import com.pitang.aula.repository.UserModelRepository;
import com.pitang.aula.servc.TokenServiceJwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class TokenServiceJwtImpl  implements TokenServiceJwt{

	@Autowired
	private UserModelRepository userRepository ;
	
	private static final Long expirationTime = (long) 8900000;
	private String secretKey = "teste";
	Date hoje = new Date();
	Date dataExp = new Date(hoje.getTime()+ expirationTime);
	
	@Override
	public String generateToken(UserModel user) {
		
		
		
		return Jwts.builder()//quem esta gerando o token , qual aplicacao ta gerando
				.setIssuer("API de smsm")
				.setIssuedAt(hoje)  //data de geracao do token
				.setSubject(user.getId().toString()) //passar algo que identifique unicamente o meu usuário EX .: pode ser o id do cliente
				.setExpiration(dataExp) 
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
				
		}

	@Override
	public boolean validaToken(String token) {
		// TODO Auto-generated method stub
		try {
			Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
			return true ;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		//devolve o clains que onde eu pego o token 
		
		
	}

	@Override
	public Long getIdUser(String token) {
		// TODO Auto-generated method stub
		Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
		
		return Long.parseLong(claims.getSubject());
	}

}
