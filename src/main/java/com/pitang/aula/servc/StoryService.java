package com.pitang.aula.servc;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pitang.aula.model.Story;
import com.pitang.aula.model.UserModel;

public interface StoryService {

	public List<Story> listAllStory();
	
	public Story creatStory (MultipartFile file, Story story);
	
	public List<Story> listStoryMyContacts(UserModel userModel);

	String guardarStory(MultipartFile file, Story story);
	
}



