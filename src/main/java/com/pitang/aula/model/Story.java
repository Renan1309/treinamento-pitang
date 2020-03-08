package com.pitang.aula.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "story")
public class Story {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "story_image")
	private String pathStory;

	@NotNull
	@Size(max = 70)
	@Column(name = "story_message")
	private String message;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datastory", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date datastory;

	//@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserModel storyOwner;

	public String getPathStory() {
		return pathStory;
	}

	public void setPathStory(String pathStory) {
		this.pathStory = pathStory;
	}

	public Date getDatastory() {
		return datastory;
	}

	public void setDatastory(Date datastory) {
		this.datastory = datastory;
	}

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
