package com.example.homefolder.redsubastas.Vistas;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homefolder.redsubastas.R;
import com.example.homefolder.redsubastas.Realm.Subasticas;
import com.example.homefolder.redsubastas.Realm.Usuario;

import org.w3c.dom.Text;

import io.realm.Realm;
import io.realm.RealmResults;

public class VistaDetalle extends AppCompatActivity {

    public static Realm realm;
    private int position;
    private RadioButton sus,pub,cur,fin;
    private TextView puja,text,text2,textpuj,textpuj2;
    private Subasticas subastas;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_detalle);
        Bundle intent = getIntent().getExtras();
        this.position = intent.getInt("position");
        subastas = realm.where(Subasticas.class).findAll().get(position);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(subastas.nombre);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.backs);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        puja = (TextView)findViewById(R.id.precio_minimo);
        puja.setText(""+subastas.precio_minimo);
        final TextView estado = (TextView)findViewById(R.id.estado);
        estado.setText(subastas.estado);
        final TextView descp = (TextView)findViewById(R.id.descripcion1);
        descp.setText(subastas.descripcion);
        sus = (RadioButton) findViewById(R.id.sus);
        pub = (RadioButton) findViewById(R.id.pub);
        cur = (RadioButton) findViewById(R.id.cur);
        fin = (RadioButton) findViewById(R.id.fin);
        text = (TextView)findViewById(R.id.nombre1);
        textpuj = (TextView) findViewById(R.id.precio1);
        text2 = (TextView)findViewById(R.id.nombre2);
        textpuj2 = (TextView) findViewById(R.id.precio2);
        boton = (Button) findViewById(R.id.adjudicar);

        permisos();
        text.setText(subastas.nombre1);
        textpuj.setText("" + subastas.puj1);
        text2.setText(subastas.nombre2);
        textpuj2.setText(""+subastas.puj2);

       /* boton.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(transition);
                visible = !visible;
                cover.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });*/


    }

    public void onRadioClicked(View view){
        realm.beginTransaction();
        if(sus.isChecked()){
            subastas.estado = "Suspendida";
        }
        else if(pub.isChecked()){
            subastas.estado = "Publicidad";
        }
        else if(cur.isChecked()){
            subastas.estado = "En Curso";
        }
        else if(fin.isChecked()){
            subastas.estado = "Finalizada";
        }
        realm.commitTransaction();
    }
    public void pujar(View view){
        EditText puja = (EditText)findViewById(R.id.puja_minima);

        if(!puja.getText().toString().equals("")){
            if(Integer.parseInt(puja.getText().toString()) > subastas.precio_minimo){
                realm.beginTransaction();
                subastas.precio_minimo = Integer.parseInt(puja.getText().toString());
                realm.commitTransaction();
                puja.setText(""+subastas.precio_minimo);

                realm.beginTransaction();
                if(!text.getText().toString().equals(realm.where(Usuario.class).findFirst().nombres)) {

                    text2.setText(text.getText().toString());
                    textpuj2.setText(textpuj.getText().toString());
                    subastas.id2 = subastas.id1;
                    subastas.nombre2 = text2.getText().toString();
                    subastas.puj2 = Integer.parseInt(textpuj2.getText().toString());
                    text.setText(realm.where(Usuario.class).findFirst().nombres);
                    textpuj.setText(""+subastas.precio_minimo);
                    subastas.nombre1 =  realm.where(Usuario.class).findFirst().nombres;
                    subastas.puj1 = subastas.precio_minimo;
                    subastas.id1 = realm.where(Usuario.class).findFirst().id_usuario;
                }
                else{
                    textpuj.setText(""+subastas.precio_minimo);
                    subastas.puj1 = subastas.precio_minimo;

                }
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),R.string.puja_exitosa, Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(),R.string.puja_insuficiente, Toast.LENGTH_LONG).show();
            }
        }
        else
            puja.setError(getString(R.string.empty_camp));
    }

    public void permisos(){
        if(subastas.estado.equals("En Curso") ){

            int algo = subastas.id_vendedor;
            int otro = realm.where(Usuario.class).findFirst().id_usuario;
            if((subastas.id_vendedor != realm.where(Usuario.class).findFirst().id_usuario) && (subastas.id_martillero !=  realm.where(Usuario.class).findFirst().id_usuario)) {
                findViewById(R.id.pujaid).setVisibility(View.VISIBLE);
            }
            findViewById(R.id.pujadores).setVisibility(View.VISIBLE);
            boton.setVisibility(View.VISIBLE);

        }
        if((realm.where(Usuario.class).findFirst().rol == 1) && (subastas.id_martillero == realm.where(Usuario.class).findFirst().id_usuario)){

            if(!subastas.estado.equals("Finalizada")) {
                findViewById(R.id.estadoid).setVisibility(View.VISIBLE);
            }

        }
        if(subastas.estado.equals("Finalizada")){
            findViewById(R.id.pujadores).setVisibility(View.VISIBLE);
            findViewById(R.id.segundo).setVisibility(View.GONE);
            TextView txt = (TextView)findViewById(R.id.poner);
            txt.setText(R.string.winner);
            boton.setVisibility(View.GONE);
            fin.setChecked(true);
        }
    }

    public void onClickAdjudicar(View view){

        realm.beginTransaction();
        subastas.estado = "Finalizada";
        realm.commitTransaction();
        findViewById(R.id.segundo).setVisibility(View.GONE);
        TextView txt = (TextView)findViewById(R.id.poner);
        txt.setText(R.string.winner);
        boton.setVisibility(View.GONE);
        fin.setChecked(true);


    }

}
