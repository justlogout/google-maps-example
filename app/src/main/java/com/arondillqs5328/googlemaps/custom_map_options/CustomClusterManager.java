package com.arondillqs5328.googlemaps.custom_map_options;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.ClusterRenderer;

import java.util.Collection;

public class CustomClusterManager extends ClusterManager<NumericMarker> {

    public CustomClusterManager(Context context, GoogleMap map) {
        super(context, map);
    }

    @Override
    public void clearItems() {
        super.clearItems();
    }

    @Override
    public void addItems(Collection<NumericMarker> items) {
        super.addItems(items);
    }

    @Override
    public void setRenderer(ClusterRenderer<NumericMarker> view) {
        super.setRenderer(view);
    }
}
