package com.cristhianbonilla.miprimera.api;

import com.cristhianbonilla.miprimera.model.Post;

import java.util.ArrayList;

/**
 * Created by ASUS on 6/03/2017.
 */

public class PostResponse {

        // creamos el que resive y manda post
    ArrayList<Post> postsList = new ArrayList<>();


    public void setPosts(ArrayList<Post> postsList){

        this.postsList = postsList;
    }
    public  ArrayList<Post> getPostsList(){

        return postsList;
    }
}

