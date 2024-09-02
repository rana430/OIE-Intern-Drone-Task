package commands;

import entities.Command;
import entities.Drone;
import entities.Medication;
import utils.Validator;

import java.util.HashMap;

public class LoadCommand implements Command {
    private Validator validator;
    private String[] parts;

    public LoadCommand(Validator validator) { this.validator = validator; }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public void execute(String input, HashMap<String, Drone> drones) {
        parse(input);
        if (validate()) {
            String serialNumber = parts[1];
            String medicationName = parts[2];
            float medicationWeight = Float.parseFloat(parts[3]);
            String medicationCode = parts[4];
            Medication medication = createMedication(medicationName, medicationWeight, medicationCode);

            Drone drone = drones.get(serialNumber);
            if (drone == null) {
                System.out.println("Drone not found");
                return;
            }

            if (!isBatterySufficient(drone)) {
                handleLowBattery(drone);
                return;
            }

            if (!canLoadMedication(drone, medication)) {
                System.out.println("Drone Weight Limit Exceeded");
                return;
            }

            loadMedication(drone, medication);

            System.out.println("Medication Loaded");
        }
    }

    @Override
    public void describtion() {
        System.out.println("Load Medication Command");
    }

    @Override
    public void parse(String input) {
        parts = input.split(",");
    }

    private boolean canLoadMedication(Drone drone, Medication medication) {
        return drone.getWeightLimit() >= drone.getCurWeight() + medication.getWeight();
    }

    private void loadMedication(Drone drone, Medication medication) {
        drone.addMedication(medication);

        if (drone.getState() == Drone.State.IDLE) {
            drone.setState(Drone.State.LOADED);
        }
    }

    private void handleLowBattery(Drone drone) {
        drone.setState(Drone.State.RETURNED);
        System.out.println("Drone Battery is Low, Please Recharge");
    }

    private boolean isBatterySufficient(Drone drone) {
        return drone.getBatteryCapacity() >= 25;
    }

    private Medication createMedication(String name, float weight, String code) {
       return new Medication(name, weight, code);
    }
}
