package com.example.homefolder.redsubastas.Tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.homefolder.redsubastas.Adaptadores.AdaptadorSuabasta;

import io.realm.Realm;


/**
 * Created by HomeFolder on 7/2/17.
 */

public class ViewPagerPrin extends FragmentStatePagerAdapter {
    int numOfTabs;

    private Subastas tab1 = new Subastas();
    private Destacadas tab2 = new Destacadas();
    private Publicidad tab3 = new Publicidad();
    private Curso tab4 = new Curso();

    public ViewPagerPrin(FragmentManager fm, int numOfTabs){

        super(fm);
        this.numOfTabs = numOfTabs;
    }

    public void setRealm(Realm realm){

        tab1.setRealm(realm);

    }

    public AdaptadorSuabasta getInstance(){
        return tab1.getInstance();
    }

    public Fragment getItem(int position){

        switch(position){

            case 0 :
                return tab1;

            case 1 :
                return tab2;
            case 2 :

                return tab3;
            case 3 :
                return tab4;
            default:
                return null;
        }

    }

    public int getCount(){
        return numOfTabs;
    }
}

