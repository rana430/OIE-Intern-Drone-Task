package commands;

import entities.Command;
import entities.Drone;
import utils.Validator;

import java.util.HashMap;

public class RegisterCommand implements Command {
    private String[] parts;
    private Validator validator;

    public RegisterCommand(Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean validate() {
        if (parts.length != 4) {
            System.out.println("Invalid input format. Please enter: register <serialNumber> <model> <weightLimit>");
            return false;
        }

        if (!validator.validateSerialNumber(parts[1])) {
            System.out.println("Invalid serial number. Please enter a valid serial number with max 50 length");
            return false;
        }

        if (!validator.validateModel(parts[2].toUpperCase())) {
            System.out.println("Invalid model. Please enter a valid model: LIGHTWEIGHT, MIDDLEWEIGHT, CRUISERWEIGHT, HEAVYWEIGHT");
            return false;
        }

        if (!validator.validateFloat(parts[3])) {
            System.out.println("Invalid weight. Please enter a valid weight");
            return false;
        }

        return true;
    }

    @Override
    public void execute(String input, HashMap<String, Drone> drones) {
        parse(input);
        if (validate()) {
            String serialNumber = parts[1];
            Drone.Model model = Drone.Model.valueOf(parts[2].toUpperCase());
            float weightLimit = Float.parseFloat(parts[3]);

             if (drones.containsKey(serialNumber)) {
                 System.out.println("Drone is already registered with serial number " + serialNumber);
                 return;
             }

            if (!checkdroneWeight(model, weightLimit))
                return;

            Drone drone = new Drone(serialNumber, model, weightLimit);
            drones.put(serialNumber, drone);

            System.out.println("Drone is registered with serial number " + serialNumber);
        }
    }

    @Override
    public void describtion() {
        System.out.println("register <serialNumber> <model> <weightLimit> : Registers a drone with the given serial number, model, and weight limit.");
    }

    @Override
    public void parse(String input) {
        parts = input.split(" ");
    }

    boolean checkdroneWeight(Drone.Model model, float weightLimit){
        if (model.getMaxWeightLimit() < weightLimit){
            System.out.println("Maximum weight limit is " + model.getMaxWeightLimit());
            return false;
        }
        if (model.getMinWeightLimit() > weightLimit) {
            System.out.println("Minimum weight limit is " + model.getMinWeightLimit());
            return false;
        }
        return true;
    }
}
