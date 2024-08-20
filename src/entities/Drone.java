package entities;

import enums.Model;
import enums.State;

import java.util.Queue;

public class Drone {
    private char[] serialNumber = new char[100];
    private Model model;
    private Queue<Medication> medications;
    private float WeightLimit;
    private int BatteryCapacity;
    private State state;

    public Drone(){}
    public Drone(char[] serialNumber, Model model, Queue<Medication> medications, float weightLimit, int batteryCapacity, State state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.medications = medications;
        WeightLimit = weightLimit;
        BatteryCapacity = batteryCapacity;
        this.state = state;
    }

    public char[] getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(char[] serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Queue<Medication> getMedications() {
        return medications;
    }

    public void setMedications(Queue<Medication> medications) {
        this.medications = medications;
    }

    public float getWeightLimit() {
        return WeightLimit;
    }

    public void setWeightLimit(float weightLimit) {
        WeightLimit = weightLimit;
    }

    public int getBatteryCapacity() {
        return BatteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        BatteryCapacity = batteryCapacity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
