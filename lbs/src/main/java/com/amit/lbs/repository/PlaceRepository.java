package com.amit.lbs.repository;

import com.amit.lbs.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value = """
    SELECT * FROM place
    WHERE ST_DWithin(
        location,
        CAST(ST_SetSRID(ST_MakePoint(:lon, :lat), 4326) AS geography),
        :radius
    )
    """, nativeQuery = true)
    List<Place> findNearby(
            @Param("lon") double lon,
            @Param("lat") double lat,
            @Param("radius") double radius
    );

    @Query(value = """
    SELECT * FROM place
    ORDER BY location <-> 
        CAST(ST_SetSRID(ST_MakePoint(:lon, :lat), 4326) AS geography)
    LIMIT 1
    """, nativeQuery = true)
    Place findNearest(
            @Param("lon") double lon,
            @Param("lat") double lat
    );

    @Query(value = """
    SELECT ST_Distance(
        CAST(ST_SetSRID(ST_MakePoint(:lon1, :lat1), 4326) AS geography),
        CAST(ST_SetSRID(ST_MakePoint(:lon2, :lat2), 4326) AS geography)
    )
    """, nativeQuery = true)
    Double calculateDistance(
            @Param("lon1") double lon1,
            @Param("lat1") double lat1,
            @Param("lon2") double lon2,
            @Param("lat2") double lat2
    );

}

