package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 6/23/2019.
 */
public class VerifyCodeResponse {

//          "phone": "01128818457",
//                  "token": "eyJhbGciO"

    @SerializedName("phone")
    private String phone;
    @SerializedName("token")
    private String token;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
