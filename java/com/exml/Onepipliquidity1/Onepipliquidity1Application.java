package com.exml.Onepipliquidity1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication

public class Onepipliquidity1Application {

	public static void main(String[] args) {
		SpringApplication.run(Onepipliquidity1Application.class, args);
	}
	
	
//	@Configuration
//	public class WebConfig {
//
//	    @Bean
//	    public WebMvcConfigurer corsConfigurer() {
//	        return new WebMvcConfigurer() {
//	            @Override
//	            public void addCorsMappings(CorsRegistry registry) {
//	                registry.addMapping("/**") // Apply to all endpoints
//	                        .allowedOrigins("http://localhost:8080") // Replace with your client's origin
//	                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//	                        .allowedHeaders("*")
//	                        .allowCredentials(false);
//	            }
//	        };
//	    }
//	}
	

}
