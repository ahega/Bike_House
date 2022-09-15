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
public class ControllerFull {

    @Autowired
    EmpresaService empresaService;

    //EMPRESAS

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas() {
        return empresaService.getAllEmpresas();
    }

    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody Empresa emp) {
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @GetMapping(path = "enterprises/{id}")
    public Empresa empresaPorID(@PathVariable("id") Integer id) {
        return this.empresaService.getEmpresaById(id);
    }

    @PatchMapping(path = "enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa) {
        Empresa emp = empresaService.getEmpresaById(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNIT(empresa.getNIT());
        return empresaService.saveOrUpdateEmpresa(emp);

    }

    @DeleteMapping(path = "enterprises/{id}")
    public String DeleteEmpresas(@PathVariable("id") Integer id) {
        boolean respuesta = this.empresaService.deleteEmpresa(id);
        if (respuesta) {  //Si respuetsa es true?
            return "Se elimino la empresa con id" + id;
        } else {
            return "No se pudo eliminar la empresa con id" + id;

        }
    }


    //////////////////////////////////////////////////////////
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
    /*
    @PostMapping("/user")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveOrUpdateUsuario(usuario);
    }
*/
    //Ver usuario por ID
    @GetMapping("/user/{id}")
    public Usuario usuarioPorID(@PathVariable("id") Integer id) {
        return usuarioService.getUsuarioById(id);
    }

    //Actualizar usuario por id
    /*
    @PatchMapping("/user/{id}")
    public Usuario actualizarUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
        Usuario usuario1 = usuarioService.getUsuarioById(id);
        usuario1.setNombre(usuario.getNombre());
        usuario1.setCorreo(usuario.getCorreo());
        usuario1.setEmpresa(usuario.getEmpresa());
        usuario1.setRol(usuario.getRol());

        return usuarioService.saveOrUpdateUsuario(usuario1);

    }*/

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
    public MovimientoDinero guardarMovimiento(@PathVariable("id") Integer id, @RequestBody MovimientoDinero mov) {
        return movimientoDineroService.crearOActualizarMovimientoDinero(mov);

    }

    @PatchMapping("/enterprises/{id}/movements")
    public MovimientoDinero actualizarMovimientoDinero(@PathVariable("id") Integer id, @RequestBody MovimientoDinero mov) {
        MovimientoDinero mov1 = movimientoDineroService.traerMovimientoDineroPorId(id);
        //ArrayList mov1 = movimientoDineroService.obtenerMovimientoByEmpresa(id);
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


    }

    //////////////////////////////////////////////////////////////////////
    /////////////////////////USUARIOS CON CONTROLLER
    //////////////////////////////////////////////////////////////////////


    /*
    @GetMapping ("/VerUsuarios")
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
        List<Empresa> listaEmpresas= empresaService.getAllEmpresas();
        model.addAttribute("emprelist",listaEmpresas);
        return "agregarUsuario";

    }

    @PostMapping("/GuardarUsuario")
    public String guardarUsuario(Usuario usuario, RedirectAttributes redirectAttributes){
        if(usuarioService.saveOrUpdateUsuario(usuario)==true){
            return "redirect:/VerUsuarios";
        }
        return "redirect:/AgregarUsuario";

    }

     */
}


