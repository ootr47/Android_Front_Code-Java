package com.example.naver_map_test;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.naver.maps.map.NaverMap;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class coordinate_timer extends TimerTask{
    public void run(@NonNull NaverMap naverMap) {
        // HTTP 전송 필요
        // 현재 위치 좌표를 받아오는 변수
        naverMap.addOnLocationChangeListener(location -> {
            System.out.println(LocalDateTime.now() + " : Executing the task from "
                    + Thread.currentThread().getName());
        });
    }

    @Override
    public void run() {

    }

    // 주기적인 좌표 업데이트를 위한 타이머 객체
//    TimerTask task = new TimerTask() {
//        @Override
//        public void run() {
////                System.out.println(LocalDateTime.now() + " : Executing the task from "
////                        + Thread.currentThread().getName());
//            setLocationMode(naverMap);
//        }
//    };
//    Timer timer = new Timer("Timer");
//    long delay = 3000L;
//    long period = 1000L;
//
//        System.out.println(LocalDateTime.now() + " : Scheduling....");
//        timer.scheduleAtFixedRate(task, delay, period);
//
}
