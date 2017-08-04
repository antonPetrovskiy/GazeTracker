package uk.androidtracker.gazetracker.gazetrackertabsdemo.map.worker;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by tosch on 15.07.2017.
 */

public interface RouteApi {
    @GET("/maps/api/directions/json")
    RouteResponse getRoute(
            @Query(value = "origin", encodeValue = false) String position,
            @Query(value = "destination", encodeValue = false) String destination,
            @Query("sensor") boolean sensor,
            @Query("language") String language);
}


