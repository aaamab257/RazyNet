package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 6/18/2019.
 */

public class PointsResponse {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("points")
    private String points;
    @SerializedName("avapoints")
    private String avapoints;
    @SerializedName("isin")
    private boolean isin;
    @SerializedName("date")
    private String date;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPoints() {
        return points;
    }
    public void setPoints(String points) {
        this.points = points;
    }
    public String getAvapoints() {
        return avapoints;
    }
    public void setAvapoints(String avapoints) {
        this.avapoints = avapoints;
    }
    public boolean isIsin() {
        return isin;
    }
    public void setIsin(boolean isin) {
        this.isin = isin;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
