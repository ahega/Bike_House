package com.UdeACiclo3.BikerHouse.service;

import com.UdeACiclo3.BikerHouse.modelos.Empresa;
import com.UdeACiclo3.BikerHouse.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //Le decimos a Sprint Boot que esta clase es de servicios
public class EmpresaService {
    @Autowired //Conectamos esta clase con el repositorio de Empresa
    EmpresaRepository empresaRepository; //Creamos un objeto tipoEmpresaRepository para poder usar los métodos que dicha clase hereda

    //Método que retornará la lista de empresas usando métodos heredados de JpaRepository
    public List<Empresa> getAllEmpresas(){
        List<Empresa> empresaList = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> empresaList.add(empresa)); //Recorremos e iterable que regresa el método findAll del Jpa y lo guardamos en la lista
        return empresaList;
    }

    //Método que trae un objeto de tipo Empresa cuando cuento con el id de la misma
    public Empresa getEmpresaById(Integer id){
        return empresaRepository.findById(id).get();
    }

    //Método para guardar o actualizar objetos de tipo Empresa
    public Empresa saveOrUpdateEmpresa(Empresa empresa){ return empresaRepository.save(empresa);}

    //Método que me premita eliminar empresas resgiatradas tenciendo el id
    public boolean deleteEmpresa(Integer id){
        empresaRepository.deleteById(id);  //Eliminar
        if (empresaRepository.findById(id)!=null){  //Verificación del servicio eliminación
            return true;
        }
        return false;
    }
}