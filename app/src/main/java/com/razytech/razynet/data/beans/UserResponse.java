package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 4/7/2019.
 */
public class UserResponse {

//"wallet":{"displayName":"Test5",
// "balance":160.03,"approved":false,
// "isStarWallet":false,"childernsCount":10,
// "levelsCount":0,
// "userImageUrl":"http://81.29.101.110:5201/Images/Wallets/596ecf39-55cb-47
// 3d-af55-460dd5b6617d.jpg","cityId":1,"city":"Cairo",
// "areaId":2,"area":"New Cairo","mobileNo":"01233445569",
// "userId":"6d55a8c8-78d8-4e4e-b992-8cfbac16cb0f","isMoved"
// :true,"token":null,"canCredit":false,"gainPoints":0.00}


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

    @SerializedName("gainPoints")
    private double gainPoints;
    @SerializedName("canCredit")
    private boolean canCredit;



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

    public double getGainPoints() {
        return gainPoints;
    }

    public void setGainPoints(double gainPoints) {
        this.gainPoints = gainPoints;
    }

    public boolean isCanCredit() {
        return canCredit;
    }

    public void setCanCredit(boolean canCredit) {
        this.canCredit = canCredit;
    }
}
