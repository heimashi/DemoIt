package com.sw.retrofit.hello;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static final String BASE_URL = "http://45.62.98.137/";

    private static RetrofitClient retrofitClient;

    private Retrofit retrofit;

    public Retrofit getRetrofit(){
        return retrofit;
    }

    public static RetrofitClient getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient();
            retrofitClient.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitClient;
    }
}
