package com.naren.recyclerviewasgrid.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by narendra on 20/01/18.
 */

public class ApiClient {


    public static final String BASE_URL = "https://api.flickr.com/services/";
    private static Retrofit sRetrofit = null;


    public static Retrofit getApiClient() {
        if (sRetrofit==null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL )
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
