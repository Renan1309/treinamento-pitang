package com.pitang.aula.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pitang.aula.model.UserModel;
import com.pitang.aula.repository.UserModelRepository;
import com.pitang.aula.servc.TokenServiceJwt;
import com.pitang.aula.service.imple.JwtUserDetailsServiceImpl;

public class AuthenticationTokenFilter extends OncePerRequestFilter {
		// fazemos a heranca da classe OncePerRequestFilter que é  um filtron do Spring que chamado uma vez a cada requisicao
	
	//não e possivel botar autowrite em filter 
	
	 private TokenServiceJwt tokenServiceJwt;
	 private UserModelRepository userModelRepository;
	 private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;
	 
	 public AuthenticationTokenFilter(TokenServiceJwt tokenServiceJwt , UserModelRepository userModelRepository,JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl) {
		// TODO Auto-generated constructor stub
		this.tokenServiceJwt = tokenServiceJwt;
		this.userModelRepository = userModelRepository;
		this.jwtUserDetailsServiceImpl = jwtUserDetailsServiceImpl;
	}
	
	
	@Override
	//Esse metodo doFilterInternal é onde nos fazemos a lógica para capturar o token e validar
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String token = pegarTokenRequest(request);
		boolean valido = tokenServiceJwt.validaToken(token);
		if(valido) {
			autenticaCliente(token);
			
		}
		//esse metodo apos a validacao esta ok libera ele para seguir o fluxo normal 
		filterChain.doFilter(request, response);
		
		
	}

		private void autenticaCliente(String token) {
		// TODO Auto-generated method stub
			Long idUsuario = tokenServiceJwt.getIdUser(token);
			UserModel usuario = userModelRepository.findById(idUsuario).get();
			final UserDetails userDetails = jwtUserDetailsServiceImpl

	                .loadUserByUsername(usuario.getEmail());
			//é um objeto autentication 
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
			//metodo do String para dizer usuario autenticado e forcar a liberacao
			SecurityContextHolder.getContext().setAuthentication(authentication);
	}


		private String pegarTokenRequest(HttpServletRequest request) {
			String token = request.getHeader("Authorization");
			if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
				return null;
			}
			return token.substring(7, token.length());
		}

}
