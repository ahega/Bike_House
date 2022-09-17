package com.UdeACiclo3.BikerHouse.controller;
import com.UdeACiclo3.BikerHouse.service.UsuarioService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    //Ver Lista Movimientos
    @GetMapping("/VerMovimientos")
    public String viewMovimientos(Model model, @ModelAttribute("mensaje") String mensaje){
        List<MovimientoDinero> listaMovimientos=movimientoDineroService.traerTodoMovimientoDinero();
        model.addAttribute("movlist",listaMovimientos);
        model.addAttribute("mensaje",mensaje);
        Long sumaMonto=movimientoDineroService.obtenrSumaMontos();
        model.addAttribute("SumaMontos",sumaMonto);
        return "verMovimientos";
    }

    //Agregar Movimiento

    @GetMapping("/AgregarMovimiento")
    public String nuevoMovimiento(Model model, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero movimiento= new MovimientoDinero();
        model.addAttribute("mov",movimiento);
        model.addAttribute("mensaje",mensaje);
        List<Usuario>listaUsuarios= usuarioService.getAllUsuarios();
        model.addAttribute("usuariolist",listaUsuarios);
        return "agregarMovimiento";
    }

    //Guardar Movimiento

    @PostMapping("/GuardarMovimiento")
    public String guardarMovimiento(MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(movimientoDineroService.crearOActualizarMovimientoDinero(mov)){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarMovimiento";
    }

    //Editar Movimiento

    @GetMapping("/EditarMovimiento/{id}")
    public String editarMovimiento(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero mov= movimientoDineroService.traerMovimientoDineroPorId(id);
        model.addAttribute("mov", mov);
        List<Usuario>listaUsuarios= usuarioService.getAllUsuarios();
        model.addAttribute("usuariolist",listaUsuarios);
        return "editarMovimiento";
    }

    //Actualizar Movimiento

    @PostMapping("/ActualizarMovimiento")
    public String actualizarMovimiento(@ModelAttribute("mov") MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(movimientoDineroService.crearOActualizarMovimientoDinero(mov)){
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/EditarMovimientos/"+mov.getId();
    }

    //Eliminar Movimiento

    @GetMapping("/EliminarMovimiento/{id}")
    public String eliminarMovimiento(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if(movimientoDineroService.eliminarMovimientoDinero(id)){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/VerMovimientos";

    }

    //Obtener Movimientos  por Usuario
    @GetMapping("/Usuario/{id}/Movimientos")
    public String movimientosPorUsuario(@PathVariable("id")Integer id, Model model){
        List<MovimientoDinero> listaMovimientos=movimientoDineroService.obtenerPorUsuario(id);
        model.addAttribute("movlist",listaMovimientos);
        Long sumaMonto=movimientoDineroService.MontosPorUsuario(id);
        model.addAttribute("SumaMontos",sumaMonto);
        return "verMovimientos";
    }

    //Obtener Movimientos  por Empresa
    @GetMapping("/Empresa/{id}/Movimientos")
    public String movimientosPorEmpresa(@PathVariable("id")Integer id, Model model){
        List<MovimientoDinero> movlist = movimientoDineroService.obtenerMovimientoByEmpresa(id);
        model.addAttribute("movlist",movlist);
        Long sumaMonto=movimientoDineroService.MontosPorEmpresa(id);
        model.addAttribute("SumaMontos",sumaMonto);
        return "verMovimientos";

    }

}






