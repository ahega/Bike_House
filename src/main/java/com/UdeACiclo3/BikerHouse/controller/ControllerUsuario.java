package com.UdeACiclo3.BikerHouse.controller;

import com.UdeACiclo3.BikerHouse.modelos.Empresa;
import com.UdeACiclo3.BikerHouse.modelos.Usuario;
import com.UdeACiclo3.BikerHouse.service.EmpresaService;
import com.UdeACiclo3.BikerHouse.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        if(usuarioService.saveOrUpdateUsuario(usuario)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerUsuarios";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarUsuario";
    }
}
