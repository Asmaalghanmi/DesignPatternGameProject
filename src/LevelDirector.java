// Director: same role as RobotEngineer in the course slides.
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

    // Chooses the settings; construction itself is performed by the Director.
    public static Level forLevel(int number) {
        LevelBuilder builder;
        switch (number) {
            case 1: builder = new ConcreteLevelBuilder(3, 7, 0, 8); break;
            case 2: builder = new ConcreteLevelBuilder(4, 9, 30, 7); break;
            case 3: builder = new ConcreteLevelBuilder(4, 12, 50, 6); break;
            default: throw new IllegalArgumentException("No such level: " + number);
        }
        LevelDirector director = new LevelDirector(builder);
        director.makeLevel();
        return director.getLevel();
    }
}
