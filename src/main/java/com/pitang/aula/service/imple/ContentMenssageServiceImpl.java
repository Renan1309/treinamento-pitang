package com.pitang.aula.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitang.aula.model.ContentMenssage;
import com.pitang.aula.repository.ContentMenssageRepository;
import com.pitang.aula.servc.ContentMenssageService;


@Service
public class ContentMenssageServiceImpl implements ContentMenssageService {
	
	@Autowired
	private ContentMenssageRepository contentMenssageRepository ;
	
	@Override
	public List<ContentMenssage> listAllMenssage() {
		// TODO Auto-generated method stub
		return contentMenssageRepository.findAll();
	}

	@Override
	public List<ContentMenssage> findAllMenssageId(Long id) {
		contentMenssageRepository.findByIdusermsg(id);
		return contentMenssageRepository.findByIdusermsg(id);
	}

}
