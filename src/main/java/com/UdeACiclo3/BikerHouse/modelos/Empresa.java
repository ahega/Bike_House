package com.UdeACiclo3.BikerHouse.modelos;

import javax.persistence.*;

@Entity
@Table(name = "Empresa")
public class Empresa {
    @Id // id en la base de datos
    @GeneratedValue(strategy = GenerationType.AUTO) //Generar id de forma automática
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String NIT;

    public Empresa(){
    }

    public Empresa(String nombre, String direccion, String telefono, String NIT) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.NIT = NIT;
    }

    // Metodo LeerNombreEmpresa
    public String LeerNombreEmpresa(Empresa empresa){
        return empresa.nombre;
    }

    //Metodo modificar nombre empresa
    public String ModificarNombreEmpresa(Empresa empresa, String nuevoNombre){
        empresa.setNombre(nuevoNombre);
        return getNombre();
    }

    // Metodo LeerDireccionEmpresa
    public String LeerDireccionEmpresa(Empresa empresa){
        return empresa.direccion;
    }

    //Metodo modificar nombre empresa
    public String ModificarDireccionEmpresa( String nuevaDireccion){
        setDireccion(nuevaDireccion);
        return getDireccion();
    }

    // Metodo Leer Telefono Empresa
    public String LeerTelefonoEmpresa(Empresa empresa){
        return empresa.getTelefono();
    }

    //Metodo modificar telefono empresa
    public String ModificarTelefonoEmpresa(Empresa empresa, String nuevoTelefono){
        empresa.setTelefono(nuevoTelefono);
        return getTelefono();
    }

    // Metodo Leer NIT Empresa
    public String LeerNITEmpresa(Empresa empresa){
        return empresa.getNIT();
    }

    //Metodo modificar NIT Empresa
    public String ModificarNITEmpresa(Empresa empresa, String nuevoNIT){
        empresa.setNIT(nuevoNIT);
        return getNIT();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }
}
