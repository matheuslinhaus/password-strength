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
		
		if(password.getPassword().contains("123")) {
			return "Senha muito fraca";
		}
		
		return "";
		
	}
}
