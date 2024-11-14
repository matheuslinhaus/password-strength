package com.passwordquality.strength.entities;

import java.util.Objects;

import com.passwordquality.strength.exceptions.StrengthException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "weak_password")
public class Password {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String password;
	private Boolean caseSensitive = true;
	private Boolean specialCharacter = true;
	private Boolean number = true;
	private Integer minimumSize = 8;

	public Password() {
	}

	public Password(Long id, String password) {
		this.id = id;
		this.password = password;
	}

	public Password(Long id, String password, Boolean caseSensitive, Boolean specialCharacter, Boolean number,
			Integer minimumSize, Integer maximumSize) {
		this.id = id;
		this.password = password;
		this.caseSensitive = caseSensitive;
		this.specialCharacter = specialCharacter;
		this.number = number;
		this.minimumSize = minimumSize;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(Boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public Boolean getSpecialCharacter() {
		return specialCharacter;
	}

	public void setSpecialCharacter(Boolean specialCharacter) {
		this.specialCharacter = specialCharacter;
	}

	public Boolean getNumber() {
		return number;
	}

	public void setNumber(Boolean number) {
		this.number = number;
	}

	public Integer getMinimumSize() {
		return minimumSize;
	}

	public void setMinimumSize(Integer minimumSize) {
		this.minimumSize = minimumSize;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Password other = (Password) obj;
		return Objects.equals(password, other.password);
	}

	public void validateStrengthPassword() throws StrengthException {
		containsMinimumSize();
		containsNumbers();
		containsCaseSensitive();
		containsSpecialCharacter();
	}

	private void containsMinimumSize() throws StrengthException {
		if (password.length() < minimumSize) {
			throw new StrengthException(
					"Sua senha é muito curta. Escolha uma senha com pelo menos 8 caracteres para garantir a máxima segurança.");
		}
	}

	private void containsNumbers() throws StrengthException {
		if (number) {
			if (!password.matches(".*\\d.*")) {
				throw new StrengthException(
						"Senhas com uma combinação de letras e números são mais seguras. Adicione um número à sua senha para torná-la mais segura.");
			}
		}
	}

	private void containsCaseSensitive() throws StrengthException {
		if (caseSensitive) {
			if (!password.matches(".*[A-Z].*")) {
				throw new StrengthException(
						"Sua senha não contém nenhuma letra maiúscula. Inclua pelo menos uma letra maiúscula aumentar a segurança de sua senha.");
			}
			if (!password.matches(".*[a-z].*")) {
				throw new StrengthException(
						"Sua senha não contém nenhuma letra minúscula. Inclua pelo menos uma letra minúscula aumentar a segurança de sua senha.");
			}
		}
	}

	private void containsSpecialCharacter() throws StrengthException {
		if (specialCharacter) {
			if (!password.matches(".*[^a-zA-Z0-9].*")) {
				throw new StrengthException(
						"Para fortalecer sua senha, adicione um caractere especial (ex: !, @, #). Isso a torna mais segura!");
			}
		}
	}

}
