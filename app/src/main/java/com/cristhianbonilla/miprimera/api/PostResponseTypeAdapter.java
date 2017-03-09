package com.cristhianbonilla.miprimera.api;

import com.cristhianbonilla.miprimera.model.Post;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ASUS on 6/03/2017.
 */

public class PostResponseTypeAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter out, Object value) throws IOException {

    }

    @Override
    public PostResponse read(JsonReader in) throws IOException {


    final PostResponse response = new PostResponse();
        ArrayList<Post> postList = new ArrayList<Post>();
        in.beginObject();

        while (in.hasNext()){
            Post post = null;
            try {
                post = readPost(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            postList.add(post);
        }
        in.endObject();

        response.setPosts(postList);
        return response;

    }
    public Post readPost(JsonReader reader) throws Exception{

        Post post = new Post();
        reader.nextName(); // vamos a operar sobre todos los elementos que estamos obteniendo en nuestro firebase
        reader.beginObject();
        while (reader.hasNext()) {// hasta que opere en todo esto

            String next = reader.nextName();

            switch (next){
                case "author":
                    post.setAuthor(reader.nextString());
                    break;
                case "imageUrl":
                    post.setImgeUrl(reader.nextString());
                    break;
                case "timesMapCreated":
                    post.setTimesMapCreated(reader.nextDouble());
                    break;


            }
        }

        reader.endObject();
        return post;

    }
}
