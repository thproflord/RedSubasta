package com.example.homefolder.redsubastas.Vistas;

import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.homefolder.redsubastas.R;


public class Crear extends AppCompatActivity {

    private EditText nombre, descripcion, precio;
    private ImageView imageView;
    private LinearLayout ll;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.backs);
        nombre = (EditText) findViewById(R.id.nombre);
        descripcion = (EditText) findViewById(R.id.descripcion1);
        precio = (EditText) findViewById(R.id.precio);
        ll = (LinearLayout)findViewById(R.id.imagenes);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("noresult", "marico");
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }

        });

        /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();


            }
        });*/
    }


    public void selectImage(){

        final CharSequence[] items = {getString(R.string.camera),getString(R.string.gallery),getString(R.string.cancel)};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.select);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(items[i].equals(getString(R.string.camera))){

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,0);

                } else if(items[i].equals(getString(R.string.gallery))){

                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent.createChooser(intent,"Select File"),SELECT_FILE);

                } else if(items[i].equals(getString(R.string.cancel))){

                    imageView = crearImagen();
                    ll.addView(imageView);
                    dialogInterface.dismiss();

                }

            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == Activity.RESULT_OK){
            imageView = crearImagen();
            if(requestCode == REQUEST_CAMERA){

                Bundle bundle = data.getExtras();
                final Bitmap bitmap = (Bitmap) bundle.get("data");
                imageView.setImageBitmap(bitmap);


            }
            else if(requestCode == SELECT_FILE){

                Uri selected = data.getData();

                imageView.setImageURI(selected);
                imageView.setTag(selected);


            }
            ll.addView(imageView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.crear, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("nombre",nombre.getText().toString());
            returnIntent.putExtra("descripcion",descripcion.getText().toString());
            returnIntent.putExtra("precio",precio.getText().toString());

            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        }

        return super.onOptionsItemSelected(item);

    }

    public ImageView crearImagen(){
        ImageView button  = new ImageView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(500, LinearLayout.LayoutParams.MATCH_PARENT);
        button.setImageResource(R.mipmap.prueba);
        button.setScaleType(ImageView.ScaleType.FIT_XY);
        button.setLayoutParams(lp);
        return button;

    }


}
