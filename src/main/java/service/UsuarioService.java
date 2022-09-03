package service;

import com.UdeACiclo3.BikerHouse.Repository.UsuarioRepository;
import com.UdeACiclo3.BikerHouse.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuario(){
        List<Usuario> usuarioList= new ArrayList<>();
        usuarioRepository.findAll().forEach(usuario -> usuarioList.add(usuario));
        return usuarioList;
    }
}
