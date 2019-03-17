package com.arondillqs5328.googlemaps.model.map;

import com.arondillqs5328.googlemaps.custom_map_options.NumericMarker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Repository implements MarkersRepository {

    private MarkersCallback callback;

    private final float MIN_LATITUDE = 44.0f;
    private final float MAX_LATITUDE = 52.0f;

    private final float MIN_LONGTITUDE = 23.0f;
    private final float MAX_LONGTITUDE = 40.0f;

    public Repository(MarkersCallback callback) {
        this.callback = callback;
    }

    @Override
    public void creteMarkers() {
        Random random = new Random();
        float latitude;
        float longtitude;
        int weight;
        List<NumericMarker> numericMarkers = new ArrayList<>();

        for (int i = 0; i <= 100; i++) {
            latitude = MIN_LATITUDE + random.nextFloat() * (MAX_LATITUDE - MIN_LATITUDE);
            longtitude = MIN_LONGTITUDE + random.nextFloat() * (MAX_LONGTITUDE - MIN_LONGTITUDE);
            weight = random.nextInt(10) + 1;

            numericMarkers.add(new NumericMarker(latitude, longtitude, weight));
        }

        callback.onSuccess(numericMarkers);
    }
}
