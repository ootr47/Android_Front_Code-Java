package com.example.naver_map_test;

import com.google.gson.annotations.SerializedName;

public class DataModel {
    @SerializedName("longitude")
    public double[] longitude;

    @SerializedName("latitude")
    public double[] latitude;

    @SerializedName("Branch")
    public String[] Branch;

    @SerializedName("Location")
    public String[] Location;


    public double[] getLongitude() {
        return longitude;
    }

    public double[] getLatitude() {
        return latitude;
    }

    public String[] getBranch() {
        return Branch;
    }

    public String[] getLocation() {
        return Location;
    }
}
