package com.razytech.razynet.data.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by A.Noby on 7/17/2019.
 */
public class RemainingResponse {


    @SerializedName("days")
    private int  days;
    @SerializedName("hours")
    private int  hours;
    @SerializedName("minutes")
    private int  minutes;
    @SerializedName("seconds")
    private int  seconds;
    @SerializedName("isSuspended")
    private boolean isSuspended;
    @SerializedName("waiting")
    private boolean waiting;




    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }
}
