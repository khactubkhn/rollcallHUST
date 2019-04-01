package com.example.rollcallhust.views.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rollcallhust.R;
import com.example.rollcallhust.presenters.LoginPresenter;
import com.example.rollcallhust.presenters.LoginPresenterImpl;
import com.example.rollcallhust.views.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {
    @BindView(R.id.edUsername)
    EditText edUsername;
    @BindView(R.id.edPassword)
    EditText edPassword;

    LoginPresenter loginPresenter;

    ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onLoginSuccess() {
        hideProgressDialog();
        startActivity(
                new Intent(this, HomeActivity.class)
        );
    }

    @Override
    public void onLoginFail() {
        hideProgressDialog();
        Toast.makeText(this, "Xin vui lòng thử lại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if(progressDialog != null){
            progressDialog.hide();
        }
    }

    @OnClick(R.id.btnLogin)
    public void login(){
        hideKeyboard(this);
        String username = edUsername.getText().toString().trim();
        String password = edPassword.getText().toString().trim();
        if(username.length() == 0 || password.length() == 0){
            Toast.makeText(this, "Vui lòng không để trống tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }
        loginPresenter.loginProcess(username, password);
    }

    @Override
    protected void onResume() {
        edPassword.setText("");
        edUsername.setText("");
        super.onResume();
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
