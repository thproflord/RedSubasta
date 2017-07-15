package com.example.homefolder.redsubastas.Estados;

import com.example.homefolder.redsubastas.Adaptadores.AdaptadorSuabasta;

/**
 * Created by HomeFolder on 6/25/17.
 */

public abstract class Estado {

    private int duracion;

    public abstract void setText(AdaptadorSuabasta.MyViewHolder holder);

}
