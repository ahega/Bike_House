package com.UdeACiclo3.BikerHouse.service;

import com.UdeACiclo3.BikerHouse.modelos.Usuario;
import com.UdeACiclo3.BikerHouse.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository; // este objeto hereda todos los metodos de UsuarioRepository

    //Servicio (Metodo) para ver todos los usuarios
    public List<Usuario> getAllUsuarios(){
        List<Usuario> usuarioList= new ArrayList<>();
        usuarioRepository.findAll().forEach(usuario -> usuarioList.add(usuario));
        return usuarioList;
    }

    //Metodo (servicio) para traer un usuario por ID
    public Usuario getUsuarioById(Integer id){

        return usuarioRepository.findById(id).get();
    }

    //Metodo (servicio) para guardar o actualizar un objeto
    //Crea y edita un objeto de tipo usuario
    public boolean saveOrUpdateUsuario(Usuario usuario){
        Usuario usuario1=usuarioRepository.save(usuario);
        if (usuarioRepository.findById(usuario1.getId())!=null){
            return true;
        }
        return false;
    }

    //Metodo (servicio) para eliminar una empresa
    public boolean deleteUsuario(Integer id){
        usuarioRepository.deleteById(id);
        //if(usuarioRepository.findById(id).get()!=null){  //== getUsuarioById(id)
        if(usuarioRepository.findById(id).isPresent()){  //== getUsuarioById(id)
            return false;
        }
        return true;
    }

    //Metodo para ver empleados por empresa

    public ArrayList<Usuario> obtenerPorEmpresa(Integer id){
        return usuarioRepository.findByEmpresa(id);
    }

}




