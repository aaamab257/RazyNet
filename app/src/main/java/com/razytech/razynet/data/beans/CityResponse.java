package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 6/23/2019.
 */
public class CityResponse {

       @SerializedName("id")
       private int id;
       @SerializedName("cityName")
       private String cityName;
       @SerializedName("countryId")
       private int countryId;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public int getCountryId() {
        return countryId;
    }
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
