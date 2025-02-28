import Builder.Dungeon;

import java.util.Scanner;

public class MUDController {
    private final Player player;
    private final Dungeon dungeon;
    private boolean running;

    public MUDController(Player player, Dungeon dungeon) {
        this.player = player;
        this.dungeon = dungeon;
        this.running = true;
    }

    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine();
            handleInput(input);
        }
        scanner.close();
    }

    public void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String arg = parts.length > 1 ? parts[1] : null;

        switch (command) {
            case "look":
                dungeon.describe();
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

    private void move(String direction) {
        System.out.println("You move " + direction + ".");
        dungeon.describe(); // Описание новой комнаты
    }

    private void pickUp(String item) {
        if (dungeon.getItems().contains(item)) {
            player.addItem(item);
            System.out.println("You picked up the " + item + ".");
        } else {
            System.out.println("There is no " + item + " here.");
        }
    }

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