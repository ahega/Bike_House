package com.UdeACiclo3.BikerHouse.controller;

import com.UdeACiclo3.BikerHouse.modelos.Empresa;
import com.UdeACiclo3.BikerHouse.modelos.Usuario;
import com.UdeACiclo3.BikerHouse.service.EmpresaService;
import com.UdeACiclo3.BikerHouse.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerUsuario {

    @Autowired
    EmpresaService empresaService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/VerUsuarios")
    public String viewEmpleados(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Usuario> listaEmpleados=usuarioService.getAllUsuarios();
        model.addAttribute("emplelist",listaEmpleados);
        model.addAttribute("mensaje",mensaje);
        return "verUsuarios"; //Llamamos al HTML
    }

    @GetMapping("/AgregarUsuario")
    public String agregarUsuario(Model model ){
        Usuario usuarioNew=new Usuario();
        model.addAttribute("usuarioNew",usuarioNew);
        List<Empresa> listaEmpresas= empresaService.getAllEmpresas(); //envia lista de empresas al html agregarUsuario
        model.addAttribute("emprelist",listaEmpresas);
        return "agregarUsuario";

    }

    @PostMapping("/GuardarUsuario")
    public String guardarUsuario(Usuario usuario, RedirectAttributes redirectAttributes){
        String passEncriptada=passwordEncoder().encode(usuario.getPassword());
        usuario.setPassword(passEncriptada);
        if(usuarioService.saveOrUpdateUsuario(usuario)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerUsuarios";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarUsuario";
    }

    @GetMapping("/EditarUsuario/{id}")
    public String editarUsuario(Model model, @PathVariable Integer id){
        Usuario userNew=usuarioService.getUsuarioById(id);
        model.addAttribute("user",userNew);
        List<Empresa> listaEmpresas= empresaService.getAllEmpresas(); //envia lista de empresas al html agregarUsuario
        model.addAttribute("emprelist",listaEmpresas);
        return "editarUsuario";

    }

    @PostMapping("/ActualizarUsuario")
    public String updateUsuario(@ModelAttribute("empl") Usuario usuario, RedirectAttributes redirectAttributes){
        Integer id=usuario.getId(); //Obtiene el id del objeto usuario
        String Oldpassword=usuarioService.getUsuarioById(id).getPassword();
        if(!usuario.getPassword().equals(Oldpassword)){
            String passEncriptada=passwordEncoder().encode(usuario.getPassword());
            usuario.setPassword(passEncriptada);
        }

        if(usuarioService.saveOrUpdateUsuario(usuario)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerUsuarios";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarUsuario/"+usuario.getId();

    }

    @GetMapping("/Empresa/{id}/Usuarios") //Filtrar los Usuarios por empresa
    public String verEmpleadosPorEmpresa(@PathVariable("id") Integer id, Model model){
        List<Usuario> listaUsuarios = usuarioService.obtenerPorEmpresa(id);
        model.addAttribute("emplelist",listaUsuarios);
        return "verUsuarios"; //Llamamos al html con el emplelist de los empleados filtrados
    }

    @GetMapping("/EliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if(usuarioService.deleteUsuario(id)){
            return "redirect:/VerUsuarios";
        }
    return "redirect:/VerUsuarios";

    }

    // Controlador que lleva al template no autorizado
    @RequestMapping(value = "/Denegado")
    public String accesoDenegado(){

        return "accessDenied";
    }


/// Metodo para encriptar la contraseña

   @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }




}
