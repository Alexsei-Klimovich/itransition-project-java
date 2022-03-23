package com.alexsei.itransition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

//@EnableOAuth2Client
@SpringBootApplication
public class ItransitionApplication  {
	public static void main(String[] args) {
		SpringApplication.run(ItransitionApplication.class, args);
	}
}
