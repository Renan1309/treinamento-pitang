package com.pitang.aula.service.imple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
		Optional<ContentMenssage> messageOriginal = contentMenssageRepository.findById(contentMenssage.getId());
		if (!messageOriginal.isPresent()) {
			throw new ExceptionBadRequest("Mensagem não existe !");
		}
		messageOriginal.get();
		if (id == contentMenssage.getIdusermsg().getId() && messageOriginal.get().getIdusermsg().getId() == contentMenssage.getIdusermsg().getId()) {
			messageOriginal.get().setStatusSend(false);
		} else if (id != contentMenssage.getIdusermsg().getId()) {
			messageOriginal.get().setStatusRecipient(false);
			
		}
		contentMenssageRepository.save(messageOriginal.get());

		return contentMenssageRepository.save(messageOriginal.get());
	}

	@Override
	public ContentMenssage deletarMensagem(Long id, ContentMenssage contentMenssage) {
		// TODO Auto-generated method stub
		Optional<ContentMenssage> messageOriginal = contentMenssageRepository.findById(contentMenssage.getId());
		if (!messageOriginal.isPresent()) {
			throw new ExceptionBadRequest("Menssagem não existe !");
		}
		
		if (id == contentMenssage.getIdusermsg().getId() && messageOriginal.get().getIdusermsg().getId() == contentMenssage.getIdusermsg().getId()) {
			messageOriginal.get().setStatusSend(false);
			messageOriginal.get().setStatusRecipient(false);
		} else {
			throw new ExceptionBadRequest("Você não é o dono dessa mensagem!");
		}
		contentMenssageRepository.save(messageOriginal.get());

		return contentMenssageRepository.save(messageOriginal.get());
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
		
		//teste para mostra json das mensagens que não possuo contato.
		//List<TalkDto> contatoestranho = listarMensagensAtivasGeral(id_user);
		//talklist.addAll(contatoestranho);
		//teste para mostra json das mensagens que não possuo contato.
	//ordenar talklist
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
	
	private void listarConversasAll(Long id_user) {
		
	}
	

	public List<TalkDto> listarMensagensAtivasGeral(Long id_user) {
		
		
		List<ContentMenssage> msg_n_cadastrados = contentMenssageRepository.findconversasEnviadasPorContatosNãoCadastrados(id_user);
		Collection<Long> listadeids  = new HashSet<Long>();//hashset para remover os repetidos
	
		for (ContentMenssage contentMenssage : msg_n_cadastrados) {
			Long number = contentMenssage.getIdusermsg().getId();
			//listadeids.add(number);
			listadeids.add(number);
		}
		
		
		Collection<Long> listadeidsContatos  = new HashSet<Long>();
		List<Contact> userContactList = contactServiceImpl.userContacts(id_user);
		for (Contact contact : userContactList) {
			listadeidsContatos.add(contact.getIdUserContact());
		}
		listadeids.removeAll(listadeidsContatos);
		
		List<TalkDto> talklist = new ArrayList<TalkDto>() ;
		TalkDto talk ;
		Contact contact ;
		boolean statusmsg = true;
        for (Long long1 : listadeids) {
        	talk = new TalkDto();
        	contact = new Contact();
			System.out.println("id que n possuo como contato :" + long1);
			List<ContentMenssage> listaMensagensOk = new ArrayList<>();
			List<ContentMenssage> listmensagensok =  contentMenssageRepository.findconversasEnviadasPeloContact(id_user ,long1,statusmsg);
			listaMensagensOk.sort(Comparator.comparing(ContentMenssage::getDatamsg));
			ContentMenssage contentMenssage = listmensagensok.get(listmensagensok.size() - 1);
			contact.setIdUserContact(long1);
			contact.setName("Contato_"+long1);
			
			talk.setContentMenssage(contentMenssage);
			talk.setContact(contact);
			talklist.add(talk);
			
		}
	

        for (TalkDto talkDto : talklist) {
			System.out.println(talkDto);
		}
	

		return null;
	}

}
