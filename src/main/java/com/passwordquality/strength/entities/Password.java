package com.passwordquality.strength.entities;

import java.util.Objects;

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

	public Password(Long id, String password, Boolean caseSensitive, 
					Boolean specialCharacter, Boolean number,
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

}
