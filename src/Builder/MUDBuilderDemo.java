package Builder;

public class MUDBuilderDemo {
    public static void main(String[] args) {
        IDungeonBuilder builder = new SimpleDungeonBuilder();

        builder.buildName();
        builder.buildRooms();
        builder.buildItems();
        builder.buildEnemies();

        Dungeon dungeon = builder.getDungeon();

        dungeon.describe();
    }
}