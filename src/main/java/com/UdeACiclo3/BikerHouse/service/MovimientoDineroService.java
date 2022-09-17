package com.UdeACiclo3.BikerHouse.service;


import com.UdeACiclo3.BikerHouse.modelos.Empresa;
import com.UdeACiclo3.BikerHouse.modelos.MovimientoDinero;
import com.UdeACiclo3.BikerHouse.modelos.Usuario;
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

    public boolean crearOActualizarMovimientoDinero(MovimientoDinero movimientoDinero){
        MovimientoDinero mov= movimientoDineroRepository.save(movimientoDinero);
        if(movimientoDineroRepository.findById(mov.getId())!=null){
            return true;
        }
        return false;
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

    // Metodo para traer todos los movimientos de una empresa por su id
    public ArrayList<MovimientoDinero> obtenerMovimientoByEmpresa(Integer id){
        return movimientoDineroRepository.findByEmpresa(id);
    }
    //Obterner teniendo en cuenta el id del empleado
    public ArrayList<MovimientoDinero> obtenerPorUsuario(Integer id) {
        return movimientoDineroRepository.findByUsuario(id);
    }

    //Suma de todos los movimientos
    public Long obtenrSumaMontos(){
        return movimientoDineroRepository.SumarMonto();
    }

    //Suma de todos Movimientos por empleado
    public Long MontosPorUsuario(Integer id){
        return movimientoDineroRepository.MontosPorUsuario(id);
    }

    //Suma de todos Movimientos por empresa
    public Long MontosPorEmpresa(Integer id){
        return movimientoDineroRepository.MontosPorEmpresa(id);
    }


}

