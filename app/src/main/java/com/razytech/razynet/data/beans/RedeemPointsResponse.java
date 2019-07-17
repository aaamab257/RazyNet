package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 6/12/2019.
 */
public class RedeemPointsResponse {

//              "id": 1,
//              "name": "Test",
//              "description": "sdfff",
//              "isActive": true,
//              "value": 10,
//              "servingId": 2,
//              "imageUrl": ""

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("isActive")
    private boolean isActive;
    @SerializedName("value")
    private double value;
    @SerializedName("servingId")
    private int servingId;
    @SerializedName("imageUrl")
    private String imageUrl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getServingId() {
        return servingId;
    }

    public void setServingId(int servingId) {
        this.servingId = servingId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
