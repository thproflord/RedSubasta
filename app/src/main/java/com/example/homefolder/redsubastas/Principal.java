package com.example.homefolder.redsubastas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.homefolder.redsubastas.Realm.*;
import com.example.homefolder.redsubastas.Tabs.ViewPagerPrin;
import com.example.homefolder.redsubastas.Vistas.Crear;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        private ViewPagerPrin adapter;
        private int id;
        private int id_usuario;
        private String mEmail;
        private int rol;
        private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        this.id_usuario = bundle.getInt("recurso");
        this.mEmail = bundle.getString("email");
        this.rol = bundle.getInt("rol");

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();// CONFIGURACION DE REALM
        //Realm.deleteRealm(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);
        if(realm.where(Subasticas.class).findAll().size() != 0) {
            this.id =  (realm.where(Subasticas.class).findAllSorted("id_subasta").last().id_subasta) + 1;
        } else{
            this.id = 0;
        }
        cargarData();
        final TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Subastas"));
        tabs.setSelectedTabIndicatorColor(Color.rgb(226, 113, 47));
        tabs.addTab(tabs.newTab().setText("Destacadas"));
        tabs.addTab(tabs.newTab().setText("Publicidad"));
        tabs.addTab(tabs.newTab().setText("En Curso"));
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);


        final ViewPager view_pager = (ViewPager) findViewById(R.id.pager);
        adapter = new ViewPagerPrin(getSupportFragmentManager(), tabs.getTabCount());
        adapter.setRealm(realm);
        view_pager.setAdapter(adapter);
        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_pager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {

                    case 0:
                        tabs.setSelectedTabIndicatorColor(Color.rgb(226, 113, 47));
                        break;

                    case 1:
                        tabs.setSelectedTabIndicatorColor(Color.rgb(226, 113, 47));
                        break;
                    case 2:
                        tabs.setSelectedTabIndicatorColor(Color.rgb(226, 113, 47));
                        break;
                    case 3:
                        tabs.setSelectedTabIndicatorColor(Color.rgb(226, 113, 47));
                        break;

                }
            }
            public void onTabUnselected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {

                    case 0:
                        break;

                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;

                }
            }
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Crear.class);
                startActivityForResult(intent,1);


            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){

                crearSubasta(data.getStringExtra("nombre"),data.getStringExtra("descripcion"),data.getStringExtra("precio"));

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                String result = data.getStringExtra("noresult");

            }
        }
    }//onActivityResult

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

            realm.beginTransaction();
            realm.where(Usuario.class).findFirst().log = false;
            realm.commitTransaction();
            finish();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void crearSubasta(String nombre, String descripcion, String precio){

        realm.beginTransaction();
        Subasticas subasta = realm.createObject(Subasticas.class,this.id);
        subasta.id_martillero = 1;
        subasta.id_vendedor = realm.where(Usuario.class).findFirst().id_usuario;
        subasta.nombre = nombre;
        subasta.descripcion = descripcion;
        subasta.precio_minimo = Integer.parseInt(precio);
        subasta.reputacion = 1;
        subasta.estado = "Inactiva";
        realm.commitTransaction();
        this.id++;
        adapter.getInstance().notifyDataSetChanged();

    }

    public void cargarData(){
        realm.beginTransaction();
        Usuario usuario = realm.where(Usuario.class).findFirst();
        if(this.rol == 0){
            if(this.id_usuario == 0) {
                usuario.id_usuario = 0;
                usuario.email = this.mEmail;
                usuario.password = "0";
                usuario.rol = 0;
                usuario.log = true;
                usuario.nombres = "Enrique manuel";
            }
            else if(this.id_usuario == 2){

                usuario.id_usuario = 2;
                usuario.email = this.mEmail;
                usuario.password = "0";
                usuario.rol = 0;
                usuario.log = true;
                usuario.nombres = "Fernando";

            }
            else if(this.id_usuario == 3){
                usuario.id_usuario = 0;
                usuario.email = this.mEmail;
                usuario.password = "0";
                usuario.rol = 0;
                usuario.log = true;
                usuario.nombres = "Jose manuel";
            }

        }
        else{
            if(this.id_usuario == 1){
            usuario.id_usuario = 1;
            usuario.email = this.mEmail;
            usuario.password = "0";
            usuario.rol = 1;
            usuario.log = true;
            usuario.nombres = "Alberto manuel";
            }
            else if(this.id_usuario == 4){

                usuario.id_usuario = 0;
                usuario.email = this.mEmail;
                usuario.password = "0";
                usuario.rol = 1;
                usuario.log = true;
                usuario.nombres = "Ricardo Jose";
            }

        }
        realm.commitTransaction();

    }


}
