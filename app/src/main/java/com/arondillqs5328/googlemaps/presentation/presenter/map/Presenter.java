package com.arondillqs5328.googlemaps.presentation.presenter.map;

import com.arondillqs5328.googlemaps.custom_map_options.NumericMarker;
import com.arondillqs5328.googlemaps.model.map.MarkersCallback;
import com.arondillqs5328.googlemaps.model.map.Repository;
import com.arondillqs5328.googlemaps.presentation.view.map.MapView;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class Presenter implements MapPresenter, MarkersCallback {

    private MutableLiveData<List<NumericMarker>> markersLiveData = new MutableLiveData<>();
    private Repository repository;
    private MapView view;

    public Presenter(MapView view) {
        this.view = view;
        repository = new Repository(this);

        List<NumericMarker> markers = new ArrayList<>();
        markersLiveData.setValue(markers);
    }

    public LiveData<List<NumericMarker>> getMarkersLiveData() {
        return markersLiveData;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onCreateMarkers() {
        if (view != null) {
            repository.creteMarkers();
        }
    }

    @Override
    public void onSuccess(List<NumericMarker> markers) {
        markersLiveData.setValue(markers);
    }
}
