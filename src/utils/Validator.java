package utils;

import entities.Drone;

public class Validator {
    public boolean validateString(String string) {
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
}
