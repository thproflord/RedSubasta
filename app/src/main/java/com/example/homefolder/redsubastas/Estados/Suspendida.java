package com.example.homefolder.redsubastas.Estados;

import com.example.homefolder.redsubastas.Adaptadores.AdaptadorSuabasta;

/**
 * Created by HomeFolder on 6/25/17.
 */

public class Suspendida extends Estado {

    public void setText(AdaptadorSuabasta.MyViewHolder holder){

        holder.estado.setText("Suspendida");

    }
}
