package Proyect.Validations;

import Proyect.Container.ContainerList;
import Proyect.StoreKeeper.StorageKeys;
import Proyect.Container.Container;

import java.util.ArrayList;

public class ValidationUtils {
    public static void validatePositiveNumber(int p_number, String p_fieldName) {
        if (p_number <= 0) {
            throw new IllegalArgumentException("Error. " + p_fieldName + " must be positive.");
        }
    }

    public static void validatePositiveNumber(float p_number, String p_fieldName) {
        if (p_number <= 0) {
            throw new IllegalArgumentException("Error. " + p_fieldName + " must be positive.");
        }
    }

    public static void validateNonNegativeNumber(int p_number, String p_fieldName){ //for quantities
        if(p_number < 0){
            throw new IllegalArgumentException("Error. " + p_fieldName +" cannot be negative.");
        }
    }

    public static void validateNonNegativeNumber(float p_number, String p_fieldName){ //for quantities
        if(p_number < 0){
            throw new IllegalArgumentException("Error. " + p_fieldName +" cannot be negative.");
        }
    }

    public static void validateString(String p_string, String p_fieldName) {
        if (p_string == null || p_string.isEmpty()) {
            throw new IllegalArgumentException("Error. " + p_fieldName + " cannot be null or empty.");
        }
    }

    public static void validateNonNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException("Error. " + fieldName + " cannot be null.");
        }
    }

    public static void validateLocationInRack(StorageKeys locationInRack){
        if (locationInRack.getRackNumber() == 0 || locationInRack.getCellNumber() == 0) {
            throw new IllegalArgumentException("Error. Location in rack cannot be null or empty");
        }
    }

    public static <T> void validatesArrayList(ArrayList<T> p_list, String fieldName ) {
        if (p_list == null || p_list.isEmpty()) {
            throw new IllegalArgumentException("Error. " + fieldName + " is empty or null.");
        }
    }

    public static <T extends Container> void validateContainerList(ContainerList<T> p_containerList, String fieldName ){
        if(p_containerList == null || p_containerList.isEmpty()){
            throw new IllegalArgumentException("Error. " + fieldName + " is empty or null.");
        }
    }


}
