package com.pitang.aula.mapper;
import javax.annotation.PostConstruct;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pitang.aula.dto.ContactDto;
import com.pitang.aula.dto.ContentMenssageDto;
import com.pitang.aula.dto.MensagemDto;
import com.pitang.aula.dto.MessageDto;
import com.pitang.aula.dto.StoryDto;
import com.pitang.aula.dto.UserDto;
import com.pitang.aula.exceptions.ExceptionBadRequest;
import com.pitang.aula.model.Contact;
import com.pitang.aula.model.ContentMenssage;
import com.pitang.aula.model.Story;
import com.pitang.aula.model.UserModel;
import com.pitang.aula.servc.UserService;



@Component
public class ModelMapperComponent {
	//classes de modelmapper
	
	
	
	public static final ModelMapper modelMapper= new ModelMapper();
	
	@PostConstruct
	private void configureMapper() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        modelMapper.addMappings(
                new PropertyMap<UserModel, UserDto>() {
                    @Override
                    protected void configure() {
                    	map().setId(source.getId());
                    	map().setEmail(source.getEmail());
                    	map().setName((source.getName()));
                    	map().setSurname(source.getSurname());
                    	map().setPathImage(source.getPathImage());
                        when(Conditions.isNotNull()).using(ModelConverter.convertStatus).map(source.isStatus()).setStatus(null);
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<UserDto , UserModel>() {
                    @Override
                    protected void configure() {
                    	map().setId(source.getId());
                    	map().setEmail(source.getEmail());
                    	map().setName((source.getName()));
                    	map().setSurname(source.getSurname());
                    	map().setPathImage(source.getPathImage());
                        when(Conditions.isNotNull()).using(ModelConverter.convertStatusToBoolean).map(source.getStatus()).setStatus(false);
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<Contact , ContactDto>() {
                    @Override
                    protected void configure() {
                    	map().setId(source.getId());
                    	map().setId_usu(source.getUserModel().getId());
                    	map().setName(source.getName());
                    	map().setIdUserContact(source.getIdUserContact());
                    	
                    	
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<ContactDto , Contact>() {
                    @Override
                    protected void configure() {
                    	map().setIdUserContact(source.getIdUserContact());
                    	map().setName(source.getName());
                    	skip().setUserModel(null);	
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<ContentMenssageDto , ContentMenssage>() {
                    @Override
                    protected void configure() {
                    	map().setContentmsg(source.getContentmsg());
                    	//map().setIdusermsg(userowner);
                    	//map().setIdusercontact(usertarget);
                    	
                    	
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<MessageDto , ContentMenssage>() {
                    @Override
                    protected void configure() {
                   
                    	map().setId(source.getId());
                    	//map().setIdusermsg(userowner);
                    	//map().setIdusercontact(usertarget);
                    	map().setStatusSend(source.getStatusSend());
                    	map().setStatusRecipient(source.getStatusRecipient());
                    	
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<ContentMenssage , MensagemDto>() {
                    @Override
                    //MAPPER PARA RETORNAR MENSAGEM
                    protected void configure() {
                   
                    	map().setId(source.getId());
                    	map().setContentmsg(source.getContentmsg());
                    	map().setIdusermsg(source.getIdusermsg().getId());
                    	map().setIdusercontact(source.getIdusercontact().getId());
                    	map().setStatusSend(source.getStatusSend());
                    	map().setStatusRecipient(source.getStatusRecipient());
                    	
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<StoryDto , Story>() {
                    @Override
                    protected void configure() {
                    	
                    	map().setMessage(source.getMessage());
                   
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<Story ,StoryDto>() {
                    @Override
                    protected void configure() {
                    	map().setId(source.getId());
                    	map().setMessage(source.getMessage());
                    	map().setStoryOwner(source.getStoryOwner().getId());
                    	
                    	
                   
                    }
                });
       
        /*
        modelMapper.addMappings(
                new PropertyMap<MessageDto , ContentMenssage>() {
                    @Override
                    protected void configure() {
                    	map().setId(source.getId());
                    	//map().setIdusermsg(source.getIdusermsg());
                    	//map().setIdusercontact(source.getIdusercontact());
                    	map().setStatusSend(source.getStatusSend());
                    	map().setStatusRecipient(source.getStatusRecipient());
                    	
                    }
                });
        
        
        */
     
        
	}
}