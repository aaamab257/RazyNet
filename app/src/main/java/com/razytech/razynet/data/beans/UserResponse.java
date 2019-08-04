package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 4/7/2019.
 */
public class UserResponse {

//
//             "displayName": "new1",
//            "balance": 1000.00,
//            "approved": false,
//            "isStarWallet": false,
//            "childernsCount": 2,
//            "levelsCount": 0,
//            "userImageUrl": null,
//            "cityId": "1",
//            "areaId": "1",
//            "mobileNo": "01128818457",
//            "userId": "55655940-20ee-410e-8e50-8915e0670912",
//            "incrementPermission": true,
//            "token": null

    @SerializedName("displayName")
    private String displayName;
    @SerializedName("balance")
    private double balance;
    @SerializedName("approved")
    private boolean approved;
    @SerializedName("isStarWallet")
    private boolean isStarWallet;
    @SerializedName("userImageUrl")
    private String idImageUrl;
    @SerializedName("childernsCount")
    private int childsCount;
    @SerializedName("levelsCount")
    private int levelsCount;
    @SerializedName("cityId")
    private String cityId;
    @SerializedName("areaId")
    private String areaId;
    @SerializedName("city")
    private String city;
    @SerializedName("area")
    private String area;
    @SerializedName("userId")
    private String userId;
    @SerializedName("incrementPermission")
    private boolean incrementPermission;
    @SerializedName("mobileNo")
    private String phone;
   @SerializedName("token")
    private String Token ;




    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isStarWallet() {
        return isStarWallet;
    }

    public void setStarWallet(boolean starWallet) {
        isStarWallet = starWallet;
    }

    public String getIdImageUrl() {
        return idImageUrl;
    }

    public void setIdImageUrl(String idImageUrl) {
        this.idImageUrl = idImageUrl;
    }

    public int getChildsCount() {
        return childsCount;
    }

    public void setChildsCount(int childsCount) {
        this.childsCount = childsCount;
    }

    public int getLevelsCount() {
        return levelsCount;
    }

    public void setLevelsCount(int levelsCount) {
        this.levelsCount = levelsCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isIncrementPermission() {
        return incrementPermission;
    }

    public void setIncrementPermission(boolean incrementPermission) {
        this.incrementPermission = incrementPermission;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
