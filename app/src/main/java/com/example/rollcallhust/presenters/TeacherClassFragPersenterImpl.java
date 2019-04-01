package com.example.rollcallhust.presenters;

import com.example.rollcallhust.models.GetClassResponse;
import com.example.rollcallhust.networks.ApiClient;
import com.example.rollcallhust.views.TeacherClassFragmentView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherClassFragPersenterImpl implements TeacherClassFragPersenter {
    TeacherClassFragmentView teacherClassFragmentView;

    public TeacherClassFragPersenterImpl(TeacherClassFragmentView teacherClassFragmentView){
        this.teacherClassFragmentView = teacherClassFragmentView;
    }
    @Override
    public void getListClass() {
        ApiClient.getService().getClassOfTeacher().enqueue(new Callback<GetClassResponse>() {
            @Override
            public void onResponse(Call<GetClassResponse> call, Response<GetClassResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        teacherClassFragmentView.onLoadedClassList(response.body().getClasses());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetClassResponse> call, Throwable t) {

            }
        });
    }
}
