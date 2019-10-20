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



   // {"id":20,"name":"TopUp 10","description":"TopUp 10","isActive":true,
    // "published":true,"schemaId":1,"categoryId":2,
    // "price":10.00,"value":0.15,"cost":9.85,
    // "imageUrl":"http://81.29.101.110:5201/Images/Products/TopUp 10-20.png"
    // ,"categoryName":null,"schemaName":null,"createdBy":"Anonymous"}



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


    @SerializedName("price")
    private double price;
    @SerializedName("cost")
    private double cost;


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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
