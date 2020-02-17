package com.pitang.aula.servc;

import java.util.List;

import com.pitang.aula.model.ContentMenssage;

public interface ContentMenssageService {
	
	public List<ContentMenssage> listAllMenssage();
	
	public List<ContentMenssage> findAllMenssageId(Long id);
	
	
}
