package commands;

import controller.DroneController;
import entities.Command;
import entities.Drone;

public class CheckStatusCommand implements Command {
    private DroneController drone;

    public CheckStatusCommand(DroneController drone){
        this.drone = drone;
    }

    @Override
    public void execute(String input) {
        drone.checkDroneState(input);
    }
}
