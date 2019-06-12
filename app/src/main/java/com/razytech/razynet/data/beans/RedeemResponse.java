package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 6/12/2019.
 */
public class RedeemResponse {

    @SerializedName("id")
    private String id;
    @SerializedName("companyname")
    private String companyname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
