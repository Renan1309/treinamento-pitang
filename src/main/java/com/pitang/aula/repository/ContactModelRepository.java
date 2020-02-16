package com.pitang.aula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.aula.model.Contact;
import com.pitang.aula.model.UserModel;

public interface ContactModelRepository extends JpaRepository<Contact, Long > {
	
     List<Contact>  findByUserModelId(Long id);
}
