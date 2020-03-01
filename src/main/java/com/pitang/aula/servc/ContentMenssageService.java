package com.pitang.aula.servc;

import java.util.List;

import com.pitang.aula.dto.TalkDto;
import com.pitang.aula.model.ContentMenssage;

public interface ContentMenssageService {
	
	public List<ContentMenssage> listAllMenssage();
	
	public List<ContentMenssage> findAllMenssageId(Long id);
	
	public List<ContentMenssage>  listarConversas(Long id_user , Long id_contact);
	
	public List<ContentMenssage> listarMensagensAtivas(Long id_user , Long id_contact, Boolean statusSend);
	
	public ContentMenssage enviarMenssage(ContentMenssage contentMenssage);
	
	public ContentMenssage deletarMensagemIndividual(Long id ,ContentMenssage contentMenssage);
	
	public ContentMenssage deletarMensagem(Long id , ContentMenssage contentMenssage);
	
	public List<TalkDto> listarConversas(Long id_user);
	
	public List<TalkDto> excluiConversa(Long id_user , Long id_contact, Boolean statusSend);
	
	
}
