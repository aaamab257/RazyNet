package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 6/17/2019.
 */
public class ChildResponse {



    @SerializedName("walletId")
    private String walletId;
    @SerializedName("displayName")
    private String name;
    @SerializedName("userId")
    private String userId;
    @SerializedName("parentId")
    private String parentId;
    @SerializedName("mobileNo")
    private String mobileNo;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("balance")
    private double balance;
    @SerializedName("approved")
    private boolean approved;
    @SerializedName("isMoved")
    private boolean isMoved;
    @SerializedName("childCounts")
    private int childCounts;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getChildCounts() {
        return childCounts;
    }

    public void setChildCounts(int childCounts) {
        this.childCounts = childCounts;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
