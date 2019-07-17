package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 7/16/2019.
 */
public class TransferResponse {

    @SerializedName("updatedBalance")
    private double updatedBalance;


    public double getUpdatedBalance() {
        return updatedBalance;
    }

    public void setUpdatedBalance(double updatedBalance) {
        this.updatedBalance = updatedBalance;
    }
}
