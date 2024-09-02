package commands;

import entities.Command;
import entities.Drone;
import utils.Validator;

import java.util.HashMap;

public class DeliverDroneCommand implements Command {
    private Validator validator;
    private String[] parts;
    public DeliverDroneCommand(Validator validator) {
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

            while (isBatterySufficient(drone) && !drone.getMedications().isEmpty()) {
                drone.getMedications().remove();
                drone.setBatteryCapacity(drone.getBatteryCapacity() - 20);
            }

            if (drone.getMedications().isEmpty()) {
                drone.setState(Drone.State.DELIVERED);
                System.out.println("Drone has successfully delivered all medications.");
            } else {
                drone.setState(Drone.State.RETURNED);
                System.out.println("Drone could not deliver all medications and has returned.");
            }
        }
    }

    @Override
    public void describtion() {
        System.out.println("deliver deliver <drone_serial_number>  Delivers medications one by one for a drone.");
    }

    @Override
    public void parse(String input) {
        parts = input.split(" ");
    }

    private boolean isBatterySufficient(Drone drone) {
        return drone.getBatteryCapacity() >= 25;
    }
}
