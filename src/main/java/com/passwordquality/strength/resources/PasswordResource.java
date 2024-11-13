package com.passwordquality.strength.resources;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.passwordquality.strength.entities.Password;
import com.passwordquality.strength.exceptions.StrengthException;
import com.passwordquality.strength.services.PasswordService;

@RestController
@RequestMapping(value = "/passwords")
public class PasswordResource {
	
		@Autowired
		private PasswordService service;
				
		@PostMapping(value = "/strength")
		public ResponseEntity<Map<String, String>> update(@RequestBody Password password) {
		    String validationResult;
		    try {
		        validationResult = service.validateStrenght(password);
		    } catch (StrengthException e) {
		        validationResult = e.getMessage();
		        return ResponseEntity.badRequest().body(Map.of("validationResult", validationResult));
		    }
		    return ResponseEntity.ok().body(Map.of("validationResult", validationResult));
		}
}
