package Builder;

import Prototype.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dungeon {
    private String name;
    private final List<Room> rooms;
    private final List<String> items;
    private final List<String> enemies;
    private Room currentRoom;
    private final Map<Room, Map<String, Room>> exists;

    public Dungeon() {
        this.rooms = new ArrayList<>();
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.exists = new HashMap<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRoom(Room room) {
        rooms.add(room);
        if (currentRoom == null) {
            currentRoom = room;
        }
        exists.put(room, new HashMap<>());
    }
    public void connectRooms(Room from, String dir, Room to){
        exists.get(from).put(dir, to);
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
    public Room getRoomInDirection(String direction) {
        return exists.get(currentRoom).get(direction);
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void describe() {
        System.out.println("Dungeon: " + name);
        System.out.println("Current Room: " + currentRoom);
        System.out.println("Items: " + items);
        System.out.println("Enemies: " + enemies);
    }
}