package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 7/18/2019.
 */
public class UploadNidResponse {

    @SerializedName("idImageUrl")
    private String idImageUrl;


    public String getIdImageUrl() {
        return idImageUrl;
    }

    public void setIdImageUrl(String idImageUrl) {
        this.idImageUrl = idImageUrl;
    }
}
