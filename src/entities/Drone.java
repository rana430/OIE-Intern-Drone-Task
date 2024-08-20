package entities;

import java.util.LinkedList;
import java.util.Queue;

public class Drone {
    private String serialNumber = "";
    private Drone.Model model;
    private Queue<Medication> medications;
    private float WeightLimit;
    private int BatteryCapacity;
    private State state;
    private float curWeight;

    public Drone(String serialNumber, Drone.Model model, float weightLimit) {
        this.serialNumber = serialNumber;
        this.model = model;
        medications = new LinkedList<>(); //TODO
        WeightLimit = weightLimit;
        BatteryCapacity = 100;
        this.state = State.IDLE;
        curWeight = 0.0f;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Drone.Model getModel() {
        return model;
    }

    public void setModel(Drone.Model model) {
        this.model = model;
    }

    public Queue<Medication> getMedications() {
        return medications;
    }
    
    public float getWeightLimit() {
        return WeightLimit;
    }

    public int getBatteryCapacity() {
        return BatteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        BatteryCapacity = batteryCapacity;
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
        return curWeight;
    }

    public void setCurWeight(float medicationWeight) {
        curWeight += medicationWeight;
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
