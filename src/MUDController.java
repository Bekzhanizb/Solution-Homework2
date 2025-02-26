import java.util.Scanner;

/**
 * MUDController (Skeleton):
 * A simple controller that reads player input and orchestrates
 * basic commands like look around, move, pick up items,
 * check inventory, show help, etc.
 */
public class MUDController {

    private Player player;
    private boolean running;

    /**
     * Constructs the controller with a reference to the current player.
     */
    public MUDController(Player player) {
        this.player = player;
        this.running = true;
    }

    /**
     * Main loop method that repeatedly reads input from the user
     * and dispatches commands until the game ends.
     */
    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine();
            handleInput(input);
        }
        scanner.close();
    }

    /**
     * Handle a single command input (e.g. 'look', 'move forward', 'pick up sword').
     */
    public void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arg = parts.length > 1 ? parts[1] : null;

        switch (command) {
            case "look":
                lookAround();
                break;
            case "move":
                if (arg != null) {
                    move(arg);
                } else {
                    System.out.println("Please specify a direction.");
                }
                break;
            case "pick":
                if (arg != null && arg.startsWith("up ")) {
                    pickUp(arg.substring(3));
                } else {
                    System.out.println("Usage: pick up <item>");
                }
                break;
            case "inventory":
                checkInventory();
                break;
            case "help":
                showHelp();
                break;
            case "quit":
                running = false;
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Unknown command. Type 'help' for a list of commands.");
        }
    }

    /**
     * Look around the current room: describe it and show items/NPCs.
     */
    private void lookAround() {
        // Assuming the player has a reference to the current room
        System.out.println("You are in a room. There are doors to the north, south, east, and west.");
        System.out.println("You see a sword on the ground.");
    }

    /**
     * Move the player in a given direction (forward, back, left, right).
     */
    private void move(String direction) {
        // Assuming the player has a reference to the current room and can move to adjacent rooms
        switch (direction.toLowerCase()) {
            case "north":
            case "south":
            case "east":
            case "west":
                System.out.println("You move " + direction + ".");
                lookAround(); // Describe the new room
                break;
            default:
                System.out.println("Invalid direction. Please specify north, south, east, or west.");
        }
    }

    /**
     * Pick up an item (e.g. "pick up sword").
     */
    private void pickUp(String item) {
        // Assuming the current room has a list of items
        if (item.equals("sword")) {
            player.addItem(item);
            System.out.println("You picked up the " + item + ".");
        } else {
            System.out.println("There is no " + item + " here.");
        }
    }

    /**
     * Check the player's inventory.
     */
    private void checkInventory() {
        if (player.inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Your inventory contains:");
            for (String item : player.inventory) {
                System.out.println("- " + item);
            }
        }
    }

    /**
     * Show help commands
     */
    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("  look - Look around the current room");
        System.out.println("  move <direction> - Move in a direction (north, south, east, west)");
        System.out.println("  pick up <item> - Pick up an item");
        System.out.println("  inventory - Check your inventory");
        System.out.println("  help - Show this help message");
        System.out.println("  quit - Quit the game");
    }
}