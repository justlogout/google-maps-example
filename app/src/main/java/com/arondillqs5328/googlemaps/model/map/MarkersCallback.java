package com.arondillqs5328.googlemaps.model.map;

import com.arondillqs5328.googlemaps.custom_map_options.NumericMarker;

import java.util.List;

public interface MarkersCallback {

    void onSuccess(List<NumericMarker> markers);

}
