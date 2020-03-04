package com.pitang.aula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.aula.model.Contact;
import com.pitang.aula.model.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {
	 
	List<Story>  findByStoryOwnerId(Long id);
	
}
