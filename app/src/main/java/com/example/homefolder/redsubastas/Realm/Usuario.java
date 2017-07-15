package com.example.homefolder.redsubastas.Realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by HomeFolder on 7/11/17.
 */

public class Usuario extends RealmObject {

    public int id_usuario;
    public String email;
    public String password;
    public boolean log;
    public int rol;
    public String nombres;

}
