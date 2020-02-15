package com.pitang.aula.security;


import static java.util.Collections.emptyList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.pitang.aula.repository.UserModelRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserModelRepository userRepository;

    public UserDetailsServiceImpl(UserModelRepository usuarioRepository) {
		super();
		this.userRepository = usuarioRepository;
	}

	@Override
    public UserDetails loadUserByUsername(String userName) {
        /*UserModel applicationUser = userRepository.findByUserName(userName);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(userName);
        } 
        return new User(applicationUser.getUserName(),applicationUser.getPassword(),applicationUser.isStatus(),true,true,true,emptyList());*/
		return new User("Renan","password",true,true,true,true,emptyList());
    }
}
