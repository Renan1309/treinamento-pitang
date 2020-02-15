package com.pitang.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.aula.model.Contact;

public interface ContactModelRepository extends JpaRepository<Contact, Long > {

}
