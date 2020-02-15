package com.pitang.aula.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pitang.aula.model.UserModel;
import com.pitang.aula.repository.UserModelRepository;
import com.pitang.aula.servc.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserModelRepository userRepository ;

	@Override
	public List<UserModel> listUser() {

		if (userRepository.findAll().size() == 0) {
			return null ;
		}
		return userRepository.findAll();
	}

	@Override
	public UserModel findByUserByUserName(String usu_name) {
		return null;
	}
	
	@Override
	public UserModel findByUserById(Long id_usu ) {
		Optional<UserModel> user = userRepository.findById(id_usu);
		if(user.isPresent()) {
			return user.get() ;	
		}
		else {
			return null;
		}

	}

	//Metodo criado para realizar o cadastro
	@Override
	public UserModel creatUser(UserModel user) {
		
		checkUser(user);
		userRepository.save(user);
		return userRepository.save(user);
	}
	
	//Metodo criado para realizar a alteração do cadastro
	@Override
	public UserModel updateUser(Long id ,UserModel userChange) {
		Optional<UserModel> user = userRepository.findById(id);
		UserModel useredit = user.get() ;
		useredit.setName(userChange.getName());
		
		userRepository.save(useredit);
		
		return useredit;
		
		
	}
	
	@Override
	public void deleteUser(Long id ) {
		
		userRepository.deleteById(id);
			
	}
	
    
	private void checkUser(UserModel user) {
		if(user == null) {
			//throw new ExceptionBadRequest("Usuário não pode ser nulo!");
			System.out.println("Usuário enviado nulo !");
		}
		if(StringUtils.isEmpty(user.getEmail())){
			System.out.println("O email não pode ser vazio!");
		}
		if(StringUtils.isEmpty(user.getName())) {
			System.out.println("O nome não pode ser vazio!");
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			System.out.print("A senha não pode ser enviada vazio");
		} 
	}

	
	
	

}
