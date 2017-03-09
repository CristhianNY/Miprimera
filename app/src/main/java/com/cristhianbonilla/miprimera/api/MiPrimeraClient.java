package com.cristhianbonilla.miprimera.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ASUS on 6/03/2017.
 */

public class MiPrimeraClient {

    private Retrofit retrofit;
    private final static String FIREBASE_BASE_URL="https://miprimera2-41704.firebaseio.com/";

    public MiPrimeraClient() {
        Gson gson = new GsonBuilder().registerTypeAdapter(PostResponse.class, new PostResponseTypeAdapter()).create();

      retrofit = new Retrofit.Builder().baseUrl(FIREBASE_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    // crear metodo
    public MiPrimeraFirebaseService getService(){

        return retrofit.create(MiPrimeraFirebaseService.class);
    }
}
