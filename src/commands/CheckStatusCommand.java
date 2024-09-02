package commands;

import entities.Command;
import entities.Drone;
import utils.Validator;

import java.util.HashMap;

public class CheckStatusCommand implements Command {
    private Validator validator;
    private String[] parts;

    public CheckStatusCommand(Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean validate() {
        return validator.validateSerialNumber(parts[1]);
    }

    @Override
    public void execute(String input, HashMap<String, Drone> drones) {
        parse(input);
        if (validate()) {
            String serialNumber = parts[1];
            Drone drone = drones.get(serialNumber);
            if (!validator.validateDrone(serialNumber, drones)) return;

            System.out.println("Drone " + serialNumber + " state is: " + drone.getState());

        }
    }

    @Override
    public void describtion() {
        System.out.println("check_status check_status <drone_serial_number>  Displays the current state, battery level, and loaded medications of a drone with the given serial number.");
    }

    @Override
    public void parse(String input) {
        parts = input.split(" ");
    }
}
