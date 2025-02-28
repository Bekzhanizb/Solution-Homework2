package Builder;

public interface IDungeonBuilder {
    void buildName();
    void buildRooms();
    void buildItems();
    void buildEnemies();
    Dungeon getDungeon();
}