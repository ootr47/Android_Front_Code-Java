package com.example.naver_map_test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

import nl.joery.animatedbottombar.AnimatedBottomBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    FragmentManager fragmentManager;
    AnimatedBottomBar animatedBottomBar;

    //Retrofit BaseUrl
    public static final String BASE_URL = "http://10.0.2.2:4000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animatedBottomBar = findViewById(R.id.bottom_bar);

        if(savedInstanceState == null) {
            animatedBottomBar.selectTabById(R.id.home1, true);
            fragmentManager = getSupportFragmentManager();
            fragment_home1 homeFragment = new fragment_home1();
            fragmentManager.beginTransaction().replace(R.id.menu_frame_layout, homeFragment)
                    .commit();
        }

        animatedBottomBar.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int lastIndex, @Nullable AnimatedBottomBar.Tab lastTab, int newIndex, @NonNull AnimatedBottomBar.Tab newTab) {
                Fragment fragment = null;

                int id = newTab.getId();
                if(id == R.id.home1) {
                    System.out.println(id);
                    Log.d("bottom_bar", "Selected index: home1, title: home1");
                    fragment = new fragment_home1();
                } else if (id == R.id.home2) {
                    System.out.println(id);
                    Log.d("bottom_bar", "Selected index: home2, title: home2");
                    fragment = new fragment_home2();
                } else if (id == R.id.home3) {
                    System.out.println(id);
                    Log.d("bottom_bar", "Selected index: home3, title: home3");
                    fragment = new fragment_home3();
                }

                if(fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.menu_frame_layout, fragment)
                            .commit();
                } else {
                    Log.e(TAG, "Error in creatring Fragment");
                }

            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }
        });

        //Retrofit Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Instance for interface
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Call<DataModel> call = apiInterface.getData();

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NonNull Call<DataModel> call, @NonNull Response<DataModel> response) {
                // Checking for the response
                if(response.code() != 200) {
                    System.out.println("Checking the connection");
                    return;
                }

                // Get the data

                // Check response.body() is not null
                assert response.body() != null;
                double[] latitude = response.body().latitude;
                double[] longitude = response.body().longitude;
                String[] Branch = response.body().Branch;
                String[] Location = response.body().Location;
                Log.i("response.body", response.body().toString());


            }

            @Override
            public void onFailure(@NonNull Call<DataModel> call, @NonNull Throwable t) {
                Log.e("Error connection", t + call.toString());
            }
        });


    }
}