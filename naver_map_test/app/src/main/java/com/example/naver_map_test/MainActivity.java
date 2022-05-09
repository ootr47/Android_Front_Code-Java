package com.example.naver_map_test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.Arrays;

import nl.joery.animatedbottombar.AnimatedBottomBar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    FragmentManager fragmentManager;
    AnimatedBottomBar animatedBottomBar;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        // 툴바 활성화
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_other);
        // 툴바에 적힐 제목
        actionBar.setTitle("");
        actionBar.setHomeButtonEnabled(true);

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

        Retrofit retrofit = null;
        retrofitClient retrofitClient = new retrofitClient();
        retrofit = retrofitClient.getClient(retrofit);

            // Instance for interface
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Call<DataModel_response> call = apiInterface.getData();

        call.enqueue(new Callback<DataModel_response>() {
                @Override
                public void onResponse(@NonNull Call<DataModel_response> call, @NonNull Response<DataModel_response> response) {
                    // Checking for the response
                    if(response.code() != 200) {
                        System.out.println("Checking the connection");
                        return;
                    }
                    // Get the data
                    // Check response.body() is not null
                    assert response.body() != null;
                    String[] Branch = response.body().Branch;
                    String[] Location = response.body().Location;
                    double[] Latitude = response.body().Latitude;
                    double[] Longitude = response.body().Longitude;
                    System.out.println(response.body());
                    System.out.println(Arrays.toString(Branch));
                    System.out.println(Arrays.toString(Location));
                    System.out.println(Arrays.toString(Latitude));
                    System.out.println(Arrays.toString(Longitude));
                }
                @Override
            public void onFailure(@NonNull Call<DataModel_response> call, @NonNull Throwable t) {
                Log.e("Error connection", t + call.toString());
            }
        });
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        switch (id) {
//
//
//        }
//    };

}