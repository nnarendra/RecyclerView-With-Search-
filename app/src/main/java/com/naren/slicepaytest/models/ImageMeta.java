package com.naren.slicepaytest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by narendra on 20/01/18.
 */

public class ImageMeta {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("owner")
    @Expose
    public String owner;
    @SerializedName("secret")
    @Expose
    public String secret;
    @SerializedName("server")
    @Expose
    public String server;
    @SerializedName("farm")
    @Expose
    public Integer farm;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("ispublic")
    @Expose
    public Integer ispublic;
    @SerializedName("isfriend")
    @Expose
    public Integer isfriend;
    @SerializedName("isfamily")
    @Expose
    public Integer isfamily;
}
