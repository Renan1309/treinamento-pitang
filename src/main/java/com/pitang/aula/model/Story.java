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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "story")
public class Story {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	@NotNull
	@Size(max = 70)
	@Column(name = "story_message")
	private String message ;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "user_id")
	private UserModel storyOwner;

	public UserModel getStoryOwner() {
		return storyOwner;
	}

	public void setStoryOwner(UserModel storyOwner) {
		this.storyOwner = storyOwner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
