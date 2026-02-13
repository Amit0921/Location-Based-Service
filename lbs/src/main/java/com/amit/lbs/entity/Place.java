package com.amit.lbs.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import org.locationtech.jts.geom.Point;

@Data
@Builder
@Entity
@Table(name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String type;

    @Column(columnDefinition = "geography(Point,4326)", nullable = false)
    private Point location;
}
