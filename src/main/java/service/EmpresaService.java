package service;

import com.UdeACiclo3.BikerHouse.Repository.EmpresaBike_House;
import com.UdeACiclo3.BikerHouse.modelos.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmpresaService {
    @Autowired
    EmpresaBike_House empresaBike_house;

    public List<Empresa>  getAllEmpresa(){
        List<Empresa> empresaList= new ArrayList<>();
        empresaBike_house.findAll().forEach(empresa -> empresaList.add(empresa));
        return empresaList;
    }

    public Empresa getEmpresaById(Integer id){
        return empresaBike_house.findById(id).get();

    }

    public Empresa saveOrUpdateEmpresa(Empresa empresa){
        return empresaBike_house.save(empresa);

    }

    public boolean deleteEmpresa(Integer id){
        empresaBike_house.deleteById(id);
        if (empresaBike_house.findById(id)==null){
            return true;
        }
        return false;
    }
}
