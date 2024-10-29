package Proyect.Logistics;

import java.time.LocalTime;

public class RouteValidationUtils {

    public boolean validateRouteId(int p_routeId){
        boolean isValid = false;
        int minimumValue= 0;

        if(p_routeId > minimumValue){
            isValid = true;
        }else{
            throw new IllegalArgumentException("ID MUST BE GREATER THAN 0");
        }

        return isValid;
    }

    public boolean validateOriginLocation(String p_originLocation){
        boolean isValid = false;
        if(p_originLocation != null){
            isValid = true;
        }else{
            throw new IllegalArgumentException("ORIGIN LOCATION REQUIRED");
        }
        return isValid;
    }

    public boolean validateDestination(String p_destination){
        boolean isValid = false;
        if(p_destination != null){
            isValid = true;
        }else{
            throw new IllegalArgumentException("DESTINATION REQUIRED");
        }

        return isValid;

    }

    public boolean validateDistance(Float p_distance){
        boolean isValid = false;

        float minimumDistinace = 0.0f;

        if(p_distance > minimumDistinace){
            isValid = true;
        }else{
            throw new IllegalArgumentException("DISTANCE MUST BE GREATER THAN 0.0 KM");
        }

        return isValid;

    }

    public boolean validateEstimatedTime(LocalTime p_estimatedTime){
        boolean isValid = false;
        LocalTime minimumTime = LocalTime.of(0, 0);

        if(p_estimatedTime.equals(minimumTime)){
            throw new IllegalArgumentException("ESTIMATED TIME MUST BE LONGER THAN 0:00");
        }else{
            isValid = true;
        }

        return isValid;
    }
}
