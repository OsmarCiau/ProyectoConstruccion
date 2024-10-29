package Proyect.Logistics;

import java.time.LocalTime;

public class RoutePlannerValidationUtils {
    
    public void validateWarehouseLocation(String p_warehouseLocation){
        if(p_warehouseLocation == null){
            throw new IllegalArgumentException("WAREHOUSE LOCATION REQUIRED");
        }
    }

    public void validateStartTime(LocalTime p_startTime){
        LocalTime minimumTime = LocalTime.of(8,0);

        if(p_startTime.isBefore(minimumTime)){
            throw new IllegalArgumentException("START TIME MUST BE AFTER 8:00 AM");
        }
    }
    
  
}
