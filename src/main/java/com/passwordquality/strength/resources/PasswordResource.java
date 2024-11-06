package com.passwordquality.strength.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.passwordquality.strength.entities.Password;
import com.passwordquality.strength.services.PasswordService;

@RestController
@RequestMapping(value = "/passwords")
public class PasswordResource {
	
		@Autowired
		private PasswordService service;
		
		@GetMapping
		public ResponseEntity<List<Password>> findAll() {
			List<Password> list = service.findAll();
			return ResponseEntity.ok().body(list);
		}
		
//		@GetMapping(value = "/{id}")
//		public ResponseEntity<Password> findById(@PathVariable Long id) {
//			Password obj = service.findById(id);
//			return ResponseEntity.ok().body(obj);
//		}
		
		@PutMapping("/")
		public ResponseEntity<Map<String, String>> update(@RequestBody Password password) {
		    String validationResult = service.validateStrenght(password);


		    Map<String, String> response = new HashMap<>();
		    response.put("validationResult", validationResult);

		    return ResponseEntity.ok(response);
		}
}
