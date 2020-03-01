package com.pitang.aula.service.imple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.swing.text.AbstractDocument.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pitang.aula.dto.ContentMenssageDto;
import com.pitang.aula.dto.TalkDto;
import com.pitang.aula.exceptions.ExceptionBadRequest;
import com.pitang.aula.model.Contact;
import com.pitang.aula.model.ContentMenssage;
import com.pitang.aula.repository.ContentMenssageRepository;
import com.pitang.aula.repository.UserModelRepository;
import com.pitang.aula.servc.ContentMenssageService;

@Service
public class ContentMenssageServiceImpl implements ContentMenssageService {

	@Autowired
	private ContentMenssageRepository contentMenssageRepository;
	
	
	@Autowired
	private ContactServiceImpl contactServiceImpl;

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

		Long user = (long) 1;
		Long contact = (long) 2;

		List<ContentMenssage> concatenaLista = contentMenssageRepository.findconversas(user, contact);
		return concatenaLista;
		// return null ;
	}

	@Override
	public List<ContentMenssage> listarMensagensAtivas(Long id_user, Long id_contact, Boolean statusSend) {
		// TODO Auto-generated method stub

		List<ContentMenssage> msg_user_send = contentMenssageRepository.findconversasEnviadasPeloSend(id_user,
				id_contact, statusSend);
		List<ContentMenssage> msg_content = contentMenssageRepository.findconversasEnviadasPeloContact(id_user,
				id_contact, statusSend);

		List<ContentMenssage> listaMensagensOk = new ArrayList(msg_user_send.size() + msg_content.size());
		;

		listaMensagensOk.addAll(msg_user_send);
		listaMensagensOk.addAll(msg_content);

		// Lista<ContentMessage> lista = new
		// ArrayList<ContentMenssage>(listaMensagensOK);
		listaMensagensOk.sort(Comparator.comparing(ContentMenssage::getDatamsg));

		return listaMensagensOk;
	}

	@Override
	public ContentMenssage enviarMenssage(ContentMenssage contentMenssage) {

		// contentMenssageRepository.save(contentMenssage);
		

		return contentMenssageRepository.save(contentMenssage);
	}

	@Override
	public ContentMenssage deletarMensagemIndividual(Long id, ContentMenssage contentMenssage) {
		// TODO Auto-generated method stub
		ContentMenssage messageOriginal = contentMenssageRepository.findById(contentMenssage.getId()).get();
		if (messageOriginal == null) {
			throw new ExceptionBadRequest("Mensagem não existe !");
		}

		if (id == contentMenssage.getIdusermsg() && messageOriginal.getIdusermsg() == contentMenssage.getIdusermsg()) {
			messageOriginal.setStatusSend(false);
		} else if (id != contentMenssage.getIdusermsg()) {
			messageOriginal.setStatusRecipient(false);
			
		}
		contentMenssageRepository.save(messageOriginal);

		return contentMenssageRepository.save(messageOriginal);
	}

	@Override
	public ContentMenssage deletarMensagem(Long id, ContentMenssage contentMenssage) {
		// TODO Auto-generated method stub
		ContentMenssage messageOriginal = contentMenssageRepository.findById(contentMenssage.getId()).get();
		if (messageOriginal == null) {
			throw new ExceptionBadRequest("Menssagem não existe !");
		}
		if (id == contentMenssage.getIdusermsg() && messageOriginal.getIdusermsg() == contentMenssage.getIdusermsg()) {
			messageOriginal.setStatusSend(false);
			messageOriginal.setStatusRecipient(false);
		} else {
			throw new ExceptionBadRequest("Você não é o dono dessa mensagem!");
		}
		contentMenssageRepository.save(messageOriginal);

		return contentMenssageRepository.save(messageOriginal);
	}

	@Override
	public List<TalkDto> listarConversas(Long id_user) {
		
		List<Contact> userContactList = contactServiceImpl.userContacts(id_user);
		List<TalkDto> talklist = new ArrayList<TalkDto>() ;
		TalkDto talk ;
		Boolean statusSend = true ;
		
		for (Contact contact : userContactList) {
			//ContentMenssage contentMenssage = contentMenssageRepository.findUltimaMensagem(id_user, contact.getIdUserContact() );
			List<ContentMenssage> listmensagensok =  listarMensagensAtivas(id_user,contact.getIdUserContact(), statusSend);
			
			if(!listmensagensok.isEmpty()) {
				ContentMenssage contentMenssage = listmensagensok.get(listmensagensok.size() - 1);
				String name = contact.getName();
				talk = new TalkDto();
				talk.setContact(contact);
				talk.setContentMenssage(contentMenssage);
				talklist.add(talk);
				
				
			}
		}
		
		//listarMensagensAtivas(Long id_user, Long id_contact, Boolean statusSend)  Comparator.comparing(ContentMenssage::getDatamsg)
		// TODO Auto-generated method stub 
		
		Collections.sort(talklist);
		return talklist;
	}

	@Override
	public List<TalkDto> excluiConversa(Long id_user, Long id_contact, Boolean statusSend) {
		// TODO Auto-generated method stub
		List<TalkDto> talklist = new ArrayList<TalkDto>() ;
		List<ContentMenssage> listmensagensok =  listarMensagensAtivas(id_user,id_contact, statusSend);
		
		for (ContentMenssage contentMenssage : listmensagensok) {
			deletarMensagemIndividual(id_user , contentMenssage);
		}
		
		talklist = listarConversas(id_user);
		return talklist;
	}

}
