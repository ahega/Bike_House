package com.UdeACiclo3.BikerHouse.modelos;

import javax.persistence.*;

@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String nombre;
    private String correo;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    private String rol;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, Empresa empresa, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.empresa = empresa;
        this.rol = rol;
    }

    //Metodos

    //Metodo leerNombreEmpleado
    public String LeerNombreEmpleado() {
        return getNombre();
    }
    //Metodo modificar empleado
    public void ModificarNombreEmpleado(String Newnombre){
        setNombre(Newnombre);}
    //Metodo leerCorreoEmpleado
    public String LeerCorreoEmpleado(){return getCorreo();}
    //Metodo ModificarCorreoEmpleado
    public void ModificarCorreoEmpleado(String newCorreo){
        setCorreo(newCorreo);}
    //Metodo LeerEmpresaEmpleado
    public String LeerNombreEmpresaEmpleado(){return getEmpresa().getNombre();}

    //Metodo ModificarNombreEmpresaEmpleado
    public void ModificarNombreEmpresaEmpleado(String NewNombreEmpresaEmpleado){
        empresa.setNombre(NewNombreEmpresaEmpleado);
    }
    //Metodo LeerRolEmpleado
    public String LeerRolEmpleado(){return getRol();}
    //Metodo ModificarRolEmpleado
    public void ModificarRolEmpleado(String newRol){setRol(newRol);}



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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
        }


    public void setRol(String rol) {
        this.rol = rol;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
