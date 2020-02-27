package com.pitang.aula.service.imple;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pitang.aula.dto.UsuarioForm;
import com.pitang.aula.exceptions.ExceptionBadRequest;
import com.pitang.aula.model.UserModel;
import com.pitang.aula.repository.UserModelRepository;
import com.pitang.aula.servc.UserService;

@Service
public class UserServiceImpl implements UserService {

	// adicionei o service do token
	@Autowired
	private TokenServiceJwtImpl tokenServiceJwtImpl;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserModelRepository userRepository;

	@Override
	public List<UserModel> listUser() {

		if (userRepository.findAll().size() == 0) {
			return null;
		}
		return userRepository.findAll();
	}

	@Override
	public UserModel findByUserByUserName(String usu_email) {
		if (usu_email == null || usu_email.isEmpty()) {
			System.out.println("email veio null");
			throw new ExceptionBadRequest("Email enviado nulo !");
		}
		UserModel user = userRepository.findByEmail(usu_email);
		if (user == null) {
			throw new ExceptionBadRequest("Não possui esse email cadastrado !");
		}

		return user;
	}

	@Override
	public UserModel findByUserById(Long id_usu) {
		Optional<UserModel> user = userRepository.findById(id_usu);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new ExceptionBadRequest("Id de usuário inválido !");
		}

	}

	// Metodo criado para realizar o cadastro
	@Override
	public UserModel creatUser(MultipartFile file, UserModel user) {

		String senha_encrypt = null;
		checkUser(user);
		validateUserCreat(user);
		try {
			senha_encrypt = passwordhash(user.getPassword());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		user.setPassword(senha_encrypt);
		user = userRepository.save(user);
		
		String caminhoImage = guardarArquivo( file,  user); //metodo para guarda o arquivo
		
		user.setPathImage(caminhoImage);
		
		return userRepository.save(user);
	}

	// Metodo criado para realizar a alteração do cadastro
	@Override
	public UserModel updateUser(Long id, UserModel userChange) {
		Optional<UserModel> user = userRepository.findById(id);
		if (user == null) {
			throw new ExceptionBadRequest("Id inválido !");
		}
		UserModel useredit = user.get();
		useredit.setName(userChange.getName());

		userRepository.save(useredit);

		return useredit;

	}

	@Override
	public void deleteUser(Long id) {
		Optional<UserModel> user = userRepository.findById(id);
		if (user == null) {
			throw new ExceptionBadRequest("Id inválido não foi possível excluir usuário !");
		}
		userRepository.deleteById(id);

	}

	// check de campos se algum veio vazio
	private void checkUser(UserModel user) {
		if (user == null) {

			throw new ExceptionBadRequest("Usuário enviado nulo !");
		}
		if (StringUtils.isEmpty(user.getEmail())) {

			throw new ExceptionBadRequest("O email não pode ser vazio!");
		}
		if (StringUtils.isEmpty(user.getName())) {
			throw new ExceptionBadRequest("O nome não pode ser vazio!");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			throw new ExceptionBadRequest("A senha não pode ser enviada vazio");
		}
	}

	private void validateUserCreat(UserModel user) {

		if (!StringUtils.isEmpty(user.getEmail()) && userRepository.findByEmail(user.getEmail()) != null) {
			System.out.println("Email ja cadastrado");
		}

	}

	// método para criptografar a senha antes de salvar o usuário
	private String passwordhash(String senhaOriginal) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		if (senhaOriginal.isEmpty()) {

			throw new ExceptionBadRequest("Senha vazia  !");

		}
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(senhaOriginal.getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		String senha = hexString.toString();

		System.out.print(senha);
		return senha;
	}

	// METODO CRIADO PARA AUTENTICACAO FALTANDO IMPLEMENTAR OS VERIFICADORES
	@Override
	public String authentication(UsuarioForm usuarioForm) {
		// colocar validacao do objeto
		UserModel user = userRepository.findByEmail(usuarioForm.getEmail());
		if (user == null) {

			throw new ExceptionBadRequest("Email inválido !");
		}

		// vaerificar se o objeto retornado foi null caso for nem chama a funcao do jwt
		String tokenreturn = tokenServiceJwtImpl.generateToken(user);

		System.out.println("Esse e o token ===> " + tokenreturn);
		return tokenreturn;
	}

	@Override
	public String guardarArquivo(MultipartFile file, UserModel user) {
		
		String  UPLOADED_FOLDER = request.getServletContext().getRealPath("/Images/");
		System.out.println(UPLOADED_FOLDER);
		
		try {
			byte[] bytes;
			bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER );
			File dir = new File(UPLOADED_FOLDER );
			
			if (!Files.exists(path)) {
					System.out.println("Arquivo ========> criou 1 : "+dir);
				  
					dir.mkdir();
					path = Paths.get(UPLOADED_FOLDER +user.getId()+"_.png");
					Files.write(path, bytes);
					
					//Files.write(pathz, bytes);
					System.out.println("Arquivo ========> path 1: "+path);
					
					//recuperar
					//byte[] bytesox;
					//bytesox = file.getBytes();
					//System.out.println(bytesox);
					//String caminhoarq = path.toString() ;
					//File filex =  new File(caminhoarq);
					//bytesox = Files.readAllBytes(filex.toPath());
					System.out.println("caminho criado e imagem 1 = "+path);
					
					
			 }
			else {
				System.out.println("diretorio criado");
				//Files.write(path, bytes);
				path = Paths.get(UPLOADED_FOLDER +user.getId()+"_.png");
				
				
				Files.write(path, bytes);
				System.out.println("Arquivo ========> path 2: "+path);
				
				//recuperar
				//byte[] bytesox;
				//String caminhoarq = path.toString() ;
				//File filex =  new File(caminhoarq);
				//bytesox = Files.readAllBytes(filex.toPath());
				//String textoDoArquivo = new String(bytesox, "UTF-8");
				//System.out.println("ÄRQUIVO ===> "+textoDoArquivo );
				System.out.println("caminho criado e imagem 2 = "+path);
				String page1 = path.toString().substring(73);
				System.out.println("path db : = "+ page1 );
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String caminhoimagemdb = "/Images/"+user.getId()+"_.png";
		return caminhoimagemdb ;
		
	}

}



/*
 * // /^(\w+)@(\w+(\.\w{2,3})+)$/ String expression =
 * "^[\\\\w\\\\.-]+@([\\\\w\\\\-]+\\\\.)+[A-Z]{2,4}$"; Pattern pattern =
 * Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
 * //System.out.println("EMAIL VERDADEIRO"); Matcher matcher =
 * pattern.matcher(user.getEmail()); if (matcher.matches()) {
 * System.out.println("EMAIL VERDADEIRO"); } else
 */
