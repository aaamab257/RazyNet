package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 6/23/2019.
 */
public class InviteResponse {

    @SerializedName("activationCode")
    private String activationCode;

    @SerializedName("updatedPoints")
    private double updatedPoints;



    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public double getBalance() {
        return updatedPoints;
    }

    public void setBalance(double updatedPoints) {
        this.updatedPoints = updatedPoints;
    }
}
