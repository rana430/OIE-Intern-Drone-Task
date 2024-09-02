package utils;

import entities.Drone;

import java.util.HashMap;

public class Validator {

    private static Validator instance = null;

    private Validator () {}

    public static synchronized Validator getInstance(){
        if (instance == null) {
            instance = new Validator();
        }
        return instance;
    }

    public boolean validateSerialNumber(String string) {
        if (string.isEmpty()) {
            System.out.println("Invalid serial number. It cannot be empty.");
            return false;
        }
        return true;
    }

    public boolean validateFloat(String number) {
        float medicationWeight;
        try {
            medicationWeight = Float.parseFloat(number);
        } catch (NumberFormatException e) {
            System.out.println("Invalid medication weight. Please enter a valid float value.");
            return false;
        }
        return true;
    }

    public boolean validateModel(String model) {
        Drone.Model newModel;
        try {
            newModel = Drone.Model.valueOf(model.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid model type. Please enter a valid model (e.g., LIGHTWEIGHT, MIDDLEWEIGHT).");
            return false;
        }
        return true;
    }

    public  boolean validateDroneName(String droneName) {
        return true;
    }

    public boolean validateDrone(String serialNumber, HashMap<String, Drone> drones) {
        if (drones.containsKey(serialNumber)) {
            System.out.println("No Drone with serial number " + serialNumber + " found! Please Enter Correct One");
            return false;
        }
        return true;
    }
}
