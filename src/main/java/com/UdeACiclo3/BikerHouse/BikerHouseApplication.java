package com.UdeACiclo3.BikerHouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;



//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

@SpringBootApplication
public class BikerHouseApplication {

	@GetMapping("/hello")
	public String hello(){
		return "Prueba Hola Mundo en la web";
	}

	public static void main(String[] args) {
		SpringApplication.run(BikerHouseApplication.class, args);
		System.out.println("Hola Mundo");
	}
}