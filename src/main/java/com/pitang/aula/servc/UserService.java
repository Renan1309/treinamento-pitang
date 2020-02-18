package com.pitang.aula.servc;

import java.util.List;
import java.util.Optional;

import com.pitang.aula.dto.UsuarioForm;
import com.pitang.aula.model.UserModel;

public interface UserService {
	
	public List<UserModel> listUser() ;
	
	public UserModel findByUserByUserName(String usu_name);
	
	public UserModel findByUserById(Long id_usu);
	
	public UserModel creatUser(UserModel user);
	
	public UserModel updateUser(Long id, UserModel userChange);

	void deleteUser(Long id);
	
	public UsuarioForm authentication(UsuarioForm usuarioForm);
}
