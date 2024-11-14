package com.passwordquality.strength.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passwordquality.strength.entities.Password;
import com.passwordquality.strength.exceptions.StrengthException;
import com.passwordquality.strength.repositories.PasswordRepository;

@Service
public class PasswordService {

	@Autowired
	private PasswordRepository repository;

	public String validateStrenght(Password password) throws StrengthException {
		password.validateStrengthPassword();
		containsSimplePass(password);
		return "Sua senha é adequada!";
	}

	private void containsSimplePass(Password password) throws StrengthException {
		List<Password> list = repository.findAll();
		for (Password ps : list) {
			if (password.getPassword().equalsIgnoreCase(ps.getPassword())) {
				throw new StrengthException("Está senha é muito simples.");
			}
		}
	}
}
