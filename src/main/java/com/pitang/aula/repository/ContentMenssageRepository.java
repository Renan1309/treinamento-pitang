package com.pitang.aula.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pitang.aula.model.ContentMenssage;

public interface ContentMenssageRepository extends JpaRepository<ContentMenssage, Long> {
	
	List<ContentMenssage> findByIdusermsg(Long id) ;
	
    List<ContentMenssage> findByIdusercontactAndIdusermsg(Long idusercontact , Long idusermsg );
    
    List<ContentMenssage> findByIdusermsgAndIdusercontact(Long idusermsg ,Long idusercontact);
    
    @Query(value = "select * from content_menssage where( id_user_msg = ?1 and id_user_contact = ?2 or  id_user_msg = ?2 and id_user_contact = ?1);", nativeQuery = true)
    List<ContentMenssage> findconversas(Long idusercontact , Long idusermsg );
    
    @Query(value = "select * from content_menssage where id_user_msg = ?1 and id_user_contact = ?2  and status_send = ?3 ", nativeQuery = true)
    List<ContentMenssage> findconversasEnviadasPeloSend(Long idusersend ,Long idusercontact, Boolean statusSender );
    
    @Query(value = "select * from content_menssage where id_user_msg = ?2 and id_user_contact = ?1  and status_recipient = ?3 ", nativeQuery = true)
    List<ContentMenssage> findconversasEnviadasPeloContact(Long idusersend ,Long idusercontact, Boolean statusRecipient );
    
    @Query(value = "SELECT  * FROM content_menssage WHERE ( id_user_msg = ?1 AND id_user_contact = ?2 OR  id_user_msg = ?2 AND id_user_contact = ?1) AND  datamsg  in (SELECT MAX(datamsg) UltimaData FROM content_menssage) AND (id_user_msg = ?1 and id_user_contact = ?2  and status_send = 'true'  OR id_user_msg = ?2 and id_user_contact = ?1  and status_recipient= 'true')limit 1; ", nativeQuery = true)
    ContentMenssage findUltimaMensagem(Long idusersend ,Long idusercontact);
    

    

	
	//List<ContentMenssage> findByIdusermsgAndIdfonecontact(String foneid);
	
}
