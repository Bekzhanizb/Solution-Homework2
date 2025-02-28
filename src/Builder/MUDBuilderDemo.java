package Builder;

public class MUDBuilderDemo {
    public static void main(String[] args) {
        // Создаем строителя
        IDungeonBuilder builder = new SimpleDungeonBuilder();

        // Пошагово строим подземелье
        builder.buildName();
        builder.buildRooms();
        builder.buildItems();
        builder.buildEnemies();

        // Получаем готовое подземелье
        Dungeon dungeon = builder.getDungeon();

        // Описываем подземелье
        dungeon.describe();
    }
}