package com.pitang.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.aula.model.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {

}
