package com.example.rollcallhust.networks;

import com.example.rollcallhust.models.ClassDetailResponse;
import com.example.rollcallhust.models.CreateRollCallResponse;
import com.example.rollcallhust.models.GeneralResponse;
import com.example.rollcallhust.models.GetClassResponse;
import com.example.rollcallhust.models.LoginResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("user/login")
    Call<LoginResponse> login(@Field("username") String userName,
                              @Field("password") String password);

    @GET("class/teacher/list")
    Call<GetClassResponse> getClassOfTeacher();

    @GET("class/student/list")
    Call<GetClassResponse> getClassOfStudent();

    @GET("class/student/current")
    Call<GetClassResponse> getCurrentStudentClass();

    @Multipart
    @POST("upload/rollcall")
    Call<GeneralResponse> uploadImageRollCall(
                                     @Part MultipartBody.Part file);

    @GET("user/info")
    Call<GeneralResponse> getInfo();

    @GET("class/detail/{classCode}")
    Call<ClassDetailResponse> getClassDetail(@Path("classCode") String classCode);

    @GET("class/rollcall/create/{classCode}")
    Call<CreateRollCallResponse> createRollCall(@Path("classCode") String classCode);

}

