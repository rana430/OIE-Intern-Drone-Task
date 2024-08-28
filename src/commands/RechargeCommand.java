package commands;

import controller.DroneController;
import entities.Command;

public class RechargeCommand implements Command {
    private DroneController drone;

    public RechargeCommand(DroneController drone){
        this.drone = drone;
    }

    @Override
    public void execute(String input) {
        drone.rechargeDrone(input);
    }
}
