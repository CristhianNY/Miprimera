package com.cristhianbonilla.miprimera.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cristhianbonilla.miprimera.R;

public class Crear extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        showToolbar(getResources().getString(R.string.crear_una_aqui),false);
    }

    public void showToolbar (String title, boolean upButton){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
       setSupportActionBar(toolbar);
       getSupportActionBar().setTitle(title);
       getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);



    }
}
