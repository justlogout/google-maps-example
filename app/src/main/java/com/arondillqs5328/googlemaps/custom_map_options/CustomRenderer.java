package com.arondillqs5328.googlemaps.custom_map_options;

import android.content.Context;

import com.arondillqs5328.googlemaps.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;

public class CustomRenderer extends DefaultClusterRenderer<NumericMarker> {

    private final int MARKER = 0;
    private final int CLUSTER = 1;

    private Context context;

    public CustomRenderer(Context context, GoogleMap map, ClusterManager<NumericMarker> clusterManager) {
        super(context, map, clusterManager);
        this.context = context;
    }

    @Override
    public void setMinClusterSize(int minClusterSize) {
        super.setMinClusterSize(minClusterSize);
    }

    @Override
    protected void onBeforeClusterItemRendered(NumericMarker item, MarkerOptions markerOptions) {
        markerOptions.icon(createIcon(MARKER, item.getWeight(), item.getPosition()));
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<NumericMarker> cluster, MarkerOptions markerOptions) {
        markerOptions.icon(createIcon(CLUSTER, getClusterNumber(new ArrayList<>(cluster.getItems())), cluster.getPosition()));
    }

    private int getClusterNumber(List<NumericMarker> list) {
        int number = 0;
        for (NumericMarker numericMarker : list) {
            number += numericMarker.getWeight();
        }
        return number / list.size();
    }

    /**
     * @param type if equals 0 -> marker
     *             if equals 1 -> cluster
     */
    private BitmapDescriptor createIcon(int type, int number, LatLng latLng) {
        IconGenerator iconGenerator = new IconGenerator(context);

        switch (type) {
            case MARKER:
                iconGenerator.setBackground(context.getDrawable(R.drawable.marker));
                iconGenerator.setTextAppearance(R.style.marker);
                iconGenerator.makeIcon(String.valueOf(number));
                break;
            case CLUSTER:
                iconGenerator.setBackground(context.getDrawable(R.drawable.cluster));
                iconGenerator.setTextAppearance(R.style.cluster);
                iconGenerator.makeIcon(String.valueOf(number));
                break;
        }

        return new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(iconGenerator.makeIcon()))
                .position(latLng)
                .getIcon();
    }
}
