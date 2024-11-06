package com.passwordquality.strength.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passwordquality.strength.entities.Password;

public interface PasswordRepository extends JpaRepository<Password, Long> {

}
