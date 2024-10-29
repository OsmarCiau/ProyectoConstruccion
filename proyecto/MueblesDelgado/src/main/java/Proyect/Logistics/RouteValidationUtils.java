package Proyect.Logistics;

import java.time.LocalTime;

public class RouteValidationUtils {

   public void validateRouteId(int p_routeId){
        int minimumValue= 0;

        if(p_routeId <= minimumValue){
            throw new IllegalArgumentException("ID MUST BE GREATER THAN 0");
        }
    }

   public void validateOriginLocation(String p_originLocation){
        if(p_originLocation == null){
            throw new IllegalArgumentException("ORIGIN LOCATION REQUIRED");
        }
    }

   public void validateDestination(String p_destination){
        if(p_destination == null){
            throw new IllegalArgumentException("DESTINATION REQUIRED");
        }
    }

     public void validateDistance(Float p_distance){
        float minimumDistinace = 0.0f;

        if(p_distance <= minimumDistinace){
            throw new IllegalArgumentException("DISTANCE MUST BE GREATER THAN 0.0 KM");
        }

    }

    public void  validateEstimatedTime(LocalTime p_estimatedTime){
        LocalTime minimumTime = LocalTime.of(0, 0);

        if(p_estimatedTime.equals(minimumTime)){
            throw new IllegalArgumentException("ESTIMATED TIME MUST BE LONGER THAN 0:00");
        }

    }

    
}
