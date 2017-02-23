package com.cristhianbonilla.miprimera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.cristhianbonilla.miprimera.view.Crear;
import com.cristhianbonilla.miprimera.view.fragment.ContenedorActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goHome(View v){

        Intent intencion = new Intent(this, ContenedorActivity.class);
        startActivity(intencion);



    }
    public void goCreateAccount (View view){

        Intent intencion = new Intent(this, Crear.class);
        startActivity(intencion);

    }

    public void  goToCristhianWeb (View view){

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cristhianbonilla.com/"));
        startActivity(intent);
    }

}
