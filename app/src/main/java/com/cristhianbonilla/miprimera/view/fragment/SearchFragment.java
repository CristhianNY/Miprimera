package com.cristhianbonilla.miprimera.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristhianbonilla.miprimera.R;
import com.cristhianbonilla.miprimera.adapter.PicturesHorizontalRecyclerView;
import com.cristhianbonilla.miprimera.model.PictureHorizontal;
import android.support.v7.widget.StaggeredGridLayoutManager;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        showToolbar("Buscar",true,view);
        // 14 instaciamos recyclreview;

        RecyclerView picturesRecyclerView = (RecyclerView) view.findViewById(R.id.pictureRecycler2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        picturesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        PicturesHorizontalRecyclerView picturesHorizontalRecyclerView =
                new PicturesHorizontalRecyclerView(buildPictures(),R.layout.cardview_lateral,getActivity());
        picturesRecyclerView.setAdapter(picturesHorizontalRecyclerView);
        return view;
        // 11 mostraremos RecyclerView y el toobar
        //13 declaramos el View y lo inflamos
    }

    public ArrayList<PictureHorizontal> buildPictures(){
        ArrayList<PictureHorizontal> pictures = new ArrayList<>();
        pictures.add(new PictureHorizontal("http://www.cantantesmedellin.com/mariachipalenque/mp2.jpg","Mariachi Palenque","4 dias","3 Me gusta"));
        pictures.add(new PictureHorizontal("http://www.cantantesmedellin.com/mariachipalenque/mp2.jpg","Mariachi Palenque","4 dias","10 Me gusta"));
        pictures.add(new PictureHorizontal("http://www.cantantesmedellin.com/mariachipalenque/mp2.jpg","Mariachi Palenque","4 dias","5 Megusta"));
        pictures.add(new PictureHorizontal("http://www.cantantesmedellin.com/mariachipalenque/mp2.jpg","Mariachi Palenque","4 dias","5 Megusta"));
        pictures.add(new PictureHorizontal("http://www.cantantesmedellin.com/mariachipalenque/mp2.jpg","Mariachi Palenque","4 dias","5 Megusta"));
        pictures.add(new PictureHorizontal("http://www.cantantesmedellin.com/mariachipalenque/mp2.jpg","Mariachi Palenque","4 dias","5 Megusta"));



        return  pictures;

    }

//12 creamos el metodo
    public void showToolbar (String title, boolean upButton, View view){

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);



    }

}
