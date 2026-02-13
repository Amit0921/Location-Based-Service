package com.amit.lbs.controller;

import com.amit.lbs.dto.CreatePlaceRequest;
import com.amit.lbs.dto.DistanceResponse;
import com.amit.lbs.dto.GeoJsonFeature;
import com.amit.lbs.dto.GeoJsonFeatureCollection;
import com.amit.lbs.service.IPlaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/places")
@RequiredArgsConstructor
@Validated
public class PlaceController {

    private final IPlaceService placeService;

    @PostMapping
    public ResponseEntity<GeoJsonFeature> create(
            @Valid @RequestBody CreatePlaceRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(placeService.createPlace(request));
    }

    @GetMapping("/nearby")
    public GeoJsonFeatureCollection nearby(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam double radius) {

        return placeService.findNearby(lat, lon, radius);
    }

    @GetMapping("/nearest")
    public GeoJsonFeature nearest(
            @RequestParam double lat,
            @RequestParam double lon) {

        return placeService.findNearest(lat, lon);
    }

    @GetMapping("/distance")
    public DistanceResponse distance(
            @RequestParam double lat1,
            @RequestParam double lon1,
            @RequestParam double lat2,
            @RequestParam double lon2) {

        return placeService.calculateDistance(lat1, lon1, lat2, lon2);
    }
}
