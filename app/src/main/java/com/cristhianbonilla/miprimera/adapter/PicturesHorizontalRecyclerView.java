package com.cristhianbonilla.miprimera.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cristhianbonilla.miprimera.R;
import com.cristhianbonilla.miprimera.model.PictureHorizontal;
import com.cristhianbonilla.miprimera.view.PicturyDeatailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ASUS on 22/02/2017.
 */


// 3 sicronizamos el adapter con un arrayList y se encargara de reutulizar y pasamos una coleccion de objetos
    // 4 creamos el modelo
    // pasamos la coleccion
public class PicturesHorizontalRecyclerView extends RecyclerView.Adapter<PicturesHorizontalRecyclerView.pictureViewHolderHorizontal> {

// 5 implemantamos los metodos

    // 6ponemos el arreglo de datos que vamos a manejar que sera el elemento que venga de internet
    private ArrayList<PictureHorizontal> pictures;
    private int resouce; // sera nuestro cardview
    private Activity activity; // queremos pasar como parametros la actividad desde donde se esta llamando

    //7 creamos un contructor con los tres elementos


    public PicturesHorizontalRecyclerView(ArrayList<PictureHorizontal> pictures, int resouce, Activity activity) {
        this.pictures = pictures;
        this.resouce = resouce;
        this.activity = activity;
    }

    @Override
    public pictureViewHolderHorizontal onCreateViewHolder(ViewGroup parent, int viewType) {

        // 8 inicializara nuestra clase pictureviewholderHorizontal ,, pasaremos el layout de cada card

        // esto funciona como un iframe se inserta

        View view = LayoutInflater.from(parent.getContext()).inflate(resouce,parent,false);

        return new pictureViewHolderHorizontal(view);
    }

    @Override
    public void onBindViewHolder(pictureViewHolderHorizontal holder, int position) {

        // 9 aca se trabaja con la lista y se hace el paso de datos
        // asignamos los datos a los cardviews
        PictureHorizontal picture = pictures.get(position);
        holder.usernameCard.setText(picture.getUserName());
        holder.timeCard.setText(picture.getTime());
        holder.likeNumberCard.setText(picture.getLike_number());
        Picasso.with(activity).load(picture.getPicture()).into(holder.pictureCard);

        holder.pictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PicturyDeatailActivity.class);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.transitionname_picture)).toBundle());

                }else{

                    activity.startActivity(intent);
                }
            }
        });



    }

    @Override
    public int getItemCount() {

        // 10 solo devuelve un item

        return pictures.size();
    }

    public class pictureViewHolderHorizontal extends RecyclerView.ViewHolder{// esta clase se concentra en un card a la vez
// 1
                private ImageView pictureCard;
                private TextView timeCard;
                private TextView usernameCard;
                private TextView likeNumberCard;


                public pictureViewHolderHorizontal(View itemView) {
                    super(itemView);
//2 instanciamos o asignamos los elementos del card a nuestras variables private
                    pictureCard = (ImageView) itemView.findViewById(R.id.imagenCard);
                    usernameCard   = (TextView) itemView.findViewById(R.id.texto_username);
                    timeCard       = (TextView) itemView.findViewById(R.id.timeCard);
                    likeNumberCard = (TextView) itemView.findViewById(R.id.likeNumberCard);
                }
            }


}
