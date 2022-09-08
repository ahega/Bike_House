package com.UdeACiclo3.BikerHouse.controller;

import com.UdeACiclo3.BikerHouse.modelos.Empresa;
import com.UdeACiclo3.BikerHouse.modelos.Usuario;
import com.UdeACiclo3.BikerHouse.service.EmpresaService;
import com.UdeACiclo3.BikerHouse.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    EmpresaService empresaService;

    @Autowired
    UsuarioService usuarioService;


    //EMPRESAS

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas() {return empresaService.getAllEmpresas();}

    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody Empresa emp) {
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @GetMapping(path = "enterprises/{id}")
    public Empresa empresaPorID(@PathVariable("id") Integer id) {return this.empresaService.getEmpresaById(id);
    }

    @PostMapping(path = "enterprises/{id}")
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

    //USUARIOS

    @GetMapping("/usuario")
    public List<Usuario> verUsuario() {
        return usuarioService.getAllUsuarios();
    }

    @PostMapping("/usuario")
    public Optional<Optional<Usuario>> guardarUsuario(@RequestBody Usuario usua) {
        return Optional.ofNullable(this.usuarioService.saveOrUpdateUsuario(usua));
    }

    @GetMapping(path = "usuarios/{id}")
    public Optional<Usuario> usuarioPorId(@PathVariable("id") Integer id) {
        return Optional.ofNullable(this.usuarioService.getUsuarioById(id));
    }

    @GetMapping("/enterprises/{id}/usuarios")
    public Optional<Usuario> UusariosPorEmpresa(@PathVariable ("id") Integer id){
        return this.usuarioService.obtenerPorEmpresa(id);
    }

    @PatchMapping("/usuarios/{id}")
    public Optional<Usuario> actualizarUsuario(@PathVariable("id") Integer id, @RequestBody Usuario usuario){
        Usuario usua=usuarioService.getUsuarioById(id).get();
        usua.setNombre(usuario.getNombre());
        usua.setCorreo(usuario.getCorreo());
        usua.setEmpresa(usuario.getEmpresa());
        usua.setRol(usuario.getRol());

        return usuarioService.saveOrUpdateUsuario(usua);

    }

}