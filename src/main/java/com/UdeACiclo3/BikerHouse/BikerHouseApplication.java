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
		Empresa empresa=new Empresa("SinRuedas SA","calle 123","3103103100","1010105");
		Usuario empleado= new Usuario("Andres","anf@gmail.com",empresa,"Administrador");
		String name= empleado.LeerNombreEmpleado();  //Nombre viejo
		empleado.ModificarNombreEmpleado("Diego");
		String nameNew= empleado.LeerNombreEmpleado();// Nombre nuevo Empleado
		String correo=empleado.LeerCorreoEmpleado();  // Correo viejo
		empleado.setCorreo("Dieggo@gmail.com");
		String correoNew=empleado.LeerCorreoEmpleado(); //Correo Nuevo
		String nameEmpE=empleado.LeerNombreEmpresaEmpleado();  //Nombre Empresa viejo
		empleado.ModificarNombreEmpresaEmpleado("Monociclo LTDA");
		String nameEmpENew=empleado.LeerNombreEmpresaEmpleado(); //Nombre Empresa Nuevo
		String NombreRol=empleado.LeerRolEmpleado(); //Rol viejo
		empleado.ModificarRolEmpleado("Operativo");
		String NuevoRol=empleado.LeerRolEmpleado(); // Rol nuevo

		//return "-name:"+nombre +"----->nameNew: "+nombreNew+"----->dir:"+dir+"--->dirNew: "+dirNew+"--Tel: "+tel+"--->TelNew="+telNew+"---->nit:"+nit+"--->nitNew: "+nitNew;

		return "Nombre:"+name+"--NombreNuevo:"+nameNew+"--Correo:"+correo+"--CorreoNuevo"+correoNew+
				"--NombreEmpresa:"+nameEmpE+"--NombreEmpresaNuevo:"+nameEmpENew+"--Rol:"+NombreRol+"--NuevoRol:"+
				NuevoRol;
	}


	@GetMapping("/testMovimiento")
	public String testMovimiento(){

		Empresa empresa=new Empresa("SinRuedas SA","calle 123","3103103100","1010105");
		Usuario empleado= new Usuario("Andres","anf@gmail.com",empresa,"Administrador");
		MovimientoDinero movimientoDinero=new MovimientoDinero(-250000,"repuesto", empleado);
		//Long monto= movimientoDinero.leerMonto();//Mostrar monto
		String monto= String.valueOf(movimientoDinero.leerMonto());//Mostrar monto
		movimientoDinero.ModificarMonto(280000);
		//long Newmonto= movimientoDinero.leerMonto();//Mostrar nuevo Monto positivo
		String Newmonto= String.valueOf(movimientoDinero.leerMonto());//Mostrar nuevo Monto positivo
		movimientoDinero.ModificarMonto(-23128);
		//long Newmonto2= movimientoDinero.leerMonto(); //Mostrar nuevo Monto negativo
		String Newmonto2= String.valueOf(movimientoDinero.leerMonto()); //Mostrar nuevo Monto negativo
		String concepto= movimientoDinero.leerConcepto();//Mostrar concepto
		movimientoDinero.ModificarConcepto("EU" );
		String newConcepto= movimientoDinero.leerConcepto();//Mostrar nuevo Concepto
		String nom=movimientoDinero.LeerEmpleadoMovimiento();
		return "Monto:"+monto+"--NewMonto:"+Newmonto+"--Concepto:"+concepto+"--NewConcepto:"+newConcepto;

	}
	@GetMapping("/testEmpresa")
	public String testEmpresa(){
		Empresa empresa=new Empresa("SinRuedas SA","calle 123","3103103100","1010105");
		String nombre=empresa.LeerNombreEmpresa(empresa);
		String nombreNew=empresa.ModificarNombreEmpresa(empresa, "Sin Frenos LTDA");
		String dir=empresa.LeerDireccionEmpresa(empresa);
		String dirNew=empresa.ModificarDireccionEmpresa("Calle falsa 567");
		String tel=empresa.getTelefono();
		String telNew=empresa.ModificarTelefonoEmpresa(empresa,"123456");
		String nit=empresa.LeerNITEmpresa(empresa);
		String nitNew=empresa.ModificarNITEmpresa(empresa,"000001");

		return "-name:"+nombre +"----->nameNew: "+nombreNew+"----->dir:"+dir+"--->dirNew: "+dirNew+"--Tel: "+tel+"--->TelNew="+telNew+"---->nit:"+nit+"--->nitNew: "+nitNew;
	}
	/*
	 */
	public static void main(String[] args) {
		SpringApplication.run(BikerHouseApplication.class, args);
		System.out.println("Hola Mundo");

	}

}
