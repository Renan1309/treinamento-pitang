package com.pitang.aula.mapper;
import javax.annotation.PostConstruct;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.pitang.aula.dto.UserDto;
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
                    	map().setName(source.getName());
                    	map().setSurname(source.getSurname());
                    	map().setStatus(source.getStatus());
                    }
                });
        
        modelMapper.addMappings(
                new PropertyMap<UserDto , UserModel>() {
                    @Override
                    protected void configure() {
                    	map().setId(source.getId());
                    	map().setEmail(source.getEmail());
                    	map().setName(source.getName());
                    	map().setSurname(source.getSurname());
                    	map().setStatus(source.isStatus());
                    }
                });
        
	}
}