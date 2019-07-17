package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 7/17/2019.
 */
public class RemainingResponse {

//             "remainDate": "00:00:00",
//             "isSuspended": true

    @SerializedName("remainDate")
    private String remainDate;
    @SerializedName("isSuspended")
    private boolean isSuspended;


    public String getRemainDate() {
        return remainDate;
    }

    public void setRemainDate(String remainDate) {
        this.remainDate = remainDate;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }
}
