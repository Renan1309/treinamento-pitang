package com.pitang.aula.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.aula.model.UserModel;

public interface UserModelRepository  extends JpaRepository<UserModel, Long>{
	
	UserModel findByName(String usu_name);
	
	Optional<UserModel> findById(Long id);//perguntar a anderson sobre esse optional
	
	UserModel findByEmail(String email);
	
	
	

}
