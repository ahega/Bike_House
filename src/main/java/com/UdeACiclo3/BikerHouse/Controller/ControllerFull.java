package com.UdeACiclo3.BikerHouse.Controller;

import com.UdeACiclo3.BikerHouse.Service.MovimientoDineroService;
import com.UdeACiclo3.BikerHouse.modelos.MovimientoDinero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ControllerFull {
    @Autowired
    MovimientoDineroService movimientoDineroService;
    @GetMapping("/enterprises")
    public List<MovimientoDinero> verMovimientoDinero(){
       return movimientoDineroService.traerTodoMovimientoDinero();
    }
}
