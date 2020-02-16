package com.pitang.aula.mapper;
import javax.annotation.PostConstruct;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.pitang.aula.dto.ContactDto;
import com.pitang.aula.dto.UserDto;
import com.pitang.aula.model.Contact;
import com.pitang.aula.model.UserModel;



@Component
public class ModelMapperComponent {
	
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
                    	map().setFoneContact(source.getFoneContact());
                    	
                    	
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<ContactDto , Contact>() {
                    @Override
                    protected void configure() {
                    	map().setFoneContact(source.getFoneContact());
                    	map().setName(source.getName());
                    	skip().setUserModel(null);
                    	
                    	
                    }
                });
        
     
        
	}
}