import commands.*;
import controller.DroneController;
import entities.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Engine {
    private Map<String, Command> commandMap;

    public Engine() {
        commandMap = new HashMap<>();
        DroneController droneController = DroneController.getInstance();

        commandMap.put("load", new ListDronesCommand(droneController));
        commandMap.put("register", new RegisterCommand(droneController));
        commandMap.put("recharge", new RechargeCommand(droneController));
        commandMap.put("check_status", new CheckStatusCommand(droneController));
        commandMap.put("deliver", new DeliverDroneCommand(droneController));
        commandMap.put("list_drones", new ListDronesCommand(droneController));
    }

    public void run() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Command");
            String input = scan.nextLine();
            String[] parts = input.split(" ");
            String commandName = parts[0].toLowerCase();

            Command command = commandMap.get(commandName);
            if (command != null) {
                System.out.println(commandName);
                command.execute(input);
            } else if ("help".equals(commandName)) {
                getCommands();
            } else {
                System.out.println("Invalid Command");
            }
        }
    }

    private void getCommands() {
        System.out.println("list_drones  Displays a list of all registered drones along with their current states and battery levels.");
        System.out.println("deliver deliver <drone_serial_number>  Delivers medications one by one for a drone.");
        System.out.println("check_status check_status <drone_serial_number>  Displays the current state, battery level, and loaded medications of a drone with the given serial number.");
        System.out.println("load   load <drone_serial_number> <medication_name> <medication_weight> <medication_code>  Loads a medication onto a drone with the given serial number.");
        System.out.println("register  register <serial_number> <model> <weight_limit>  Registers a drone with the given serial number, model, and weight limit.");
        System.out.println("recharge  recharge <drone_serial_number>  Recharges the battery of a drone with the given serial number.");
    }
}
