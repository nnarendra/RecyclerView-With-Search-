package com.naren.recyclerviewasgrid.application;

import android.app.Application;

import com.naren.recyclerviewasgrid.network.ApiClient;
import com.naren.recyclerviewasgrid.network.ApiInterface;

/**
 * Created by narendra on 20/01/18.
 */

public class SlicePayTestApplication extends Application {
    public static ApiInterface sApiService;


    @Override
    public void onCreate() {
        super.onCreate();
        sApiService =
                ApiClient.getApiClient().create(ApiInterface.class);
    }
}
