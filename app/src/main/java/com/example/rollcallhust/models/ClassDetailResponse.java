package com.example.rollcallhust.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassDetailResponse {
    @SerializedName("classCode")
    @Expose
    private String classCode;
    @SerializedName("size")
    @Expose
    private int size;
    @SerializedName("className")
    @Expose
    private String className;
    @SerializedName("term")
    @Expose
    private String term;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("rollCall")
    @Expose
    private List<RollCall> rollCall = null;

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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<RollCall> getRollCall() {
        return rollCall;
    }

    public void setRollCall(List<RollCall> rollCall) {
        this.rollCall = rollCall;
    }
}