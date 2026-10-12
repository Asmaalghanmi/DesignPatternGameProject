public class LevelDirector {

    public static final int LEVEL_COUNT = 3;

    private final LevelBuilder levelBuilder;

    public LevelDirector(LevelBuilder levelBuilder) {
        this.levelBuilder = levelBuilder;
    }

    public void makeLevel() {
        levelBuilder.buildMap();
        levelBuilder.buildDelay();
        levelBuilder.buildTotalBricks();
    }

    public Level getLevel() {
        return levelBuilder.getLevel();
    }
}