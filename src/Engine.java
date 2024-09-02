import commands.*;
import entities.Command;
import entities.Drone;
import utils.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Engine {
    private Map<String, Command> commandMap;
    private HashMap<String, Drone> drones;
    public Engine() {
        commandMap = new HashMap<>();
        drones = new HashMap<>();
        Validator validator = Validator.getInstance();

        commandMap.put("load", new LoadCommand(validator));
        commandMap.put("register", new RegisterCommand(validator));
        commandMap.put("recharge", new RechargeCommand(validator));
        commandMap.put("check_status", new CheckStatusCommand(validator));
        commandMap.put("deliver", new DeliverDroneCommand(validator));
        commandMap.put("list_drones", new ListDronesCommand());
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
                command.execute(input, drones);
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
