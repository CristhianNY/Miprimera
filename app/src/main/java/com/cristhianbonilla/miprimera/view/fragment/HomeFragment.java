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
import com.cristhianbonilla.miprimera.adapter.PictureAdapterRecyclerView;
import com.cristhianbonilla.miprimera.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.app_name), false,view);
        RecyclerView pictureRecycler = (RecyclerView) view.findViewById(R.id.pictureRecycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pictureRecycler.setLayoutManager(linearLayoutManager);
        PictureAdapterRecyclerView pictureAdapterRecyclerView =
                new PictureAdapterRecyclerView(buidPictures(),R.layout.cardview_picture,getActivity());

        pictureRecycler.setAdapter(pictureAdapterRecyclerView);
        return view;


    }

    public ArrayList<Picture> buidPictures(){
        ArrayList<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://www.cantantesmedellin.com/mariachipalenque/mp2.jpg","Mariachi Palenque","4 dias","3 Me gusta"));
        pictures.add(new Picture("http://www.cantantesmedellin.com/mariachipalenque/mp2.jpg","Mariachi Palenque","4 dias","10 Me gusta"));
        pictures.add(new Picture("http://www.cantantesmedellin.com/mariachipalenque/mp2.jpg","Mariachi Palenque","4 dias","5 Megusta"));

        return pictures;
    }

    public void showToolbar (String title, boolean upButton, View view){

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar2);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);



    }





}
