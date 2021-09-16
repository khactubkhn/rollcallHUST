package com.example.rollcallhust.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateRollCallResponse {

    @SerializedName("rollCallId")
    @Expose
    private int rollCallId;
    @SerializedName("status")
    @Expose
    private int status;

    public int getRollCallId() {
        return rollCallId;
    }

    public void setRollCallId(int rollCallId) {
        this.rollCallId = rollCallId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess(){
        return  status == 1;
    }

}
