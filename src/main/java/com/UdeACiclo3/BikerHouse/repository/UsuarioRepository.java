package com.UdeACiclo3.BikerHouse.repository;

import com.UdeACiclo3.BikerHouse.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario,Integer> {
    @Query(value="SELECT * FROM usuario where empresa_id= ?1", nativeQuery = true)
    public abstract ArrayList<Usuario> findByEmpresa(Integer id);
}