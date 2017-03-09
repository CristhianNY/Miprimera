package com.cristhianbonilla.miprimera.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ASUS on 6/03/2017.
 */

public interface MiPrimeraFirebaseService {
// implementamos nuestro servicio

    //tomaremos los endpoing y tendremos los get en este caso sera uno y sera una interface


    @GET("post.json")
    Call<PostResponse> getPostList();
}
