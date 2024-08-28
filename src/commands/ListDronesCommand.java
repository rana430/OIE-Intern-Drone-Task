package commands;

import controller.DroneController;
import entities.Command;

public class ListDronesCommand implements Command {
    private DroneController drone;
    public ListDronesCommand(DroneController drone) {
        this.drone = drone;
    }
    @Override
    public void execute(String input) {
        drone.listDrones();
    }
}
