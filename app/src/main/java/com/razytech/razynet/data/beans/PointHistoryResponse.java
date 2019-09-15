package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 9/2/2019.
 */
public class PointHistoryResponse {

//               "id": 33,
//            "title": "Recive points",
//            "description": "Increase 1 points for invite new wallet 01128818449",
//            "amount": 1.00,
//            "walletId": "74829c10-4460-42d1-8fd9-d3eec83ad038",
//            "trDateTime": "2019-09-02T12:55:08.146133",
//            "trType": 3,
//            "trDir": 1,
//            "fromWallet": null,
//            "toWallet": null

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("amount")
    private double amount;
    @SerializedName("walletId")
    private String walletId;
    @SerializedName("trDateTime")
    private String trDateTime;
    @SerializedName("trType")
    private int trType;
    @SerializedName("trDir")
    private int trDir;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getTrDateTime() {
        return trDateTime;
    }

    public void setTrDateTime(String trDateTime) {
        this.trDateTime = trDateTime;
    }

    public int getTrType() {
        return trType;
    }

    public void setTrType(int trType) {
        this.trType = trType;
    }

    public int getTrDir() {
        return trDir;
    }

    public void setTrDir(int trDir) {
        this.trDir = trDir;
    }
}
