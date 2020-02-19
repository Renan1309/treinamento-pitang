package com.pitang.aula.service.imple;

import java.util.ArrayList;
import java.util.Collection;
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

	@Override
	public List<ContentMenssage> listarConversas(Long id_user, Long id_contact) {
		// TODO Auto-generated method stub
		
		Long user = (long) 1 ;
		Long contact = (long) 2;
		//List<ContentMenssage>  msg_user_content =   contentMenssageRepository.findByIdusercontactAndIdusermsg(user, contact);
		// List<ContentMenssage> msg_user =  contentMenssageRepository.findByIdusermsgAndIdusercontact(user,contact) ;
		
		// List<ContentMenssage>  concatenaLista = new ArrayList(msg_user.size()+ msg_user_content.size());
		//List<ContentMenssage>  concatenaLista = contentMenssageRepository.findByIdusercontactAndIdusermsgOrIdusermsgAndIdusercontact(user, contact) ;
		//concatenaLista.addAll(msg_user_content);
		//concatenaLista.addAll(msg_user);
		
		List<ContentMenssage>  concatenaLista  = contentMenssageRepository.findconversas(user,contact);
		return concatenaLista;
		//return null ;
	}

	@Override
	public List<ContentMenssage> listarMensagensAtivas(Long id_user, Long id_contact, Boolean statusSend) {
		// TODO Auto-generated method stub
		
		List<ContentMenssage>  msg_user_send =   contentMenssageRepository.findconversasEnviadasPeloSend(id_user, id_contact, statusSend);
		List<ContentMenssage>  msg_content =   contentMenssageRepository.findconversasEnviadasPeloContact(id_user, id_contact, statusSend);
		
		//List<ContentMenssage>  listaMensagensOk = new ArrayList(msg_user_send.size());
		//List<ContentMenssage>  listaMensagensOk = new ArrayList( msg_content.size()); ;
		List<ContentMenssage>  listaMensagensOk = new ArrayList(msg_user_send.size()+ msg_content.size()); ;

		
		listaMensagensOk.addAll(msg_user_send);
		listaMensagensOk.addAll(msg_content);
		
		
		return listaMensagensOk;
	}

}
