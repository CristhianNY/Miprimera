package com.cristhianbonilla.miprimera.view.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.cristhianbonilla.miprimera.adapter.PostAdapterRecyclerView;
import com.cristhianbonilla.miprimera.api.MiPrimeraClient;
import com.cristhianbonilla.miprimera.api.MiPrimeraFirebaseService;
import com.cristhianbonilla.miprimera.api.PostResponse;
import com.cristhianbonilla.miprimera.model.Picture;
import com.cristhianbonilla.miprimera.model.Post;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView pictureRecycler;
    LinearLayoutManager linearLayoutManager;
    ArrayList<Post> post;
    PostAdapterRecyclerView postAdapterRecyclerView;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.app_name), false,view);
        post = new ArrayList<>();
        populateDate();
       pictureRecycler = (RecyclerView) view.findViewById(R.id.pictureRecycler);

        linearLayoutManager = new LinearLayoutManager(getContext());

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pictureRecycler.setLayoutManager(linearLayoutManager);
        postAdapterRecyclerView =
                new PostAdapterRecyclerView(post,R.layout.cardview_picture,getActivity());

        pictureRecycler.setAdapter(postAdapterRecyclerView);

        FloatingActionButton fab =  (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPost newPost = new NewPost();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, newPost)
                .addToBackStack(null).commit();
            }
        });
        return view;


    }

    private void populateDate() {

        MiPrimeraFirebaseService service = (new MiPrimeraClient()).getService();

        Call<PostResponse> postListCall = service.getPostList();// este es el metodo que creamos para obtener todos los metodos
        postListCall.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {

                // aca obtenemos nuestros post desde firebas
                // si nuestra respuesta es exitosa
                if(response.isSuccessful()){

                    PostResponse result = response.body();

                    post.clear();
                    post.addAll(result.getPostsList());
                    postAdapterRecyclerView.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });
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


    @Override
    public void onResume() {
        super.onResume();
        populateDate();
    }
}
