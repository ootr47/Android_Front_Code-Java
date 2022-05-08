package com.example.naver_map_test;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/gs25")
    Call<DataModel_response> getData();

}
