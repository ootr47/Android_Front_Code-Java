package com.example.naver_map_test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/test")
    Call<DataModel> getData();

}
