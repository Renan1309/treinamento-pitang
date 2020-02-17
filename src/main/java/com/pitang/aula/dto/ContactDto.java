package com.pitang.aula.dto;

public class ContactDto {
	//class
	private Long Id ;
	private Long Id_usu;
	private String name ;
	private Long idUserContact ;
	
	public Long getId_usu() {
		return Id_usu;
	}
	public void setId_usu(Long id_usu) {
		Id_usu = id_usu;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getIdUserContact() {
		return idUserContact;
	}
	public void setIdUserContact(Long idUserContact) {
		this.idUserContact = idUserContact;
	}
	
}
