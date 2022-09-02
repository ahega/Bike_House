package com.UdeACiclo3.BikerHouse.Service;


import com.UdeACiclo3.BikerHouse.Repository.MovimientoDineroRepository;
import com.UdeACiclo3.BikerHouse.modelos.MovimientoDinero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
         MovimientoDinero movimientoDi= movimientoDineroRepository.save(movimientoDinero);
         if(movimientoDineroRepository.findById(movimientoDi.getId())!=null){
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
         if(traerMovimientoDineroPorId(id)!=null){
             return false;
         }
         return true;
     }


}
