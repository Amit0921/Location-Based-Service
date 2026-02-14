package com.amit.lbs.service.impl;

import com.amit.lbs.dto.CreatePlaceRequest;
import com.amit.lbs.dto.DistanceResponse;
import com.amit.lbs.dto.GeoJsonFeature;
import com.amit.lbs.dto.GeoJsonFeatureCollection;
import com.amit.lbs.entity.Place;
import com.amit.lbs.exception.ResourceNotFoundException;
import com.amit.lbs.repository.PlaceRepository;
import com.amit.lbs.service.IPlaceService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements IPlaceService {

    private final PlaceRepository repository;
    private final GeometryFactory geometryFactory;

    public GeoJsonFeature createPlace(CreatePlaceRequest request) {

        Point point = geometryFactory.createPoint(
                new Coordinate(request.getLongitude(), request.getLatitude())
        );

        Place place = Place.builder()
                .name(request.getName())
                .type(request.getType())
                .location(point)
                .build();

        Place saved = repository.save(place);
        return convertToFeature(saved);
    }

    public GeoJsonFeatureCollection findNearby(double lat, double lon, double radius) {

        List<Place> places = repository.findNearby(lon, lat, radius);
        List<GeoJsonFeature> features = places.stream()
                .map(this::convertToFeature)
                .toList();
        return new GeoJsonFeatureCollection(features);
    }

    public GeoJsonFeature findNearest(double lat, double lon) {

        Place place = repository.findNearest(lon, lat);
        if (place == null)
            throw new ResourceNotFoundException("No nearby location found");
        return convertToFeature(place);
    }

    public DistanceResponse calculateDistance(
            double lat1, double lon1,
            double lat2, double lon2) {
        Double distanceInMeters = repository.calculateDistance(
                lon1, lat1, lon2, lat2);
        Double distanceInKm = Math.round(distanceInMeters/1000 * 10000.0) / 10000.0;
        distanceInMeters = Math.round(distanceInMeters * 10000.0) / 10000.0;
        return new DistanceResponse(distanceInMeters, distanceInKm);
    }

    private GeoJsonFeature convertToFeature(Place place) {
        Map<String, Object> geometry = Map.of(
                "type", "Point",
                "coordinates", List.of(
                        place.getLocation().getX(),
                        place.getLocation().getY()
                )
        );

        Map<String, Object> properties = Map.of(
                "id", place.getId(),
                "name", place.getName(),
                "type", place.getType()
        );
        return new GeoJsonFeature("Places", geometry, properties);
    }
}

