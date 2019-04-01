package com.example.rollcallhust.views;

public interface LoginView {

    void onLoginSuccess();

    void onLoginFail();

    void showProgressDialog();

    void hideProgressDialog();
}
