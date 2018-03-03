package com.naren.recyclerviewasgrid.managers;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.naren.recyclerviewasgrid.application.SlicePayTestApplication;
import com.naren.recyclerviewasgrid.models.ImageMeta;
import com.naren.recyclerviewasgrid.utils.GSONUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by narendra on 20/01/18.
 */

public class ImageLoadingManager {

    // member variable
    private static final String TAG = "ImageLoadingManager";
    private static ImageLoadingManager sSingleton;

    public static synchronized ImageLoadingManager singleton() {
        if (sSingleton == null) {
            sSingleton = new ImageLoadingManager();
        }

        return sSingleton;
    }

    public void getImageList(final ImagesListListener listener) {


        /**
         *
         */

        Call<JsonElement> call = SlicePayTestApplication.sApiService.getImageList("flickr.interestingness.getList", "9f89151d82e427401680cd48dd2d5cf5",
                30, 1, "json", 1);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                Log.i(TAG, response.toString());
                // response.

                if (GSONUtility.getStringSafe(response.body().getAsJsonObject(), "stat").equals("ok")) {
                    List<ImageMeta> imageList = new ArrayList<>();
                    JsonObject photos = GSONUtility.getJsonObjectSafe(response.body().getAsJsonObject(), "photos");
                    JsonArray photoArray = GSONUtility.getJsonArraySafe(photos, "photo");
                    for (JsonElement json : photoArray) {
                        ImageMeta imageMeta = new Gson().fromJson(json, ImageMeta.class);
                        imageList.add(imageMeta);
                    }

                    listener.onSuccess(imageList);

                } else {
                    listener.onFailure("Oops! Something went wrong please try again later");
                    Log.i(TAG, "Error");

                }

            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                // Log error here since request failed
                listener.onFailure(t.getMessage());
                t.printStackTrace();
            }
        });


    }


    public interface ImagesListListener {
        void onSuccess(List<ImageMeta> imagesList);

        void onFailure(String errorMsg);
    }
}
