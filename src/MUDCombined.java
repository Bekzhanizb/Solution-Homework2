import Builder.Dungeon;
import Builder.IDungeonBuilder;
import Builder.SimpleDungeonBuilder;

public class MUDCombined {
    private final Player player;
    private final Dungeon dungeon;
    private final MUDController controller;

    public MUDCombined() {
        this.player = new Player();
        IDungeonBuilder builder = new SimpleDungeonBuilder();
        builder.buildName();
        builder.buildRooms();
        builder.buildItems();
        builder.buildEnemies();
        this.dungeon = builder.getDungeon();
        this.controller = new MUDController(player, dungeon);
    }

    public void startGame() {
        controller.runGameLoop();
    }

    public static void main(String[] args) {
        MUDCombined game = new MUDCombined();
        game.startGame();
    }
}