package com.pitang.aula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.aula.model.ContentMenssage;

public interface ContentMenssageRepository extends JpaRepository<ContentMenssage, Long> {
	
	List<ContentMenssage> findByIdusermsg(Long id) ;
	
	//List<ContentMenssage> findByIdusermsgAndIdfonecontact(String foneid);
	
}
