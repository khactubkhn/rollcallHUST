package com.example.rollcallhust.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetClassResponse {
    @SerializedName("classes")
    @Expose
    private List<Class> classes = null;
    @SerializedName("status")
    @Expose
    private int status;

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess(){
        return status == 1;
    }
}
