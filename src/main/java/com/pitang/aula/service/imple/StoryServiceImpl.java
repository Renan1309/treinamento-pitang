package com.pitang.aula.service.imple;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pitang.aula.dto.StoryDto;
import com.pitang.aula.dto.TalkDto;
import com.pitang.aula.dto.UserDto;
import com.pitang.aula.exceptions.ExceptionBadRequest;
import com.pitang.aula.mapper.ModelMapperComponent;
import com.pitang.aula.model.Contact;
import com.pitang.aula.model.Story;
import com.pitang.aula.model.UserModel;
import com.pitang.aula.repository.StoryRepository;
import com.pitang.aula.servc.StoryService;


@Service
public class StoryServiceImpl implements StoryService {
	
	@Autowired
	private StoryRepository  storyRepository ;
	
	@Autowired
	private ContactServiceImpl ContactServiceImpl;
	
	
	@Override
	public List<Story> listAllStory() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Story creatStory(MultipartFile file, Story story) {
		// TODO Auto-generated method stub
		
		
		
		 story = storyRepository.save(story);
		 String caminhoStory = guardarStory(file, story); // metodo para guarda o arquivo
		 
		story.setPathStory(caminhoStory);
		 
		 
		 return storyRepository.save(story);
	}


	@Override
	public List<StoryDto> listStoryMyContacts(UserModel userModel) {
		// TODO Auto-generated method stub
		
		gerardata();
		List<Contact> contactList = ContactServiceImpl.userContacts(userModel.getId());
		List<Story> storyMyContacts = new ArrayList<Story>()  ;
		byte[] bytesStory;
		for (Contact contact : contactList) {
			
			List<Story> storyMyContactsDB = storyRepository.findByStoryOwnerId(contact.getIdUserContact());
			
			storyMyContacts.addAll(storyMyContactsDB);
			
		}
		List<StoryDto> storyMyContactsDto = new ArrayList<StoryDto>()  ;
		for (Story story : storyMyContacts) {
			StoryDto storyDto = ModelMapperComponent.modelMapper.map(story, new TypeToken<StoryDto>() {
			}.getType());
			
			bytesStory = bytesDoStory(story.getPathStory());
			storyDto.setImagebyte(bytesStory);
			storyMyContactsDto.add(storyDto);
		}
		
		return storyMyContactsDto;
	}
	
	@Override
	public String guardarStory(MultipartFile file, Story story) {
		
		
		Path currentRelativePath = Paths.get("");
		String url = currentRelativePath.toAbsolutePath().toString();
		System.out.println("URL ======> " + url);
		String diretorioStory = "/Story/";
		String UPLOADED_FOLDER = url + diretorioStory; // request.getServletContext().getRealPath("/Images/");
		System.out.println(UPLOADED_FOLDER);
		Random random = new Random();
		long id_story = random.nextLong();

		try {
			byte[] bytes;
			bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER);
			File dir = new File(UPLOADED_FOLDER);

			if (!Files.exists(path)) {
				System.out.println("Arquivo ========> criou 1 : " + dir);

				dir.mkdir();
				path = Paths.get(UPLOADED_FOLDER + story.getId()+"_"+id_story+ "_.png");
				Files.write(path, bytes);
				System.out.println("Arquivo ========> path 1: " + path);
				System.out.println("caminho criado e imagem 1 = " + path);

			} else {
				System.out.println("diretorio criado");
				path = Paths.get(UPLOADED_FOLDER + story.getId()+"_"+id_story+ "_.png");
				Files.write(path, bytes);
				System.out.println("Arquivo ========> path 2: " + path);
			
				String page1 = path.toString().substring(73);
				System.out.println("path db : = " + page1);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String caminhostorydb = diretorioStory + story.getId()+"_"+id_story+ "_.png";
		return caminhostorydb;

	}
	
	@Override
	public byte[]  bytesDoStory( String diretorioStory) {
		Path currentRelativePath = Paths.get("");
		String UPLOADED_FOLDER = currentRelativePath.toAbsolutePath().toString() + diretorioStory;
		Path path = Paths.get(UPLOADED_FOLDER);
		byte[] bytesImageStory = null;
		if (diretorioStory != null) {
			try {
				bytesImageStory = Files.readAllBytes(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bytesImageStory;

		
	}


	@Override
	public String DeleteStory(Long idUser , Long idStory ) {
		// TODO Auto-generated method stub
		Optional<Story> storyDB = storyRepository.findById(idStory);
		if (!storyDB.isPresent()) {
			throw new ExceptionBadRequest("id do story invalido");
		}
		Story story = storyDB.get();
		
		if(story.getStoryOwner().getId() == idUser) {
			storyRepository.deleteById(storyDB.get().getId());
			return "Story deletado com sucesso" ;
		}else {
			return "Impossível deletar story";
		}
		
		
	}
	private void gerardata() {
		String formatodDate = null;
	     Date date = new Date();
	     System.out.println("1p = " + date);
	     DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	     System.out.println("2p = " +formato);
	     formatodDate = formato.format(date);
	     System.out.println("3p = " +formatodDate+" 00:00:00");
	     System.out.println("3p = " +formatodDate+" 23:59:59");
	
	}

//08/03/2020 05:09:17
//08/03/2020 23:59:59

}
