package com.example.homefolder.redsubastas.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by HomeFolder on 7/8/17.
 */

public class Subasticas extends RealmObject {

    public @PrimaryKey int id_subasta;
    public String nombre;
    public int precio_minimo;
    public String descripcion;
    public int id_vendedor;
    public int reputacion;
    public int id_martillero;
    public String estado;
    public String nombre1;
    public int puj1;
    public int id1;
    public String nombre2;
    public int puj2;
    public int id2;



}
