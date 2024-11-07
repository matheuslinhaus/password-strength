package com.passwordquality.strength.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passwordquality.strength.entities.Password;
import com.passwordquality.strength.repositories.PasswordRepository;

@Service
public class PasswordService {
	
	@Autowired
	private PasswordRepository repository;
	
	public List<Password> findAll() {		
		return repository.findAll();	
	}
	
	public String validateStrenght(Password password) {
		/*
		 * TODO - Não usar por mensagem porque a seguinte retorna null e apaga mensagem anterior
		 */
		String msg = validateUpperCase(password.getPassword());
		msg = validateLowerCase(password.getPassword());
		
		if (msg != null) {
			return msg;
		}
		
		
		List<Password> list = repository.findAll();
		
		for(Password ps : list) {
			if(password.getPassword().contains(ps.getPassword())) {
				return "Senha muito fraca";
			}
		}	
		return "";
		
	}

	private String validateUpperCase(String password) {	
		if(!password.matches(".*[A-Z].*")) {
			return "Senha não possui letras minusculas";
		}
		return null;
	}
	
	private String validateLowerCase(String password) {	
		if(!password.matches(".*[a-z].*")) {
			return "Senha não possui letras minusculas";
		}
		return null;
	}
}
