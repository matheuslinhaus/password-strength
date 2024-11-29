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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/passwords")
public class PasswordResource {

	@Autowired
	private PasswordService service;

	@Operation(summary = "Avaliar força da senha",
			description = "Método para avaliar a força e segurança da senha.",
			tags = "Strength",
			responses = {
					@ApiResponse(responseCode = "200", description = "Senha analisada com sucesso",
							content = @Content(mediaType = "application/json", schema = @Schema(example = ""))),
					@ApiResponse(responseCode = "400", description = "Erro nos parâmetros fornecidos",
						content = @Content(mediaType = "application/json",schema = @Schema(example = ""))) })
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
