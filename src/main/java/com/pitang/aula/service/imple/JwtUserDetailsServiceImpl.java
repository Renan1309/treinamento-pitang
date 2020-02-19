package com.pitang.aula.service.imple;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.pitang.aula.model.UserModel;
import com.pitang.aula.repository.UserModelRepository;

@Component
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserModelRepository userRepository ;
	
	 @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	        UserModel user = userRepository.findByEmail(email);
	        if (user == null) {
	            throw new UsernameNotFoundException("User not found with username: " + email);
	        }
	        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
	                new ArrayList<>());
	    }
	
}
