package com.example.rollcallhust.presenters;

import com.example.rollcallhust.models.ClassDetailResponse;
import com.example.rollcallhust.models.CreateRollCallResponse;
import com.example.rollcallhust.networks.ApiClient;
import com.example.rollcallhust.views.ClassDetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassDetailPresenterImpl implements ClassDetailPresenter {
    ClassDetailView classDetailView;

    public ClassDetailPresenterImpl(ClassDetailView classDetailView){
        this.classDetailView = classDetailView;
    }

    @Override
    public void loadDetailOfClass(String classCode) {
        ApiClient.getService().getClassDetail(classCode).enqueue(new Callback<ClassDetailResponse>() {
            @Override
            public void onResponse(Call<ClassDetailResponse> call, Response<ClassDetailResponse> response) {
                if(response.isSuccessful()){
                    classDetailView.onLoadClassDetail(response.body());
                }else{
                    classDetailView.onLoadFail();
                }
            }

            @Override
            public void onFailure(Call<ClassDetailResponse> call, Throwable t) {
                classDetailView.onLoadFail();
            }
        });
    }

    @Override
    public void createRollCall(String classCode) {
        ApiClient.getService().createRollCall(classCode).enqueue(new Callback<CreateRollCallResponse>() {
            @Override
            public void onResponse(Call<CreateRollCallResponse> call, Response<CreateRollCallResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        classDetailView.onCreateRollCallSuccess(response.body().getRollCallId());
                    }else{
                        classDetailView.onCreateRollCallFail();
                    }
                }else{
                    classDetailView.onCreateRollCallFail();
                }
            }

            @Override
            public void onFailure(Call<CreateRollCallResponse> call, Throwable t) {
                classDetailView.onCreateRollCallFail();
            }
        });
    }
}
