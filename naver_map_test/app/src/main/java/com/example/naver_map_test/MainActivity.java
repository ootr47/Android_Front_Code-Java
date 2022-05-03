package com.example.naver_map_test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    FragmentManager fragmentManager;
    AnimatedBottomBar animatedBottomBar;

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
    }
}