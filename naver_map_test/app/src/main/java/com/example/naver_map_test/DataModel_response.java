package com.example.naver_map_test;

import com.google.gson.annotations.SerializedName;

public class DataModel_response {
    @SerializedName("Longitude")
    public double[] Longitude;

    @SerializedName("Latitude")
    public double[] Latitude;

    @SerializedName("Branch")
    public String[] Branch;

    @SerializedName("Location")
    public String[] Location;


    public double[] getLongitude() {
        return Longitude;
    }

    public double[] getLatitude() {
        return Latitude;
    }

    public String[] getBranch() {
        return Branch;
    }

    public String[] getLocation() {
        return Location;
    }
}
