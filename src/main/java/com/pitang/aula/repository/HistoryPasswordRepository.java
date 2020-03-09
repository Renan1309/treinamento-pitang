package com.pitang.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.aula.model.HistoryPasswordsModel;

public interface HistoryPasswordRepository  extends JpaRepository<HistoryPasswordsModel, Long> {

	HistoryPasswordsModel  findByPreviousPasswordAndUserOwnerId(String password , Long id);
}
