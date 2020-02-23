package com.pitang.aula.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitang.aula.model.Story;
import com.pitang.aula.repository.StoryRepository;
import com.pitang.aula.servc.StoryService;


@Service
public class StoryServiceImpl implements StoryService {
	
	@Autowired
	private StoryRepository  storyRepository ;
	
	
	@Override
	public List<Story> listAllStory() {
		// TODO Auto-generated method stub
		return null;
	}

}
