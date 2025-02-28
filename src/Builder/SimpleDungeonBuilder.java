package Builder;

import Prototype.Room;

public class SimpleDungeonBuilder implements IDungeonBuilder {
    private Dungeon dungeon;

    public SimpleDungeonBuilder() {
        this.dungeon = new Dungeon();
    }

    @Override
    public void buildName() {
        dungeon.setName("Simple Dungeon");
    }

    @Override
    public void buildRooms() {
        dungeon.addRoom(new Room("Entrance"));
        dungeon.addRoom(new Room("Main Hall"));
        dungeon.addRoom(new Room("Treasure Room"));
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