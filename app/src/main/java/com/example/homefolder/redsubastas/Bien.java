package com.example.homefolder.redsubastas;

import java.util.ArrayList;

/**
 * Created by HomeFolder on 6/25/17.
 */

/**
 * Este sera el objeto de los bienes en general con su informacion
 */
public class Bien {

    private String nombre;
    private int precio_minimo;
    private String descripcion;

    private int id_vendedor;
    private int reputacion;


    public Bien(String nombre,int precio,String descripcion,int vendedor,int reputacion){

        this.nombre = nombre;
        this.precio_minimo = precio;
        this.descripcion = descripcion;
        this.id_vendedor = vendedor;
        this.reputacion = reputacion;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public int getPrecio_minimo(){
        return this.precio_minimo;
    }

    public int getId_vendedor(){
        return this.id_vendedor;
    }

    public int getReputacion(){
        return this.reputacion;
    }


}
