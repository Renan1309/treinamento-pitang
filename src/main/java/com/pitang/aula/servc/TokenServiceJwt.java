package com.pitang.aula.servc;

import com.pitang.aula.model.UserModel;

public interface TokenServiceJwt {

	public String generateToken(UserModel user);

	public boolean validaToken(String token);

	public Long getIdUser(String token);
}
