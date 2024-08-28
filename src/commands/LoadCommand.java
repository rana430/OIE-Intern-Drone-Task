package commands;

import controller.DroneController;
import entities.Command;

public class LoadCommand implements Command {
    private DroneController droneController;

    public LoadCommand(DroneController droneController) {
        this.droneController = droneController;
    }

    @Override
    public void execute(String input) {
        droneController.loadDrone(input);
    }
}
