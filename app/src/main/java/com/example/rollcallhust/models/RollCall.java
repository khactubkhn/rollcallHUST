package com.example.rollcallhust.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RollCall {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rollCallId")
    @Expose
    private int rollCallId;
    @SerializedName("time")
    @Expose
    private String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRollCallId() {
        return rollCallId;
    }

    public void setRollCallId(int rollCallId) {
        this.rollCallId = rollCallId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
