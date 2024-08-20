package controller;

import entities.Drone;
import entities.Medication;
import services.DroneService;
import utils.Validator;

import java.util.HashMap;

public class DroneController {
    DroneService droneService = DroneService.getInstance();

    Validator validator = new Validator();

    private static DroneController instance = null;

    private DroneController() {}

    public static synchronized DroneController getInstance() {
        if (instance ==null)
            instance = new DroneController();
        return instance;
    }

    public void registerDrone(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 4) {
            System.out.println("Invalid input format. Please enter: serialNumber model weightLimit");
            return;
        }

        String serialNumber = parts[1];
        if(!validator.validateString(serialNumber))
            return;

        Drone.Model model;
        if (!validator.validateModel(parts[2].toUpperCase()))
            return;
        model = Drone.Model.valueOf(parts[2].toUpperCase());

        float weightLimit;
        if (!validator.validateFloat(parts[3]))
            return;
        weightLimit = Float.parseFloat(parts[3]);

        droneService.register(serialNumber, model, weightLimit);
    }

    public void  loadDrone(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 5) {
            System.out.println("Invalid input format. Please enter: serialNumber medicationName medicationWeight medicationCode");
            return;
        }

        String serialNumber = parts[1];
        if (serialNumber.isEmpty()) {
            System.out.println("Invalid serial number. It cannot be empty.");
            return;
        }

        String medicationName = parts[2];
        if (medicationName.isEmpty()) {
            System.out.println("Invalid medication name. It cannot be empty.");
            return;
        }

        float medicationWeight;
        try {
            medicationWeight = Float.parseFloat(parts[3]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid medication weight. Please enter a valid float value.");
            return;
        }

        String medicationCode = parts[4];

        Medication medication = new Medication(medicationName, medicationWeight, medicationCode);
        droneService.load(medication, serialNumber);
    }

    public Drone.State checkDroneState(String input) {
        String[] parts = input.split(" ");
        String serialNumber = parts[1];

        if(!validator.validateString(serialNumber))
            return null;

        return droneService.checkStatus(serialNumber);
    }

    public void deliverDrone(String input) {
        String[] parts = input.split(" ");
        String serialNumber = parts[1];
        if(!validator.validateString(serialNumber))
            return;
        droneService.deliver(serialNumber);
    }

    public void rechargeDrone(String input) {
        String[] parts = input.split(" ");
        String serialNumber = parts[1];
        droneService.recharge(serialNumber);
    }

    public void listDrones() {
        droneService.getDroneList();
    }


}
