package com.arondillqs5328.googlemaps.ui.activity.map;

import android.os.Bundle;
import android.view.View;

import com.arondillqs5328.googlemaps.R;
import com.arondillqs5328.googlemaps.custom_map_options.CustomClusterManager;
import com.arondillqs5328.googlemaps.custom_map_options.CustomRenderer;
import com.arondillqs5328.googlemaps.custom_map_options.NumericMarker;
import com.arondillqs5328.googlemaps.presentation.presenter.map.Presenter;
import com.arondillqs5328.googlemaps.presentation.view.map.MapView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, MapView {

    private GoogleMap map;
    private FloatingActionButton refreshFAB;

    private Presenter presenter;
    private LiveData<List<NumericMarker>> markerLiveData;

    private CustomClusterManager clusterManager;
    private CustomRenderer numericRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        presenter = new Presenter(this);
        markerLiveData = presenter.getMarkersLiveData();

        refreshFAB = findViewById(R.id.refreshFAB);
        refreshFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCreateMarkers();
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        clusterManager = new CustomClusterManager(this, map);
        numericRenderer = new CustomRenderer(this, map, clusterManager);
        numericRenderer.setMinClusterSize(1);
        clusterManager.setRenderer(numericRenderer);

        map.setOnCameraIdleListener(clusterManager);
        presenter.onCreateMarkers();

        markerLiveData.observe(this, new Observer<List<NumericMarker>>() {
            @Override
            public void onChanged(List<NumericMarker> markers) {
                clusterManager.clearItems();
                clusterManager.cluster();
                clusterManager.addItems(markers);
            }
        });
    }
}
