package commands;

import controller.DroneController;
import entities.Command;

public class RegisterCommand implements Command {
    private DroneController droneController;

    public RegisterCommand(DroneController droneController) {
        this.droneController = droneController;
    }

    @Override
    public void execute(String input) {
        droneController.registerDrone(input);
    }
}
