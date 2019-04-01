package com.example.rollcallhust.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class GeneralResponse {

    @SerializedName("status")
    @Expose
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess(){
        return this.status == 1;
    }

}
