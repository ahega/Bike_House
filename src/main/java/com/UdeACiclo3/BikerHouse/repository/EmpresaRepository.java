package com.UdeACiclo3.BikerHouse.repository;

import com.UdeACiclo3.BikerHouse.modelos.Empresa;
import com.UdeACiclo3.BikerHouse.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository //Anotación que le dice a Spring Boot que esta clase es un repositorio
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}
