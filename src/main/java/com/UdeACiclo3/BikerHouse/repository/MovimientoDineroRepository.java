package com.UdeACiclo3.BikerHouse.repository;

import com.UdeACiclo3.BikerHouse.modelos.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovimientoDineroRepository extends JpaRepository <MovimientoDinero,Integer>{
    //movimientos por empresa

    @Query(value="select * from movimientos where usuario_id in (select id from usuario where empresa_id= ?1)", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpresa(Integer id);

    //movimientos por empleado

    @Query(value ="select * from movimientos where usuario_id= ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByUsuario(Integer id);

    //movimientos para ver la suma de todos los movimientos

    @Query(value ="SELECT SUM(monto) from movimientos", nativeQuery = true)
    public abstract Long SumarMonto();

    //movimientos para ver la suma de los movimientos por empresa

    @Query(value ="SELECT SUM(monto) from movimientos where usuario_id in (select id from usuario where empresa_id= ?1)", nativeQuery = true)
    public abstract Long MontosPorEmpresa(Integer id);

    //movimientos para ver la suma de por empleado

    @Query(value ="SELECT SUM(monto) from movimientos where usuario_id=?1", nativeQuery = true)
    public abstract Long MontosPorUsuario(Integer id);
}