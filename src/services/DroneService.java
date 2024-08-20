package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.Drone;
import entities.Medication;
import enums.Model;
import enums.State;

public class DroneService {
    private HashMap<char [],Drone> drones;
    public DroneService() {
        drones = new HashMap();
    }
    public void register(char[] serialNumber , Model model , float weightLimit){
        Drone drone = new Drone(serialNumber, model, weightLimit);
        drones.put(serialNumber, drone);
    }
    public void load(Medication medication , char[] serialNumber){
        Drone drone = drones.get(serialNumber);
        // check battery level is above 25
        if (drone.getBatteryCapacity() < 25) {
            if(drone.getWeightLimit() >= drone.getCurWeight() + medication.getWeight()) {
                drone.addMedication(medication);
                drone.setCurWeight(medication.getWeight());
                //check drone state
                if (drone.getState() == State.IDLE) {
                    drone.setState(State.LOADED);
                }
            } else {
                System.out.println("Drone Weight Limit Exceeded");
                return;
            }
        } else {
            drone.setState(State.RETURNED);
            System.out.println("Drone Battery is Low, Please Recharge");
            return;
        }
    }

    public void deliver(char[] serialNumber){
        Drone drone = drones.get(serialNumber);

        //check drone state
        if (drone.getState() != State.LOADED) {
            System.out.println("Drone State is not in LOADED");
            return;
        }

        //check drone battery level
        if (drone.getBatteryCapacity() < 25) {
            drone.setState(State.RETURNED);
            System.out.println("Drone Battery is Low, Please Recharge");
            return;
        } else {
            while (drone.getBatteryCapacity() >= 25) {
                drone.getMedications().remove();
                drone.setBatteryCapacity(drone.getBatteryCapacity() - 20);
            }
            if(drone.getMedications().isEmpty()) {
                drone.setState(State.DELIVERED);
            } else {
                drone.setState(State.RETURNED);
            }
        }
    }

    public void recharge(char[] serialNumber){
        Drone drone = drones.get(serialNumber);
        drone.setBatteryCapacity(100);
        if (!drone.getMedications().isEmpty()) {
            drone.setState(State.DELIVERING);
        }
    }

    public void getDroneList(){
        for (Drone drone : drones.values()) {
            System.out.println("Drone State: "+drone.getState() + " Drone Battery Level" + drone.getBatteryCapacity());
        }
    }

    public State checkStatus(char[] serialNumber){
        Drone drone = drones.get(serialNumber);
        return drone.getState();
    }
}
