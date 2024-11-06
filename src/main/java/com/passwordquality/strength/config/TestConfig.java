package com.passwordquality.strength.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.passwordquality.strength.entities.Password;
import com.passwordquality.strength.repositories.PasswordRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private PasswordRepository passwordRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Password ps = new Password(null, "123456");	
		passwordRepository.saveAll(Arrays.asList(ps));
	}

}
