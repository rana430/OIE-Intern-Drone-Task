package entities;

import java.util.HashMap;

public interface Command {
    boolean validate();
    void execute(String input, HashMap<String, Drone> drones);
    void describtion();
    void parse(String input);
}
