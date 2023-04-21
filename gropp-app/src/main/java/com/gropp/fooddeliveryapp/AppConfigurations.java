package com.gropp.fooddeliveryapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class AppConfigurations {

	@Bean
	public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	        .info(new Info()
	            .title("Gropp App")
	            .description("Food Delivery App")
	            .version("1.0.0")
	            .termsOfService("http://swagger.io/terms/")
	            .license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
