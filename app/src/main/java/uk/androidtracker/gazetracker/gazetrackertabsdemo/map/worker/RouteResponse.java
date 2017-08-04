package uk.androidtracker.gazetracker.gazetrackertabsdemo.map.worker;

import java.util.List;

/**
 * Created by tosch on 15.07.2017.
 */

public class RouteResponse {
    public List<Route> routes;

    public String getPoints() {
        return this.routes.get(0).overview_polyline.points;
    }

    class Route {
        OverviewPolyline overview_polyline;
    }

    class OverviewPolyline {
        String points;
    }
}
