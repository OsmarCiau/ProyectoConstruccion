package Logistics;

import java.time.LocalTime;

public class RoutePlannerValidationUtils {
    public boolean validateWarehouseLocation(String p_warehouseLocation){
        boolean isValid = false;
        if(p_warehouseLocation != null){
            isValid = true;
        }else{
            throw new IllegalArgumentException("WAREHOUSE LOCATION REQUIRED");
        }
        return isValid;

    }

    public boolean validateStartTime(LocalTime p_startTime){
        boolean isValid = false;
        LocalTime minimumTime = LocalTime.of(8,0);

        if(p_startTime.isAfter(minimumTime)){
            isValid = true;
        }else{
            throw new IllegalArgumentException("START TIME MUST BE AFTER 8:00 AM");
        }

        return isValid;
    }
}
