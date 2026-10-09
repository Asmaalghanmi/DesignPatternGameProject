import java.util.Random;

// Concrete Builder: same role as OldRobotBuilder in the course slides.
public class ConcreteLevelBuilder implements LevelBuilder {
    private final Level level;
    private final int rows;
    private final int cols;
    private final int strongPercent;
    private final int delay;

    public ConcreteLevelBuilder(int rows, int cols, int strongPercent, int delay) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("rows and cols must be positive");
        }
        if (strongPercent < 0 || strongPercent > 100) {
            throw new IllegalArgumentException("strongPercent must be between 0 and 100");
        }
        if (delay <= 0) {
            throw new IllegalArgumentException("delay must be positive");
        }
        this.level = new Level();
        this.rows = rows;
        this.cols = cols;
        this.strongPercent = strongPercent;
        this.delay = delay;
    }

    @Override
    public void buildMap() {
        BrickCreator normalCreator = new NormalBrickCreator();
        BrickCreator strongCreator = new StrongBrickCreator();
        Random random = new Random();
        Brick[][] bricks = new Brick[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                BrickCreator creator = random.nextInt(100) < strongPercent
                        ? strongCreator : normalCreator;
                bricks[i][j] = creator.createBrick();
            }
        }
        level.setMap(new MapGenerator(bricks));
    }

    @Override
    public void buildDelay() {
        level.setDelay(delay);
    }

    @Override
    public void buildTotalBricks() {
        level.setTotalBricks(rows * cols);
    }

    @Override
    public Level getLevel() {
        return level;
    }
}
