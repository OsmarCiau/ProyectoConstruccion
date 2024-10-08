package Logistics;

import java.time.LocalTime;

public class Route {
    private int idRoute = 0;
    private String originLocation = null;
    private String destination = null;
    private float distance = 0.0f;
    private LocalTime estimatedTime = LocalTime.of(0, 0);

    public Route(int idRoute, String originLocation, String destination,
                  float distance, LocalTime estimatedTime){
        setIdRoute(idRoute);
        setOriginLocation(originLocation);
        setDestination(destination);
        setDistance(distance);
        setEstimatedTime(estimatedTime);
    }

    private void setIdRoute(int idRoute){
        int minimumValue = 0;
        if(idRoute > minimumValue ){
            this.idRoute = idRoute;
        }else{
            throw new IllegalArgumentException("ID MUST BE GREATER THAN 0");
        }
    }

    private void setOriginLocation(String originLocation){
        if(originLocation != null){
            this.originLocation = originLocation;
        }else{
            throw new IllegalArgumentException("ORIGIN LOCATION REQUIRED");
        }
    }

    private void setDestination(String destination) {
        if(destination != null){
            this.destination = destination;
        }else{
            throw new IllegalArgumentException("DESTINATION REQUIRED");
        }

    }

    private void setDistance(float distance) {
        float minimumDistinace = 0.0f;

        if(distance > minimumDistinace){
            this.distance = distance;
        }else{
            throw new IllegalArgumentException("DISTANCE MUST BE GREATER THAN 0.0 KM");
        }
    }

    private void setEstimatedTime(LocalTime estimatedTime){
        LocalTime minimumTime = LocalTime.of(0,0);

        if(estimatedTime.isAfter(minimumTime)){
            this.estimatedTime = estimatedTime;
        }

    }

    public int getIdRoute() {
        return idRoute;
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
                "idRoute:" + idRoute +
                ", originLocation:'" + originLocation + '\'' +
                ", destination:" + destination + '\'' +
                ", distance:" + distance +
                ", estimatedTime:" + estimatedTime +
                '}';
    }
}
