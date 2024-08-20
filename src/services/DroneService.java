package services;

import entities.Drone;
import entities.Medication;
import enums.Model;
import enums.State;

import java.util.List;

public class DroneService {
    public void register(char[] serialNumber , Model model , float weightLimit){

    }
    public void load(Medication medication , char[] serialNumber){}

    public void deliver(char[] serialNumber){}

    public void recharge(char[] serialNumber){}

    public List<Drone> getDroneList(List<char[]> serialNumber){
        return null;
    }

    public State checkStatus(char[] serialNumber){
        return null;
    }


}
