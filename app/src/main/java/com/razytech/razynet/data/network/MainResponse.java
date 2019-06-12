package com.razytech.razynet.data.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 4/7/2019.
 */
public class MainResponse<T> {

    @SerializedName("success")
    public boolean status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public T data;
}

