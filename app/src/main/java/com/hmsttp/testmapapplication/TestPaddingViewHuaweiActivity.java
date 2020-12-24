package com.hmsttp.testmapapplication;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import com.huawei.hms.maps.CameraUpdateFactory;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapView;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.LatLngBounds;
import com.huawei.hms.maps.model.PolygonOptions;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class TestPaddingViewHuaweiActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final LatLng CENTRO_CDMX = new LatLng(19.432590, -99.133197);
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    //private SupportMapFragment mSupportMapFragment;
    private HuaweiMap hMap;
    private MapView mMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_map_huawei);

        mMapView = findViewById(R.id.mapView);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(final HuaweiMap huaweiMap) {
        //Timber.d("Map Huawei ready!");
        hMap = huaweiMap;
        //Timber.d("dpToPixels(280) => %d", dpToPixels(280, getResources()));
        mMapView.setPadding(0, 0, 0 , dpToPixels(250, getResources()));
        //hMap.setPadding(0,0,0,dpToPixels(400, getResources()));

        hMap.moveCamera(CameraUpdateFactory.newLatLngZoom(CENTRO_CDMX, 4));

        hMap.addPolygon(new PolygonOptions().addAll(Arrays.asList(
                new LatLng(20.258450000000003, -99.07611000000003),
                new LatLng(20.258450000000003, -94.32027),
                new LatLng(16.1532, -94.32027),
                new LatLng(16.1532, -99.07611000000003))
        ).strokeColor(Color.RED));

        LatLng northeast = new LatLng(20.258450000000003, -94.32027);
        LatLng southwest = new LatLng(16.1532, -99.07611000000003);
        LatLngBounds latLngBounds = new LatLngBounds(southwest, northeast);
        hMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 100));
    }

    public static int dpToPixels(int dp, Resources res) {
        return (int) (res.getDisplayMetrics().density * dp + 0.5f);
    }
}