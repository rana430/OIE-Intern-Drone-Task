package entities;

import enums.Model;
import enums.State;

import java.util.LinkedList;
import java.util.Queue;

public class Drone {
    private char[] serialNumber = new char[100];
    private Model model;
    private Queue<Medication> medications;
    private float WeightLimit;
    private int BatteryCapacity;
    private State state;
    private float curWeight;

    public Drone(){}
    public Drone(char[] serialNumber, Model model, float weightLimit) {
        this.serialNumber = serialNumber;
        this.model = model;
        medications = new LinkedList<>();
        WeightLimit = weightLimit;
        BatteryCapacity = 0;
        this.state = State.IDLE;
        curWeight = 0.0f;
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

    public void addMedication (Medication medication) {
        medications.add(medication);
    }

    public void removeMedication (Medication medication) {
        medications.remove(medication);
    }

    public void rechargeBattery () {
        BatteryCapacity = 100;
    }

    public void deliver () {
        setState(State.DELIVERING);
    }

    public float getCurWeight() {
        return curWeight;
    }

    public void setCurWeight(float medicationWeight) {
        curWeight += medicationWeight;
    }
}
