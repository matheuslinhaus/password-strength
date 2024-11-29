package com.passwordquality.strength.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Password API")
						.version("1.0.0")
						.description("Api de avaliação de força de senha."));
	}

}
