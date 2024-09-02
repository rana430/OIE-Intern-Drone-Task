package commands;

import entities.Command;
import entities.Drone;
import utils.Validator;

import java.util.HashMap;

public class RechargeCommand implements Command {
    private Validator validator;
    private String[] parts;

    public RechargeCommand(Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean validate() {
        if (!validator.validateSerialNumber(parts[1])) {
            System.out.println("Invalid serial number. Please enter a valid serial number with max 50 length");
            return false;
        }
        return true;
    }

    @Override
    public void execute(String input, HashMap<String, Drone> drones) {
        parse(input);
        if (validate()) {
            String serialNumber = parts[1];
            Drone drone = drones.get(serialNumber);

            if (drone == null) {
                System.out.println("Drone is not registered with serial number " + serialNumber);
                return;
            }

            drone.setBatteryCapacity(100);
            if (!drone.getMedications().isEmpty()) {
                drone.setState(Drone.State.DELIVERING);
            }

            System.out.println("Drone Recharged");
        }
    }

    @Override
    public void describtion() {

    }

    @Override
    public void parse(String input) {
        parts = input.split(" ");
    }
}
