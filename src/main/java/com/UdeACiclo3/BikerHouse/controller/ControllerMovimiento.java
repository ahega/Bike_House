package com.UdeACiclo3.BikerHouse.controller;
import com.UdeACiclo3.BikerHouse.service.UsuarioService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.UdeACiclo3.BikerHouse.modelos.MovimientoDinero;
import com.UdeACiclo3.BikerHouse.modelos.Usuario;
import com.UdeACiclo3.BikerHouse.service.MovimientoDineroService;
import java.util.List;

@Controller
public class ControllerMovimiento {
    @Autowired
    MovimientoDineroService movimientoDineroService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/VerMovimientos")
    public String viewMovimientos(Model model, @ModelAttribute("mensaje") String mensaje){
        List<MovimientoDinero> listaMovimientos=movimientoDineroService.traerTodoMovimientoDinero();
        model.addAttribute("movimientoDineroList",listaMovimientos);
        model.addAttribute("mensaje",mensaje);
        return "verMovimientos"; //Llamamos al HTML
    }

    @GetMapping("/AgregarMovimiento")
    public String nuevoMovimiento(Model model, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero movimiento= new MovimientoDinero();
        model.addAttribute("mov",movimiento);
        model.addAttribute("mensaje",mensaje);
        List<Usuario>listaUsuarios= usuarioService.getAllUsuarios();
        model.addAttribute("usuariolist",listaUsuarios);
        return "agregarMovimiento";
    }


    @PostMapping("/GuardarMovimiento")
    public String guardarMovimiento(MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(movimientoDineroService.crearOActualizarMovimientoDinero(mov)){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarMovimiento";
    }
}






