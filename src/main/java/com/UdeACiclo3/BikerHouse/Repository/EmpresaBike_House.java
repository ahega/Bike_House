package com.UdeACiclo3.BikerHouse.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.UdeACiclo3.BikerHouse.modelos.Empresa;

@Repository
public interface EmpresaBike_House extends JpaRepository<Empresa, Integer> {

}
