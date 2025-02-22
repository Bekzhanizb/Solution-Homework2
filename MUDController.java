package source;

import source.AbstractFactory.IMUDAbstractFactory;
import source.AbstractFactory.FantasyMUDFactory;
import source.AbstractFactory.SciFiMUDFactory;
import source.FactoryMethod.ConcreteMUDGameEntityFactory;
import source.Item.Item;
import source.Item.NPC;
import source.Item.Room;
import source.Player.Player;

import java.util.Scanner;

public class MUDController {

    private final Player player;
    private boolean running;
    private final Scanner scanner;
    private IMUDAbstractFactory factory;
    private ConcreteMUDGameEntityFactory entityFactory = new ConcreteMUDGameEntityFactory();
    private Room currentRoom;

    public MUDController() {
        this.player = new Player("Bekezhan");
        this.running = true;
        this.scanner = new Scanner(System.in);
        this.factory = new FantasyMUDFactory();
        this.currentRoom = initializeGameAreas();
    }


    public void runGameLoop() {
        System.out.println("Choose a theme for your adventure: 1. Fantasy, 2. SCiFi");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            factory = new FantasyMUDFactory();
            System.out.println("You have chosen the Fantasy theme. Enjoy your game " + getPlayer().getName() + "!");
        } else if (choice == 2) {
            factory = new SciFiMUDFactory();
            System.out.println("You have chosen the SCiFi theme. Enjoy your game " + getPlayer().getName() + "!");
        } else {
            System.out.println("Invalid choice.");
            scanner.close();
            return;
        }

        System.out.println("Welcome to the MUD game! Type 'help' for a list of commands.");
        lookAround();
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            handleInput(input);
        }
        scanner.close();
    }

    public void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String argument = parts.length > 1 ? parts[1] : null;

        switch (command) {
            case "look":
                lookAround();
                break;
            case "move":
                if (argument != null) {
                    move(argument);
                } else {
                    System.out.println("Please specify a direction (e.g., 'move north').");
                }
                break;
            case "pick":
                if (argument != null && argument.startsWith("up ")) {
                    pickUp(argument.substring(3));
                } else {
                    System.out.println("Please specify an item to pick up (e.g., 'pick up sword').");
                }
                break;
            case "inventory":
                checkInventory();
                break;
            case "talk":
                talk();
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

    private void lookAround() {
        System.out.println(currentRoom.getDescription());
        NPC npc = currentRoom.getNPC();
        if (npc != null) {
            npc.describe();
        }
        if (!currentRoom.getItems().isEmpty()) {
            System.out.println("Items in the room:");
            for (String item : currentRoom.getItems()) {
                System.out.println("- " + item);
            }
        }

    }

    private Room initializeGameAreas(){
        //Game Rooms
        Room startingRoom = factory.createRoom("Game Hub", "The entrance to the dungeon.");
        Room wardenRoom =  factory.createRoom("Warden Room", "It is a spacious but gloomy room filled " +
                                                    "with an atmosphere of control and power.");
        Room elvenGuardianRoom = factory.createRoom("The Elven Guardian Room", "It is a space where nature and magic serve protection and order.");
        Room starHall = factory.createRoom("Star Hall", "Everything in the room looks like you're " +
                                                    "in space, with stars, planets, and other galaxies and a portal twinkling " +
                                                    "all around. And you can travel between universes.");

        // Directions
        startingRoom.addExit("south", wardenRoom);
        startingRoom.addExit("north", elvenGuardianRoom);
        wardenRoom.addExit("north", startingRoom);
        elvenGuardianRoom.addExit("south", startingRoom);
        starHall.addExit("east", elvenGuardianRoom);
        elvenGuardianRoom.addExit("west", starHall);

        // Add items
        startingRoom.addItem("Sword");
        starHall.addItem("Portal Key");
        elvenGuardianRoom.addItem("Map");

        // Add NPC
        startingRoom.setNpc((NPC) entityFactory.createEntity("npc", "Mentor", "Gives you a guide for games"));
        starHall.setNpc((NPC) entityFactory.createEntity("npc", "Travel guide", "The guide can give tips on how to complete the task, find the right item or defeat the enemy."));
        elvenGuardianRoom.setNpc((NPC) entityFactory.createEntity("npc", "The Elven Guardian", "Guards do not attack first, but when threatening their home, nature, or people, they immediately come to the defense."));
        wardenRoom.setNpc((NPC) entityFactory.createEntity("npc", "Stone Guardian", "He prefers actions to words."));
        return startingRoom;

    }

    private void move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            getPlayer().setCurrentRoom(currentRoom);
            System.out.println("You move " + direction + ".");
            lookAround();
        } else {
            System.out.println("You can't go that way.");
        }

    }

    private void pickUp(String itemName) {
        String itemToPick = null;
        for (String item : currentRoom.getItems()) {
            if (item.equalsIgnoreCase(itemName)) {
                itemToPick = item;
                break;
            }
        }

        if (itemToPick != null) {
            getPlayer().addItem(new Item(itemToPick));
            currentRoom.getItems().remove(itemToPick);
            System.out.println("You picked up: " + itemToPick);
        } else {
            System.out.println("Item not found.");
        }
    }

    private void talk() {
        NPC npc = currentRoom.getNPC();
        if (npc != null) {
            System.out.println(npc.getName() + " says: \"" + npc.getDialogue() + "\"");
        } else {
            System.out.println("There's no one here to talk to.");
        }
    }

    private void checkInventory() {
        for(int i = 0; i < getPlayer().getInventory().size(); i++) {
            System.out.println(i + ". " + getPlayer().getInventory().get(i).getName());
        }
    }

    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("- look: Describe the current room.");
        System.out.println("- move <direction>: Move in a specified direction (e.g., north, south).");
        System.out.println("- pick up <item>: Pick up an item in the room.");
        System.out.println("- inventory: Check your inventory.");
        System.out.println("- talk: Talk to an NPC in the room.");
        System.out.println("- help: Show this help message.");
        System.out.println("- quit: Exit the game.");
    }

    public Player getPlayer() {
        return player;
    }

}