package com.amit.lbs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class GeoJsonFeature {
    private String type = "Places";
    private Object geometry;
    private Map<String, Object> properties;
}
