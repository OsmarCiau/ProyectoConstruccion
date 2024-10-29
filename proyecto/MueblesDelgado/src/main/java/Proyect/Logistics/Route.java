package Proyect.Logistics;

import java.time.LocalTime;

public class Route {
    private int routeId = 0;
    private String originLocation = null;
    private String destination = null;
    private float distance = 0.0f;
    private LocalTime estimatedTime = LocalTime.of(0, 0);

      private static final RouteValidationUtils routeValidator = new RouteValidationUtils();
    

    public Route(int p_routeId, String p_originLocation, String p_destination,
                 float p_distance, LocalTime p_estimatedTime){
        setRouteId(p_routeId);
        setOriginLocation(p_originLocation);
        setDestination(p_destination);
        setDistance(p_distance);
        setEstimatedTime(p_estimatedTime);
    }

    private void setRouteId(int p_routeId){
        routeValidator.validateRouteId(p_routeId);
        this.routeId = p_routeId;
    }


    private void setOriginLocation(String p_originLocation){
        routeValidator.validateOriginLocation(p_originLocation);
        this.originLocation = p_originLocation;
    }


    private void setDestination(String p_destination) {
        routeValidator.validateDestination(p_destination);
        this.destination = p_destination;
    }

    private void setDistance(float p_distance) {
         routeValidator.validateDistance(p_distance);
        this.distance = p_distance;
    }



    private void setEstimatedTime(LocalTime p_estimatedTime){
        routeValidator.validateEstimatedTime(p_estimatedTime);
        this.estimatedTime = p_estimatedTime;
    }


    public int getIdRoute() {
        return routeId;
    }

    public String getOriginLocation() {
        return originLocation;
    }

    public String getDestination() {
        return destination;
    }

    public float getDistance() {
        return distance;
    }

    public LocalTime getEstimatedTime() {
        return estimatedTime;
    }

    @Override
    public String toString() {
        return "Route:" +
                "Route ID:" + getIdRoute()+
                ", Origin Location:'" + getOriginLocation() + '\'' +
                ", Destination:" + getDestination() + '\'' +
                ", Distance:" + getDistance() +
                ", EstimatedTime:" + getEstimatedTime();
    }
}
