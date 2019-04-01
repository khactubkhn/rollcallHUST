package com.example.rollcallhust.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Class {

    @SerializedName("classCode")
    @Expose
    private String classCode;
    @SerializedName("size")
    @Expose
    private int size;
    @SerializedName("className")
    @Expose
    private String className;

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
