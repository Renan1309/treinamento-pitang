package com.pitang.aula.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "history_passwords")
public class HistoryPasswordsModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	 @NotNull
	 //@Size(max = 100)
	 @Column(name = "previous_password")
	 private String previousPassword;
	 
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "user_id", nullable = false)
	 private UserModel userOwner;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPreviousPassword() {
		return previousPassword;
	}

	public void setPreviousPassword(String previousPassword) {
		this.previousPassword = previousPassword;
	}

	public UserModel getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(UserModel userOwner) {
		this.userOwner = userOwner;
	}
	 
	 
}
