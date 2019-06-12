package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 4/7/2019.
 */
public class UserResponse {
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("email")
    private String email;
    @SerializedName("username")
    private String username;
    @SerializedName("image")
    private String image;
    @SerializedName("phone")
    private int phone;
    @SerializedName("Token")
    private String Token ;

    public  String DeliveryAddress =  "";


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

}
