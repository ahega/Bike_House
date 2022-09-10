package com.UdeACiclo3.BikerHouse.service;


import com.UdeACiclo3.BikerHouse.modelos.MovimientoDinero;
import com.UdeACiclo3.BikerHouse.repository.MovimientoDineroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoDineroService {
    @Autowired
    MovimientoDineroRepository movimientoDineroRepository; /*creamos un objeto de tpo mivimientoDineroRepository para poder usar kos metodos*/

    /*Lista de los movimientos de Dinero*/
    public List <MovimientoDinero> traerTodoMovimientoDinero(){
        List<MovimientoDinero> movimientoDineroList= new ArrayList<>();
        movimientoDineroRepository.findAll().forEach(movimientoDinero -> movimientoDineroList.add(movimientoDinero));
        return movimientoDineroList;
    }
    /*Editar/actualizar movimiento de Dinero de la lista; Si lo encontro id lo actualiza, si no lo crea*/

    public MovimientoDinero crearOActualizarMovimientoDinero(MovimientoDinero movimientoDinero){
       // MovimientoDinero movimientoDi= movimientoDineroRepository.save(movimientoDinero);

        return movimientoDineroRepository.save(movimientoDinero);

    }
    /*Traer movimiento de dinero por id*/
    public MovimientoDinero traerMovimientoDineroPorId(Integer id){
        return movimientoDineroRepository.findById(id).get();
    }
    /*Eliminar movimiento de dinero teniendo el id*/
    public boolean eliminarMovimientoDinero( Integer id){
        movimientoDineroRepository.deleteById(id);
        if(movimientoDineroRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }






    //// Metodo para traer todos los movimientos de una empresa por su id
    public ArrayList<MovimientoDinero> obtenerMovimientoByEmpresa(Integer id){
        return movimientoDineroRepository.findByEmpresa(id);
    }

}