package Proyect.Logistics;

import java.time.LocalTime;

public class Route {
    private int a_routeId = 0;
    private String a_originLocation = null;
    private String a_destination = null;
    private float a_distance = 0.0f;
    private LocalTime a_estimatedTime = LocalTime.of(0, 0);

    public Route(int p_routeId, String p_originLocation, String p_destination,
                 float p_distance, LocalTime p_estimatedTime){
        setRouteId(p_routeId);
        setOriginLocation(p_originLocation);
        setDestination(p_destination);
        setDistance(p_distance);
        setEstimatedTime(p_estimatedTime);
    }

    private void setRouteId(int p_routeId){
        boolean routeIdIsValid = validateRouteId(p_routeId);
        if(routeIdIsValid){
            this.a_routeId = p_routeId;
        }
    }

    private boolean validateRouteId(int p_routeId){
        boolean isValid = false;
        int minimumValue= 0;

        if(p_routeId > minimumValue){
            isValid = true;
        }else{
            throw new IllegalArgumentException("ID MUST BE GREATER THAN 0");
        }

        return isValid;
    }

    private void setOriginLocation(String p_originLocation){
        boolean originLocationIsValid = validateOriginLocation(p_originLocation);
        if(originLocationIsValid){
            this.a_originLocation = p_originLocation;
        }

    }

    private boolean validateOriginLocation(String p_originLocation){
        boolean isValid = false;
        if(p_originLocation != null){
            isValid = true;
        }else{
            throw new IllegalArgumentException("ORIGIN LOCATION REQUIRED");
        }
        return isValid;
    }

    private void setDestination(String p_destination) {
        boolean destinationIsValid = validateDestination(p_destination);
        if(destinationIsValid){
            this.a_destination = p_destination;
        }
    }

    private boolean validateDestination(String p_destination){
        boolean isValid = false;
        if(p_destination != null){
            isValid = true;
        }else{
            throw new IllegalArgumentException("DESTINATION REQUIRED");
        }

        return isValid;

    }

    private void setDistance(float distance) {
        float minimumDistinace = 0.0f;

        if(distance > minimumDistinace){
            this.a_distance = distance;
        }else{
            throw new IllegalArgumentException("DISTANCE MUST BE GREATER THAN 0.0 KM");
        }
    }

    private void setEstimatedTime(LocalTime p_estimatedTime){
        boolean estimatedTimeIsValid = validateEstimatedTime(p_estimatedTime);
        if(estimatedTimeIsValid){
            this.a_estimatedTime = p_estimatedTime;
        }
    }

    private boolean validateEstimatedTime(LocalTime p_estimatedTime){
        boolean isValid = false;
        LocalTime minimumTime = LocalTime.of(0, 0);

        if(p_estimatedTime.equals(minimumTime)){
            throw new IllegalArgumentException("ESTIMATED TIME MUST BE LONGER THAN 0:00");
        }else{
            isValid = true;
        }

        return isValid;
    }

    public int getIdRoute() {
        return a_routeId;
    }

    public String getOriginLocation() {
        return a_originLocation;
    }

    public String getDestination() {
        return a_destination;
    }

    public float getDistance() {
        return a_distance;
    }

    public LocalTime getEstimatedTime() {
        return a_estimatedTime;
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
