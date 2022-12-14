package com.UdeACiclo3.BikerHouse.modelos;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Movimientos")
public class MovimientoDinero {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private long monto;
    private String concepto;
    @ManyToOne
    @JoinColumn(name= "usuario_id" )
    private Usuario usuario;
    @DateTimeFormat(pattern="yyyy-MM-dd")// Anotacion para el atributo fecha del movimiento
    private Date fecha;

//METODOS:

    // Metodo leerMonto

    public long leerMonto() {
        return getMonto();
    }
    //Metodo modificar Monto
    public void ModificarMonto(long Newmonto){
        setMonto(Newmonto);
    }
    //Metodo leer Concepto de Dinero
    public String leerConcepto(){
        return getConcepto();
    }
    //Metodo Modificar Concepto de Dinero
    public void ModificarConcepto(String newConcepto){
        setConcepto(newConcepto);
    }

    //Metodo para mostra el usuario encargado de registrar el movimiento
    public String LeerEmpleadoMovimiento(){
        return getUsuario().getNombre();
    }




//CONSTRUCTOR


    public MovimientoDinero() {
    }


    public MovimientoDinero(long monto, String concepto, Usuario usuario, Date fecha) {

        this.monto = monto;
        this.concepto = concepto;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    //GET Y SET PARA LEER Y MODIFICAR LOS ATRIBUTOS


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMonto() {
        return monto;
    }

    public void setMonto(long monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}