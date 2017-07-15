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

public class Destacadas  extends Fragment {


    public Destacadas(){


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.destacadas, container, false);

        return rootView;

    }


}