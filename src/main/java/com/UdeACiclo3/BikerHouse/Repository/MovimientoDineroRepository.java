package com.UdeACiclo3.BikerHouse.Repository;

import com.UdeACiclo3.BikerHouse.modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoDineroRepository extends JpaRepository <MovimientoDinero,Integer>{

}

