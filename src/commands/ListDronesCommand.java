package commands;

import entities.Command;
import entities.Drone;
import entities.Medication;

import java.util.HashMap;
import java.util.Queue;

public class ListDronesCommand implements Command {
    private HashMap<String, Drone> drones;

    public ListDronesCommand() {
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public void execute(String input, HashMap<String, Drone> drones) {
        parse(input);
        if (validate()) {
            this.drones = drones;
            getDrones();
        }
    }

    @Override
    public void describtion() {
        System.out.println("List all registered Drones: ");
    }

    @Override
    public void parse(String input) {}

    private void getDrones() {
        if (drones.isEmpty()) {
            System.out.println("No Registered Drones Yet");
            return;
        }
        System.out.println("here");
        for (Drone drone : drones.values()) {
            System.out.println("Serial Number: " + drone.getSerialNumber() + " Drone State: "+drone.getState() + " Drone Battery Level: " + drone.getBatteryCapacity());
            System.out.println("Drone Medications: ");
            getDroneMedication(drone.getSerialNumber());
        }
    }

    private void getDroneMedication(String serialNumber) {
        Drone drone = drones.get(serialNumber);
        Queue<Medication> medications = drone.getMedications();
        if (medications.isEmpty()) {
            System.out.println("This drone has no Medication");
            return;
        }
        for (Medication medication : medications) {
            System.out.println("Medication Name: " + medication.getName());
        }
    }
}
