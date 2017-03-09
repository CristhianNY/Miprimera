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
import com.cristhianbonilla.miprimera.model.Picture;
import com.cristhianbonilla.miprimera.model.Post;
import com.cristhianbonilla.miprimera.view.PicturyDeatailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ASUS on 12/02/2017.
 */

public class PostAdapterRecyclerView extends RecyclerView.Adapter<PostAdapterRecyclerView.PictureViewHolder> {

    // 1 definimos todos los views que componen al cardView
    private ArrayList<Post> posts;
    private int resource;
    private Activity activity;
    //ene 1

    public PostAdapterRecyclerView(ArrayList<Post> post, int resource, Activity activity){

        this.posts = post;
        this.resource = resource;
        this.activity = activity;

    }
    @Override
    public PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);

        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureViewHolder holder, int position) {

        // recorremos la lista  y vamos generando tarjetas
        Post post = posts.get(position);
        holder.usernameCard.setText(post.getAuthor());
        holder.timeCard.setText(post.getRelativeTimeStamp());
        holder.likeNumberCard.setText("1");
        Picasso.with(activity).load(post.getImgeUrl()).into(holder.pictureCard);

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


        return posts.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{


        // declaramos los items del cardview
        private ImageView pictureCard;
        private TextView  usernameCard;
        private TextView timeCard;
        private TextView likeNumberCard;


        public PictureViewHolder(View itemView) {
            super(itemView);
            pictureCard    = (ImageView) itemView.findViewById(R.id.imagenCard);
            usernameCard   = (TextView) itemView.findViewById(R.id.texto_username);
            timeCard       = (TextView) itemView.findViewById(R.id.timeCard);
            likeNumberCard = (TextView) itemView.findViewById(R.id.likeNumberCard);

        }
        // esta clase trabajara los views que compones los cardsview


    }
}
