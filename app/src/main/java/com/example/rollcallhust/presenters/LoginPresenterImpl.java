package com.example.rollcallhust.presenters;

import com.example.rollcallhust.managers.AppPreference;
import com.example.rollcallhust.models.LoginResponse;
import com.example.rollcallhust.networks.ApiClient;
import com.example.rollcallhust.views.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenterImpl implements LoginPresenter {
    LoginView loginView;
    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
    }
    @Override
    public void loginProcess(String username, String password) {
        loginView.showProgressDialog();
        ApiClient.getService().login(username, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        LoginResponse loginResponse = response.body();
//                        if(loginResponse.getUser().getStudentCode() != null){
//                            loginView.onLoginFail();
//                            return;
//                        }
                        AppPreference.INSTANCE.setToken(loginResponse.getToken());
                        AppPreference.INSTANCE.setUser(loginResponse.getUser());
                        //System.out.println("TOKEN: " + loginResponse.getToken());
                        loginView.onLoginSuccess();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginView.onLoginFail();
            }
        });
    }
}
