package me.gogosing.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!prod")
@Configuration
@OpenAPIDefinition
public class SpringdocConfig {

	@Bean
	public OpenAPI openAPI() {
		final var info = new Info()
			.title("spring-boot-test-study API")
			.description("Spring boot test study Sample Application")
			.version("v0.0.1")
			.contact(new Contact()
				.name("JinBum Jeong")
				.email("gogosing@mz.co.kr")
				.url("https://github.com/MZ-GOGOSING"));

		return new OpenAPI().info(info);
	}
}
