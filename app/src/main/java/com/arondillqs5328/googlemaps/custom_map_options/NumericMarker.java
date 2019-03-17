package com.arondillqs5328.googlemaps.custom_map_options;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class NumericMarker implements ClusterItem {

    private float latitude;
    private float longtitude;
    private int weight;

    public NumericMarker(float latitude, float longtitude, int weight) {
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.weight = weight;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(latitude, longtitude);
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getSnippet() {
        return "";
    }

    public int getWeight() {
        return weight;
    }
}
