package com.pitang.aula.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pitang.aula.exceptions.ExceptionBadRequest;
import com.pitang.aula.model.Contact;
import com.pitang.aula.model.UserModel;
import com.pitang.aula.repository.ContactModelRepository;
import com.pitang.aula.repository.UserModelRepository;
import com.pitang.aula.servc.ContactService;


@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactModelRepository contactRepository ;
	
	@Autowired
	private UserModelRepository userRepository;
	
	@Autowired
	private ContentMenssageServiceImpl contentMenssageServiceImpl ;
	

	
	

	@Override
	public List<Contact> listcontact() {
		// TODO Auto-generated method stub
		return contactRepository.findAll();
	}




	@Override
	public Contact creatContact(Contact contact) {
		checkContact(contact);
		validarContato (contact);
		return contactRepository.save(contact);
	}




	@Override
	public UserModel findUserByContact(Long id) {
		Optional<UserModel> user = userRepository.findById(id);
		return user.get();
	}



    //Metodo para listar os contatos do usuario
	@Override
	public List<Contact> userContacts(Long id) {
		UserModel userValido = userRepository.findById(id).get(); //verificando se o id informado contem um usuario valido
		if( userValido == null) {
			throw new ExceptionBadRequest("Usuario não encontrado");
		}
		
		return  contactRepository.findByUserModelId(id);
		// TODO Auto-generated method stub
		//return (List<Contact>) contactRepository.findByuserModel_id(id);
	}




	@Override
	public String deleteContact(Long id , Long idusercontact) {
		if(id == null || id == 0 || idusercontact == null || idusercontact == 0) {
			throw new ExceptionBadRequest("Id do contato inválido !");
		}
		
		Contact contactDB = contactRepository.findByUserModelIdAndIdUserContact( id, idusercontact );
		if(contactDB == null) {
			throw new ExceptionBadRequest("Contato não encontrado !");
		}
		
		boolean statusMsg = true ;
		contentMenssageServiceImpl.excluiConversa(id, idusercontact,statusMsg);  ///Apos deletar o contato eu chamo a funcao para excluir as mensagens e as conversas individuais
		
		contactRepository.deleteById(contactDB.getId());
		
		return "Contato deletado com sucesso !";
	}

	private void checkContact(Contact contact) {
		if (contact == null) {

			throw new ExceptionBadRequest("Contato enviado nulo !");
		}
		if (StringUtils.isEmpty(contact.getIdUserContact())) {

			throw new ExceptionBadRequest("O id do contato esta vazio !");
		}
		if (StringUtils.isEmpty(contact.getName())) {
			throw new ExceptionBadRequest("O nome do contato não pode ser vazio!");
		}
		if (contact.getUserModel() == null) {
			throw new ExceptionBadRequest("Usuario não encontrado");
		}
	}

	
	private void validarContato (Contact contact) {
		Optional<UserModel> usercontatcDB = userRepository.findById(contact.getIdUserContact());
		Contact contactDB = contactRepository.findByUserModelIdAndIdUserContact(contact.getUserModel().getId(), contact.getIdUserContact());
		
		if(!usercontatcDB.isPresent()) {
			throw new ExceptionBadRequest("Id de adicao do contato invalido ou Id de contato é inexistente");
		}
		if(contactDB != null) {
			throw new ExceptionBadRequest("Contato ja exite !");
		}
		
	}
	
	
	
}
