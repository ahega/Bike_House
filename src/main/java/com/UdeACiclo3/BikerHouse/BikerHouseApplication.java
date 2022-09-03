package com.UdeACiclo3.BikerHouse;

import com.UdeACiclo3.BikerHouse.modelos.Usuario;
import com.UdeACiclo3.BikerHouse.modelos.Empresa;
import com.UdeACiclo3.BikerHouse.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BikerHouseApplication {

	@GetMapping("/hello")
	public String hello(){
		return "Prueba Hola Mundo en la web";
	}

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	EmpresaService empresaService;

	//////////////////////////////////////////////EMPRESAS
	@GetMapping("/enterprises") //Ver json de todas las empresas
	public List<Empresa> verEmpresas(){
		return empresaService.getAllEmpresas();
	}

	@PostMapping("/enterprises") //Guardar el json del body como una nueva empresa o registro en nuestra bd
	public Optional<Empresa> guardarEmpresa(@RequestBody Empresa emp){
		return this.empresaService.saveOrUpdateEmpresa(emp);
	}

	@GetMapping(path = "enterprises/{id}")
	public Empresa empresaPorID(@PathVariable("id") Integer id){
		return this.empresaService.getEmpresaById(id);
	}

	@PatchMapping("/enterprises/{id}")
	public Optional<Empresa> actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa){
		Empresa emp= empresaService.getEmpresaById(id);
		emp.setNombre(empresa.getNombre());
		emp.setDireccion(empresa.getDireccion());
		emp.setTelefono(empresa.getTelefono());
		emp.setNIT(empresa.getNIT());
		return empresaService.saveOrUpdateEmpresa(emp);

	}

	////////////////////////////////////////////Usuario
	@GetMapping("/users")
	public List<Usuario> getAllUsuarios(){
		return usuarioService.getAllUsuarios();
	}

	@PostMapping("/users") //Guardar el json del body como una nueva empresa o registro en nuestra bd
	public Optional<Usuario> guardarUsuario(@RequestBody Usuario usuario1){
		return usuarioService.saveOrUpdateUsuario(usuario1);

	}




	public static void main(String[] args) {
		SpringApplication.run(BikerHouseApplication.class, args);
		System.out.println("Hola Mundo");

	}

}
