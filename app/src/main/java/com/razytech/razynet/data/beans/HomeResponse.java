package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by A.Noby on 7/15/2019.
 */
public class HomeResponse {

    @SerializedName("topWallets")
    private List<ChildResponse> topWallets;
    @SerializedName("topChildrens")
    private List<ChildResponse> topChildrens;
    @SerializedName("wallet")
    private UserResponse wallet;


    public List<ChildResponse> getTopWallets() {
        return topWallets;
    }

    public void setTopWallets(List<ChildResponse> topWallets) {
        this.topWallets = topWallets;
    }

    public List<ChildResponse> getTopChildrens() {
        return topChildrens;
    }

    public void setTopChildrens(List<ChildResponse> topChildrens) {
        this.topChildrens = topChildrens;
    }

    public UserResponse getWallet() {
        return wallet;
    }

    public void setWallet(UserResponse wallet) {
        this.wallet = wallet;
    }
}
