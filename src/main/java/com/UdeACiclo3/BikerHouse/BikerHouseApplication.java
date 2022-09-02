package com.UdeACiclo3.BikerHouse;

import com.UdeACiclo3.BikerHouse.modelos.Usuario;
import com.UdeACiclo3.BikerHouse.modelos.Empresa;
import com.UdeACiclo3.BikerHouse.modelos.MovimientoDinero;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BikerHouseApplication {

	@GetMapping("/hello")
	public String hello(){
		return "Prueba Hola Mundo en la web";
	}


	@GetMapping("/testEmpleado")
	public String testEmpleado(){

		return "Nombre:";
	}


	@GetMapping("/testMovimiento")
	public String testMovimiento(){

		return "monto";
	}
	@GetMapping("/testEmpresa")
	public String testEmpresa(){

	return "test-empresa";
	}

	public static void main(String[] args) {
		SpringApplication.run(BikerHouseApplication.class, args);
		System.out.println("Hola Mundo");

	}

}
