package com.example.naver_map_test;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitClient {
    //Retrofit BaseUrl
    public static final String BASE_URL = "http://10.0.2.2:4000";


    public Retrofit getClient(Retrofit retrofit) {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        }
        return retrofit;
    }
}
