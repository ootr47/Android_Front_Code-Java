package com.example.naver_map_test;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.Objects;

public class fragment_home1 extends Fragment implements OnMapReadyCallback {

    //지도 제어를 위한 mapView 변수
    private MapView mapView;     // View를 사용하여 naver map을 출력했다면
    private static NaverMap naverMap;  // Fragment를 이용하여 naver map을 출력 했다면


    // 지도상에 현재 위치를 받아오는 변수
    private FusedLocationSource locationSource;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

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

        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map);

        if(mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        // onMapReady 함수를 인자로 callback함
        mapFragment.getMapAsync(this);
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
    }



    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        fragment_home1.naverMap = naverMap;

        naverMap.setCameraPosition(getCameraPosition(naverMap, 36.69051516, 126.577804));

        setMarker(37.5670135, 126.9783740);
        setMarker(36.69051516, 126.577804);
        setMarker(36.82970416, 127.1839876);
        setMarker(35.56321329, 129.3332209);

        naverMap.setLayerGroupEnabled(NaverMap.LAYER_GROUP_BUILDING, true);

        naverMap.setLocationSource(locationSource);

        ActivityCompat.requestPermissions(requireActivity(), PERMISSIONS,LOCATION_PERMISSION_REQUEST_CODE);

        // 현재 위치 표시 설정
        setLocationMode(naverMap);
        // Map UI 설정 함수
        setMapUi(naverMap);

        // 오버레이 설정
//        setOverlay(naverMap);
    }

    public CameraPosition getCameraPosition(NaverMap naverMap, double latitude, double longitude) {
        // 시작시 지도 위치 설정
        return new CameraPosition(
                new LatLng(latitude, longitude),
                18
        );
    }

    public void setOverlay(NaverMap naverMap) {
        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
        locationOverlay.setVisible(true);
    }

    public void setLocationMode(@NonNull NaverMap naverMap) {
        // 현재 위치 좌표를 받아오는 변수
        naverMap.addOnLocationChangeListener(location -> {
//            Toast.makeText(getContext(), location.getLatitude() + "," + location.getLongitude(), Toast.LENGTH_SHORT).show();

        });
    }

    public void setMapUi(@NonNull NaverMap naverMap) {
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setCompassEnabled(false);
        uiSettings.setScaleBarEnabled(false);
        uiSettings.setLocationButtonEnabled(true);

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
    public void setMarkerSize(@NonNull Marker marker, int width, int height) {
        marker.setWidth(width);
        marker.setHeight(height);
    }

    /*
        현재 위치를 받아오기 위한 위치 권한 함수
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] granResults) {

//        Activty에서 권한요청
        if(locationSource.onRequestPermissionsResult(requestCode, permissions, granResults)) {
            if(!locationSource.isActivated()) {  // 권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
                return;
            } else {
                naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, granResults);
    }

//    private final ActivityResultLauncher<String> mPermissionResult = registerForActivityResult(
//            new ActivityResultContracts.RequestPermission(),
//            new ActivityResultCallback<Boolean>() {
//                @Override
//                public void onActivityResult(Boolean result) {
//                    if(!result) {
//                        naverMap.setLocationTrackingMode(LocationTrackingMode.None);
//                        Log.e("naverMap TrackingMode None", "onActivityResult: PERMISSION DENIED");
//                    } else {
//                        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
//                        Log.e("naverMap TrackingMode Follow", "onActivityResult: PERMISSION GRANTED");
//                    }
//                }
//            }
//
//    );

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        mPermissionResult.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
//         Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home1, container, false);
    }

}