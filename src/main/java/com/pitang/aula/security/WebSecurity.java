package com.pitang.aula.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pitang.aula.repository.ContactModelRepository;
import com.pitang.aula.repository.UserModelRepository;
import com.pitang.aula.servc.TokenServiceJwt;
import com.pitang.aula.service.imple.JwtUserDetailsServiceImpl;


@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImpl userDetailsService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private TokenServiceJwt tokenServiceJwt ;
	
	@Autowired
	private UserModelRepository userModelRepository;
	
	 @Autowired
	 private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;
	
	//adicionei isso
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	//adicionei isso
	
	public WebSecurity(UserDetailsServiceImpl userDetailsService,BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	//esse metodo é o de configuracao de autenticacao
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    
	@Override
	//esse metodo é o de configuracao de autorizacao
    protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin();
        http.csrf()
		        .disable()
		        .authorizeRequests().antMatchers("/h2-console**").permitAll()
		        .antMatchers("/auth").permitAll()
		       // .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                                                  //metodo que inibi a criacao por secçao pq a gente vai autenticar via token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticationTokenFilter(tokenServiceJwt ,userModelRepository , jwtUserDetailsServiceImpl), UsernamePasswordAuthenticationFilter.class)//adicionei o filter before para interceptar antes de tudo
                ;
    }
	
}