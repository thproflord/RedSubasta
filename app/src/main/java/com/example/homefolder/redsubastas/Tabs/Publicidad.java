package com.example.homefolder.redsubastas.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homefolder.redsubastas.R;

/**
 * Created by HomeFolder on 7/2/17.
 */

public class Publicidad extends Fragment {

    public Publicidad(){


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.publicidad, container, false);


        return rootView;

    }


}
