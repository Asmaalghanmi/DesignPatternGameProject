// Builder interface: same role as RobotBuilder in the course slides.
public interface LevelBuilder {
    void buildMap();
    void buildDelay();
    void buildTotalBricks();
    Level getLevel();
}
