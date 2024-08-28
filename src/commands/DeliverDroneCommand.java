package commands;

import controller.DroneController;
import entities.Command;

public class DeliverDroneCommand implements Command {
    private DroneController drone;
    public DeliverDroneCommand(DroneController drone){
        this.drone = drone;
    }
    @Override
    public void execute(String input) {
        drone.deliverDrone(input);
    }
}
