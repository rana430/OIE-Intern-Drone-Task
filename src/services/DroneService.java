package services;

import java.util.HashMap;

import entities.Drone;
import entities.Medication;

public class DroneService {
    private HashMap<String, Drone> drones;

    private static DroneService instance;

    private DroneService() {
        drones = new HashMap<>();
    }

    public static synchronized DroneService getInstance() {
        if (instance == null)
            instance = new DroneService();

        return instance;
    }

    public void register(String serialNumber , Drone.Model model , float weightLimit){
        Drone drone = new Drone(serialNumber, model, weightLimit);
        if(drones.containsKey(serialNumber)){
            System.out.println("Drone is already registered with serial number " + serialNumber);
            return;
        }
        if (!checkdroneWeight(model, weightLimit))
            return;
        drones.put(serialNumber, drone);

        System.out.println("Drone is registered with serial number " + serialNumber);
    }
    boolean checkdroneWeight(Drone.Model model, float weightLimit){
        if(model.getMaxWeightLimit() < weightLimit){
            System.out.println("Maximum weight limit is " + model.getMaxWeightLimit());
            return false;
        }
        if(model.getMinWeightLimit() > weightLimit) {
            System.out.println("Minimum weight limit is " + model.getMinWeightLimit());
            return false;
        }
        return true;
    }
    public void load(Medication medication, String serialNumber) {
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

    private boolean isBatterySufficient(Drone drone) {
        return drone.getBatteryCapacity() >= 25;
    }

    private void handleLowBattery(Drone drone) {
        drone.setState(Drone.State.RETURNED);
        System.out.println("Drone Battery is Low, Please Recharge");
    }

    private boolean canLoadMedication(Drone drone, Medication medication) {
        return drone.getWeightLimit() >= drone.getCurWeight() + medication.getWeight();
    }

    private void loadMedication(Drone drone, Medication medication) {
        drone.addMedication(medication);
        drone.setCurWeight(drone.getCurWeight() + medication.getWeight());

        if (drone.getState() == Drone.State.IDLE) {
            drone.setState(Drone.State.LOADED);
        }
    }


    public void deliver(String serialNumber) {
        Drone drone = drones.get(serialNumber);

        if (drone == null) {
            System.out.println("Drone is not registered with serial number " + serialNumber);
            return;
        }

        if (!isDroneLoaded(drone)) {
            System.out.println("Drone State is not in LOADED");
            return;
        }

        if (!isBatterySufficient(drone)) {
            handleLowBattery(drone);
            return;
        }

        deliverMedications(drone);

        System.out.println("Medication Delivered");
    }

    private boolean isDroneLoaded(Drone drone) {
        return drone.getState() == Drone.State.LOADED;
    }

    private void deliverMedications(Drone drone) {
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


    public void recharge(String serialNumber){
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

    public void getDroneList(){
        for (Drone drone : drones.values()) {
            System.out.println("Serial Number: " + drone.getSerialNumber() + " Drone State: "+drone.getState() + " Drone Battery Level: " + drone.getBatteryCapacity());
        }
    }

    public Drone.State checkStatus(String serialNumber){
        Drone drone = drones.get(serialNumber);
        return drone.getState();
    }

    public HashMap<String, Drone> getDrones() {
        return drones;
    }
}
