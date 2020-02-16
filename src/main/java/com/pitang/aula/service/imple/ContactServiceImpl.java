package com.pitang.aula.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	
	

	@Override
	public List<Contact> listcontact() {
		// TODO Auto-generated method stub
		return contactRepository.findAll();
	}




	@Override
	public Contact creatContact(Contact contact) {
		
		return contactRepository.save(contact);
	}




	@Override
	public UserModel findUserByContact(Long id) {
		Optional<UserModel> user = userRepository.findById(id);
		return user.get();
	}




	@Override
	public List<Contact> userContacts(Long id) {
		return  contactRepository.findByUserModelId(id);
		// TODO Auto-generated method stub
		//return (List<Contact>) contactRepository.findByuserModel_id(id);
	}




	@Override
	public String deleteContact(Long id) {

		contactRepository.deleteById(id);
		
		return "Contato deletado com sucesso !";
	}




	
}
