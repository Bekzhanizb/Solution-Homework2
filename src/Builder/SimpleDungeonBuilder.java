package Builder;

import Prototype.Room;

public class SimpleDungeonBuilder implements IDungeonBuilder {
    private final Dungeon dungeon;

    public SimpleDungeonBuilder() {
        this.dungeon = new Dungeon();
    }

    @Override
    public void buildName() {
        dungeon.setName("Simple Dungeon");
    }

    @Override
    public void buildRooms() {
        Room entrance = new Room("Entrance");
        Room clonedRoom = (Room) entrance.clone();
        Room clonedRoom2 = (Room) clonedRoom.clone();

        clonedRoom.setName("Main Hall");
        clonedRoom2.setName("Treasure Room");

        dungeon.addRoom(entrance);
        dungeon.addRoom(clonedRoom);
        dungeon.addRoom(clonedRoom2);

        dungeon.connectRooms(entrance, "north", clonedRoom);
        dungeon.connectRooms(clonedRoom, "south", entrance);
        dungeon.connectRooms(clonedRoom, "east", clonedRoom2);
        dungeon.connectRooms(clonedRoom2, "west", clonedRoom);
    }

    @Override
    public void buildItems() {
        dungeon.addItem("Sword");
        dungeon.addItem("Potion");
    }

    @Override
    public void buildEnemies() {
        dungeon.addEnemy("Goblin");
        dungeon.addEnemy("Skeleton");
    }

    @Override
    public Dungeon getDungeon() {
        return dungeon;
    }
}