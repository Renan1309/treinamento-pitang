package com.pitang.aula.service.imple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pitang.aula.dto.TalkDto;
import com.pitang.aula.model.Contact;
import com.pitang.aula.model.Story;
import com.pitang.aula.model.UserModel;
import com.pitang.aula.repository.StoryRepository;
import com.pitang.aula.servc.StoryService;


@Service
public class StoryServiceImpl implements StoryService {
	
	@Autowired
	private StoryRepository  storyRepository ;
	
	@Autowired
	private ContactServiceImpl ContactServiceImpl;
	
	
	@Override
	public List<Story> listAllStory() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Story creatStory(MultipartFile file, Story story) {
		// TODO Auto-generated method stub
		
		
		
		return storyRepository.save(story);
	}


	@Override
	public List<Story> listStoryMyContacts(UserModel userModel) {
		// TODO Auto-generated method stub
		List<Contact> contactList = ContactServiceImpl.userContacts(userModel.getId());
		List<Story> storyMyContacts = new ArrayList<Story>()  ;
		
		for (Contact contact : contactList) {
			
			List<Story> storyMyContactsDB = storyRepository.findByStoryOwnerId(contact.getIdUserContact());
			
			storyMyContacts.addAll(storyMyContactsDB);
			
		}
		
		return storyMyContacts;
	}

}
