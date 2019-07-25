package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 7/22/2019.
 */
public class RedeemUpdateResponse {


    @SerializedName("updatedPoints")
    private double updatedPoint;


    public double getUpdatedPoint() {
        return updatedPoint;
    }

    public void setUpdatedPoint(double updatedPoint) {
        this.updatedPoint = updatedPoint;
    }
}
