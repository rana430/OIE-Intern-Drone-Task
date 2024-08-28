package entities;

import java.util.LinkedList;
import java.util.Queue;

public class Drone {
    private String serialNumber = "";
    private Drone.Model model;
    private Queue<Medication> medications;
    private float weightLimit;
    private int batteryCapacity;
    private State state;

    public Drone(String serialNumber, Drone.Model model, float weightLimit) {
        this.serialNumber = serialNumber;
        this.model = model;
        medications = new LinkedList<>(); //TODO
        this.weightLimit = weightLimit;
        batteryCapacity = 100;
        this.state = State.IDLE;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Drone.Model getModel() {
        return model;
    }

    public Queue<Medication> getMedications() {
        return medications;
    }
    
    public float getWeightLimit() {
        return weightLimit;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        batteryCapacity = batteryCapacity;
    }

    public Drone.State getState() {
        return state;
    }

    public void setState(Drone.State state) {
        this.state = state;
    }

    public void addMedication (Medication medication) {
        medications.add(medication);
    }
    
    public float getCurWeight() {
        float weight = 0;
        for (Medication medication : medications) {
            weight += medication.getWeight();
        }
        return weight;
    }

    public enum State {
        IDLE , LOADED , DELIVERING , DELIVERED , RETURNED
    }

    public enum Model {
        LIGHTWEIGHT(100, 50),
        MIDDLEWEIGHT(200, 100),
        CRUISERWEIGHT(300, 150),
        HEAVYWEIGHT(500, 250);

        private final float maxWeightLimit;
        private final float minWeightLimit;

        Model(float maxWeightLimit, float minWeightLimit) {
            this.maxWeightLimit = maxWeightLimit;
            this.minWeightLimit = minWeightLimit;
        }

        public float getMaxWeightLimit() {
            return maxWeightLimit;
        }

        public float getMinWeightLimit() {
            return minWeightLimit;
        }
    }

}
