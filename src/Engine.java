import controller.DroneController;
import services.DroneService;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Engine {
    public void run() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Enter Command");
            String input = scan.nextLine();
            String[] parts = input.split(" ");
            switch (parts[0].toLowerCase()) {
                case "load":
                    DroneController.getInstance().loadDrone(input);
                    break;
                case "register":
                    DroneController.getInstance().registerDrone(input);
                    break;
                case "recharge":
                    DroneController.getInstance().rechargeDrone(input);
                    break;
                case "check_status":
                    DroneController.getInstance().checkDroneState(input);
                    break;
                case "deliver":
                    DroneController.getInstance().deliverDrone(input);
                    break;
                case "list_drones":
                    DroneController.getInstance().listDrones();
                    break;
                case "help":
                    getCommands();
                    break;
                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }
    };

    private void getCommands() {
        System.out.println("list_drones  Displays a list of all registered drones along with their current states and battery levels.");
        System.out.println("deliver deliver <drone_serial_number>  Delivers medications one by one for a drone.");
        System.out.println("check_status check_status <drone_serial_number>  Displays the current state, battery level, and loaded medications of a drone with the given serial number.");
        System.out.println("load   load <drone_serial_number> <medication_name> <medication_weight> <medication_code>  Loads a medication onto a drone with the given serial number.");
        System.out.println("register  register <serial_number> <model> <weight_limit>  Registers a drone with the given serial number, model, and weight limit.");
        System.out.println("recharge  recharge <drone_serial_number>  Recharges the battery of a drone with the given serial number.");
    }
}
