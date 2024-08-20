import controller.DroneController;
import services.DroneService;

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
                default:
                    System.out.println("Invalid Command");
                    break;
            }
            System.out.println("Continue? Enter Y/N");
            if (!scan.nextLine().equalsIgnoreCase("Y")) {
                break;
            }
        }
    };
}
