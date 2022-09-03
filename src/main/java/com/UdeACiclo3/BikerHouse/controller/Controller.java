package com.UdeACiclo3.BikerHouse.controller;

import com.UdeACiclo3.BikerHouse.modelos.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.UdeACiclo3.BikerHouse.service.EmpresaService;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    EmpresaService empresaService;

    //EMPRESAS

    @GetMapping("/enterprises")
    public List<Empresa> verEmpresas(){
        return empresaService.getAllEmpresa();
    }

    @PostMapping("/enterprises")
    public Empresa guardarEmpresa(@RequestBody Empresa emp){
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @GetMapping(path = "enterprises/{id}")
    public Empresa empresaPorID(@PathVariable("id") Integer id){
        return this.empresaService.getEmpresaById(id);
    }

    @PostMapping(path = "enterprises/{id}")
    public Empresa actualizarEmpresa(@PathVariable("id") Integer id, @RequestBody Empresa empresa){
        Empresa emp= empresaService.getEmpresaById(id);
        emp.setNombre(empresa.getNombre());
        emp.setDireccion(empresa.getDireccion());
        emp.setTelefono(empresa.getTelefono());
        emp.setNIT(empresa.getNIT());
        return empresaService.saveOrUpdateEmpresa(emp);

    }

    @DeleteMapping (path = "enterprises/{id}")
    public String DeleteEmpresas(@PathVariable("id") Integer id) {
        boolean respuesta = this.empresaService.deleteEmpresa(id);
        if (respuesta) {  //Si respuetsa es true?
            return "Se elimino la empresa con id" + id;
        } else {
            return "No se pudo eliminar la empresa con id" + id;

        }
    }


}
