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

		containsMinimumSize(password);

		if (password.getNumber()) {
			containsNumbers(password.getPassword());
		}

		if (password.getCaseSensitive()) {
			containsUpperCase(password.getPassword());
			containsLowerCase(password.getPassword());
		}

		if (password.getSpecialCharacter()) {
			containsSpecialCharacter(password.getPassword());

		}

		containsSimplePass(password);

		return "Sua senha é adequada!";

	}

	private void containsMinimumSize(Password password) throws StrengthException {
		if (password.getPassword().length() < password.getMinimumSize()) {
			throw new StrengthException(
					"Sua senha é muito curta. Escolha uma senha com pelo menos 8 caracteres para garantir a máxima segurança.");
		}
	}

	private void containsNumbers(String password) throws StrengthException {
		if (!password.matches(".*\\d.*")) {
			throw new StrengthException(
					"Senhas com uma combinação de letras e números são mais seguras. Adicione um número à sua senha para torná-la mais segura.");
		}
	}

	private void containsUpperCase(String password) throws StrengthException {
		if (!password.matches(".*[A-Z].*")) {
			throw new StrengthException(
					"Sua senha não contém nenhuma letra maiúscula. Inclua pelo menos uma letra maiúscula aumentar a segurança de sua senha.");
		}
	}

	private void containsLowerCase(String password) throws StrengthException {
		if (!password.matches(".*[a-z].*")) {
			throw new StrengthException(
					"Sua senha não contém nenhuma letra minúscula. Inclua pelo menos uma letra minúscula aumentar a segurança de sua senha.");
		}
	}

	private void containsSpecialCharacter(String password) throws StrengthException {
		if (!password.matches(".*[^a-zA-Z0-9].*")) {
			throw new StrengthException("Para fortalecer sua senha, adicione um caractere especial (ex: !, @, #). Isso a torna mais segura!");
		}
	}

	private void containsSimplePass(Password password) throws StrengthException {
		List<Password> list = repository.findAll();
		for (Password ps : list) {
			if (password.getPassword().contains(ps.getPassword())) {
				throw new StrengthException("Está senha é muito simples.");
			}
		}
	}
}
