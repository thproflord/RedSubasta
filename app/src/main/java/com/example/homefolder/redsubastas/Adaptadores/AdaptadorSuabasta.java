package com.example.homefolder.redsubastas.Adaptadores;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homefolder.redsubastas.R;
import com.example.homefolder.redsubastas.Realm.Subasticas;
import com.example.homefolder.redsubastas.Vistas.VistaDetalle;

import io.realm.Realm;

/**
 * Created by HomeFolder on 7/2/17.
 */

public class AdaptadorSuabasta extends RecyclerView.Adapter<AdaptadorSuabasta.MyViewHolder>{

    private  Realm realm1;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView Nombre, precio, estado;



        public MyViewHolder(View view) {
            super(view);
            Nombre = (TextView) view.findViewById(R.id.nombre_subasta);
            precio = (TextView) view.findViewById(R.id.precio);
            estado = (TextView) view.findViewById(R.id.estado);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent intent = new Intent(view.getContext(),VistaDetalle.class);
            intent.putExtra("position",position);
            view.getContext().startActivity(intent);
        }

    }


    public AdaptadorSuabasta(Realm realm){

        this.realm1 = realm;
        VistaDetalle.realm = this.realm1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.esquelo_subasta,parent,false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        Subasticas subastas = realm1.where(Subasticas.class).findAll().get(position);
        holder.Nombre.setText(subastas.nombre);
        //subastas.getEstado().setText(holder);
        holder.estado.setText(subastas.estado);
        holder.precio.setText("" + subastas.precio_minimo);


    }

    @Override
    public int getItemCount(){

        return realm1.where(Subasticas.class).findAll().size();
    }


}

