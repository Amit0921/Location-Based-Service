package com.amit.lbs.repository;

import com.amit.lbs.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value = """
        SELECT * FROM place
        WHERE ST_DWithin(
            location,
            ST_SetSRID(ST_MakePoint(:lon, :lat), 4326)::geography,
            :radius
        )
        """, nativeQuery = true)
    List<Place> findNearby(double lon, double lat, double radius);

    @Query(value = """
        SELECT * FROM place
        ORDER BY location <-> 
        ST_SetSRID(ST_MakePoint(:lon, :lat), 4326)::geography
        LIMIT 1
        """, nativeQuery = true)
    Place findNearest(double lon, double lat);

    @Query(value = """
        SELECT ST_Distance(
            ST_SetSRID(ST_MakePoint(:lon1, :lat1), 4326)::geography,
            ST_SetSRID(ST_MakePoint(:lon2, :lat2), 4326)::geography
        )
        """, nativeQuery = true)
    Double calculateDistance(
            double lon1, double lat1,
            double lon2, double lat2
    );
}

