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
    public Optional <Usuario> saveOrUpdateUsuario(Usuario usuario){
        Usuario usuario2=usuarioRepository.save(usuario);
        return usuarioRepository.findById(usuario2.getId());

    }

    //Metodo (servicio) para eliminar una empresa
    public boolean deleteUsuario(Integer id){
        usuarioRepository.deleteById(id);
        if(usuarioRepository.findById(id).get()!=null){  //== getUsuarioById(id)
            return false;
        }
        return true;
    }


}


























///comentarios