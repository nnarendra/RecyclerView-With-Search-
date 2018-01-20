package com.naren.slicepaytest.application;

import android.app.Application;

import com.naren.slicepaytest.network.ApiClient;
import com.naren.slicepaytest.network.ApiInterface;

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
