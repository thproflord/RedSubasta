package com.example.homefolder.redsubastas.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.homefolder.redsubastas.Adaptadores.AdaptadorSuabasta;
import com.example.homefolder.redsubastas.R;


import io.realm.Realm;

/**
 * Created by HomeFolder on 7/2/17.
 */

public class Subastas extends Fragment {

    private static AdaptadorSuabasta mAdapter;
    private Realm realm;

    public Subastas(){


    }
    public void setRealm(Realm realm){

       this.realm = realm;

    }

    public AdaptadorSuabasta getInstance(){
        return mAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.subastas, container, false);
        RecyclerView rv = (RecyclerView)rootView.findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        mAdapter = new AdaptadorSuabasta(realm);

        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(mAdapter);


        return rootView;

    }
    @Override
    public void onResume(){
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }



}