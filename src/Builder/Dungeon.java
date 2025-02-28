package Builder;

import Prototype.Room;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private String name;
    private final List<Room> rooms;
    private final List<String> items;
    private final List<String> enemies;
    private Room currentRoom;

    public Dungeon() {
        this.rooms = new ArrayList<>();
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRoom(Room room) {
        rooms.add(room);
        if (currentRoom == null) {
            currentRoom = room; // Устанавливаем первую комнату как текущую
        }
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void addEnemy(String enemy) {
        enemies.add(enemy);
    }

    public List<String> getItems() {
        return items;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void describe() {
        System.out.println("Dungeon: " + name);
        System.out.println("Current Room: " + currentRoom);
        System.out.println("Items: " + items);
        System.out.println("Enemies: " + enemies);
    }
}