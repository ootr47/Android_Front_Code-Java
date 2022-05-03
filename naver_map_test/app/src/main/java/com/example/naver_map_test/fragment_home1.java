package com.example.naver_map_test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;

public class fragment_home1 extends Fragment implements OnMapReadyCallback {

    //지도 제어를 위한 mapView 변수
    private MapView mapView;
    private static NaverMap naverMap;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public fragment_home1() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment fragment_home1.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static fragment_home1 newInstance(String param1, String param2) {
//        fragment_home1 fragment = new fragment_home1();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getActivity().getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);

        if(mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        // onMapReady 함수를 인자로 callback함
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        // 시작시 지도 위치 설정
        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(36.82970416, 127.1839876),
                14
        );
        naverMap.setCameraPosition(cameraPosition);


        setMarker(37.5670135, 126.9783740);
        setMarker(36.69051516, 126.577804);
        setMarker(36.82970416, 127.1839876);
        setMarker(35.56321329, 129.3332209);

        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, true);

    }

    public void setMarker(double latitude, double longitude) {
        Marker marker = new Marker();
        marker.setPosition(new LatLng(latitude, longitude));
        // 아이콘 이미지 설정
//        marker.setIcon(OverlayImage.fromResource(R.drawable.ic_launcher_foreground));

        // 마커 사이즈 설정
        setMarkerSize(marker, 80, 100);
        marker.setMap(naverMap);
    }

    public void setMarkerSize(Marker marker, int width, int height) {
        marker.setWidth(width);
        marker.setHeight(height);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home1, container, false);
    }

}