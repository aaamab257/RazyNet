package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 6/12/2019.
 */
public class RedeemPointsResponse {

    @SerializedName("id")
    private String id;
    @SerializedName("points")
    private String points;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
