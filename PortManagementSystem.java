import java.util.ArrayDeque;
import java.util.Scanner;

class Container {
    private String id;
    private String description;
    private String weight;

    public Container(String id, String description, String weight) {
        this.id = id;
        this.description = description;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return id + " | " + description + " | " + weight;
    }
}

class Ship {
    private String name;
    private String captain;

    public Ship(String name, String captain) {
        this.name = name;
        this.captain = captain;
    }

    @Override
    public String toString() {
        return name + " | Capt. " + captain;
    }
}

public class PortManagementSystem {   
    private static ArrayDeque<Container> containerStack = new ArrayDeque<>();
    private static ArrayDeque<Ship> shipQueue = new ArrayDeque<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Port Container Management System ===");
            System.out.println("[1] Store container (push)");
            System.out.println("[2] View port containers");
            System.out.println("[3] Register arriving ship (enqueue)");
            System.out.println("[4] View waiting ships");
            System.out.println("[5] Load next ship (pop + poll)");
            System.out.println("[0] Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> storeContainer();
                case 2 -> viewContainers();
                case 3 -> registerShip();
                case 4 -> viewShips();
                case 5 -> loadNextShip();
                case 0 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void storeContainer() {
        System.out.print("Enter Container ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Description: ");
        String desc = sc.nextLine();
        System.out.print("Enter Weight (e.g., 200kg): ");
        String weight = sc.nextLine();

        Container c = new Container(id, desc, weight);
        containerStack.push(c);
        System.out.println("Stored: " + c);
    }

    private static void viewContainers() {
        if (containerStack.isEmpty()) {
            System.out.println("No containers at the port.");
            return;
        }
        System.out.println("\nTOP →");
        for (Container c : containerStack) {
            System.out.println(c);
        }
        System.out.println("← BOTTOM");
    }

    private static void registerShip() {
        System.out.print("Enter Ship Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Captain: ");
        String captain = sc.nextLine();

        Ship s = new Ship(name, captain);
        shipQueue.add(s);
        System.out.println("Registered: " + s);
    }

    private static void viewShips() {
        if (shipQueue.isEmpty()) {
            System.out.println("No ships waiting.");
            return;
        }
        System.out.println("\nFRONT →");
        for (Ship s : shipQueue) {
            System.out.println(s);
        }
        System.out.println("← REAR");
    }

    private static void loadNextShip() {
        if (containerStack.isEmpty() || shipQueue.isEmpty()) {
            System.out.println("Cannot load. Either no containers or no ships waiting.");
            return;
        }
        Container c = containerStack.pop();
        Ship s = shipQueue.poll();

        System.out.println("Loaded: " + c + " → " + s);
        System.out.println("Remaining containers: " + containerStack.size());
        System.out.println("Remaining ships waiting: " + shipQueue.size());
    }
}
