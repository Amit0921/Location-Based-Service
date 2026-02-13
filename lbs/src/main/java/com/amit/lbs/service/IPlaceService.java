package com.amit.lbs.service;

import com.amit.lbs.dto.CreatePlaceRequest;
import com.amit.lbs.dto.DistanceResponse;
import com.amit.lbs.dto.GeoJsonFeature;
import com.amit.lbs.dto.GeoJsonFeatureCollection;
import jakarta.validation.Valid;

public interface IPlaceService {

    GeoJsonFeature createPlace(@Valid CreatePlaceRequest request);

    GeoJsonFeatureCollection findNearby(double lat, double lon, double radius);

    GeoJsonFeature findNearest(double lat, double lon);

    DistanceResponse calculateDistance(double lat1, double lon1, double lat2, double lon2);
}
