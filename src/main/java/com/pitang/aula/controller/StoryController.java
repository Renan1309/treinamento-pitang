package com.pitang.aula.controller;

import java.io.IOException;
import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pitang.aula.dto.StoryDto;
import com.pitang.aula.dto.UserDto;
import com.pitang.aula.mapper.ModelMapperComponent;
import com.pitang.aula.model.Story;
import com.pitang.aula.model.UserModel;
import com.pitang.aula.servc.StoryService;
import com.pitang.aula.servc.UserService;

@RestController
public class StoryController {
	
	private StoryService storyService ;
	private UserService userService ;
	
	public StoryController(StoryService storyService , UserService userService ) {
		// TODO Auto-generated constructor stub
		super();
		this.storyService = storyService;
		this.userService = userService;
	}
	

	@RequestMapping(value = "/story", method = RequestMethod.POST )
	@ResponseBody
	                   
	public ResponseEntity<StoryDto> creatStory(@RequestParam("file") MultipartFile file ,@RequestParam("storyPost") String storyPost) throws IOException {
		
		
		ObjectMapper objectMapper = new ObjectMapper();
	    StoryDto storyDto = null;
	    storyDto = objectMapper.readValue(storyPost, StoryDto.class);
	    Story storyCreat ;
	    storyCreat  = ModelMapperComponent.modelMapper.map(storyDto, new TypeToken<Story>() {}.getType());
	    
	    UserModel userOwnerStory =  userService.findByUserById(storyDto.getStoryOwner());
	    
	    storyCreat.setStoryOwner(userOwnerStory);
	    
	    storyCreat = storyService.creatStory(file, storyCreat);
	    
	    storyDto = ModelMapperComponent.modelMapper.map(storyCreat, new TypeToken<StoryDto>() {}.getType());
	     
	    byte[] bytesStory = storyService.bytesDoStory(storyCreat.getPathStory());
	    storyDto.setImagebyte(bytesStory);
	     
	     
		return new ResponseEntity<>(storyDto,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/story/{id}", method = RequestMethod.GET )
	@ResponseBody
	public ResponseEntity <List<StoryDto>> listStoryContacts (@PathVariable("id") Long id) throws IOException {
		if( id == null || id == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
		UserModel userModel = userService.findByUserById(id);
		
		List<StoryDto> storyCreat = storyService.listStoryMyContacts(userModel) ;
		
		return new ResponseEntity<>(storyCreat,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/story/user/{iduser}/story/{idstory}", method = RequestMethod.DELETE)
	@ResponseBody
      public ResponseEntity <String>  DeleteStoryUsuario(@PathVariable("iduser") Long iduser , @PathVariable("idstory") Long idstory) {
      
		if(iduser == null || iduser == 0 || idstory == null || idstory == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		String retorno = storyService.DeleteStory(iduser, idstory);
		return new ResponseEntity<>(retorno,HttpStatus.OK);
		
	}
}
