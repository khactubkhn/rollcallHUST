package com.example.rollcallhust.networks;

import com.example.rollcallhust.managers.AppPreference;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

//    public static final String ROOT_URL = "http://192.168.0.103:8080/";
    public static final String ROOT_URL = "http://40.122.108.162:8080/";
    public static final String BASE_URL = ROOT_URL + "api/";
    //		private static final String ROOT_URL = "https://mettingdsd.herokuapp.com/api/";
    private static final String AUTHENTICATION = "authorization";

    private static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient(createLogging(), true))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static HttpLoggingInterceptor createLogging() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    private static OkHttpClient httpClient(HttpLoggingInterceptor logging, boolean checkToken) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = (checkToken && AppPreference.INSTANCE.getToken() != null)
                    ? getRequestToken(original)
                    : getRequest(original);
            return chain.proceed(request);
        });
        httpClient.addInterceptor(logging);
        httpClient.connectTimeout(120, TimeUnit.SECONDS);
        return httpClient.build();
    }

    private static Request getRequestToken(Request original) {
        return original.newBuilder()
                .header(AUTHENTICATION, AppPreference.INSTANCE.getToken())
                .method(original.method(), original.body())
                .build();
    }

    private static Request getRequest(Request original) {
        return original.newBuilder()
                .method(original.method(), original.body())
                .build();
    }

    public static ApiInterface getService() {
        return getClient().create(ApiInterface.class);
    }

}

