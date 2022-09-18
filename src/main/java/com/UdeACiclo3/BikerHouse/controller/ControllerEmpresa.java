package com.UdeACiclo3.BikerHouse.controller;

import com.UdeACiclo3.BikerHouse.modelos.Empresa;
import com.UdeACiclo3.BikerHouse.modelos.MovimientoDinero;
import com.UdeACiclo3.BikerHouse.modelos.Usuario;
import com.UdeACiclo3.BikerHouse.service.MovimientoDineroService;
import com.UdeACiclo3.BikerHouse.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.UdeACiclo3.BikerHouse.service.EmpresaService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ControllerEmpresa {

    @Autowired
    EmpresaService empresaService;

    //EMPRESAS

    @GetMapping ({"/","/VerEmpresas"})
    public String viewEmpresas(Model model, @ModelAttribute("mensaje") String mensaje){
        List<Empresa> listaEmpresas=empresaService.getAllEmpresas();
        model.addAttribute("emplist",listaEmpresas);
        model.addAttribute("mensaje",mensaje);
        return "verEmpresas"; //Llamamos al HTML
    }

    @GetMapping("/AgregarEmpresa")
    public String nuevaEmpresa(Model model, @ModelAttribute("mensaje") String mensaje){
        Empresa emp= new Empresa();
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje",mensaje);
        return "agregarEmpresa";
    }

    @PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAttributes){
        if(empresaService.saveOrUpdateEmpresa(emp)==true){
            redirectAttributes.addFlashAttribute("mensaje","saveOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","saveError");
        return "redirect:/AgregarEmpresa";
    }

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        Empresa emp=empresaService.getEmpresaById(id);
        //Creamos un atributo para el modelo, que se llame igualmente emp y es el que ira al html para llenar o alimentar campos
        model.addAttribute("emp",emp);
        model.addAttribute("mensaje", mensaje);
        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String updateEmpresa(@ModelAttribute("emp") Empresa emp, RedirectAttributes redirectAttributes){
        if(empresaService.saveOrUpdateEmpresa(emp)){
            redirectAttributes.addFlashAttribute("mensaje","updateOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje","updateError");
        return "redirect:/EditarEmpresa/"+emp.getId();

    }

    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if (empresaService.deleteEmpresa(id)==true){
            redirectAttributes.addFlashAttribute("mensaje","deleteOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerEmpresas";
    }

    /*//////////////////////////////////////////////////////////
    /////////////////////////   USUARIOS   //////////////////
    //////////////////////////////////////////////////////////

    @Autowired
    UsuarioService usuarioService;

    //Ver todos los usuarios
    @GetMapping("/user")
    public List<Usuario> verUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    //Guardar un Usuario
    @PostMapping("/user")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveOrUpdateUsuario(usuario);
    }

    //Ver usuario por ID
    @GetMapping("/user/{id}")
    public Usuario usuarioPorID(@PathVariable("id") Integer id) {
        return usuarioService.getUsuarioById(id);
    }

    //Actualizar usuario por id
    @PatchMapping("/user/{id}")
    public Usuario actualizarUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
        Usuario usuario1 = usuarioService.getUsuarioById(id);
        usuario1.setNombre(usuario.getNombre());
        usuario1.setCorreo(usuario.getCorreo());
        usuario1.setEmpresa(usuario.getEmpresa());
        usuario1.setRol(usuario.getRol());

        return usuarioService.saveOrUpdateUsuario(usuario1);

    }

    @DeleteMapping("/usuarios/{id}")
    public String DeleteUsuario(@PathVariable("id") Integer id) {
        boolean respuesta = usuarioService.deleteUsuario(id);
        if (respuesta) {  //Si respuetsa es true?
            return "Se elimino el usuario con id" + id;
        } else {
            return "No se pudo eliminar el usuario con id" + id;

        }
    }

    // ver empleados por empresas
    @GetMapping("/enterprises/{id}/usuarios")
    public ArrayList<Usuario> usuarioByEmpresa(@PathVariable("id") Integer id) {

        return usuarioService.obtenerPorEmpresa(id);
    }

    ////////////////////////////////////////////////////////////////////
    ///////////////////////// Movimientos /////////////////////////////
    //////////////////////////////////////////////////////////////////

    @Autowired
    MovimientoDineroService movimientoDineroService;

    @GetMapping("/movimientos")
    public List<MovimientoDinero> verMovimientosDinero() {

        return movimientoDineroService.traerTodoMovimientoDinero();
    }

    @GetMapping("/enterprises/{id}/movements")
    public ArrayList<MovimientoDinero> verUsuarios(@PathVariable("id") Integer id) {
        return movimientoDineroService.obtenerMovimientoByEmpresa(id);
    }

    @PostMapping("/enterprises/{id}/movements")
    public Optional<MovimientoDinero> guardarMovimiento(@PathVariable("id") Integer id, @RequestBody MovimientoDinero mov) {
        return movimientoDineroService.crearOActualizarMovimientoDinero(mov);

    }

    @PatchMapping("/enterprises/{id}/movements")
    public Optional<MovimientoDinero> actualizarMovimientoDinero(@PathVariable("id") Integer id, @RequestBody MovimientoDinero mov) {
        MovimientoDinero mov1 = movimientoDineroService.traerMovimientoDineroPorId(id);
        mov1.setMonto(mov.getMonto());
        mov1.setConcepto(mov.getConcepto());
        mov1.setUsuario(mov.getUsuario());

        return movimientoDineroService.crearOActualizarMovimientoDinero(mov1);
        //return usuarioService.saveOrUpdateUsuario(usuario1);

    }


    @DeleteMapping("/enterprises/{id}/movements")
    public String DeleteMovimientoByEmpresa(@PathVariable("id") Integer id) {
        boolean respuesta = movimientoDineroService.eliminarMovimientoDinero(id);

        if (respuesta) {  //Si respuetsa es true?
            return "Se elimino el movimiento de la empresa con id" + id;
        }
        return "No se pudo eliminar el movimiento con id" + id;


    }*/
}


