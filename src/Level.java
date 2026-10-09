// Product: same role as Robot in the course slides.
public class Level {
    private MapGenerator map;
    private int delay;
    private int totalBricks;

    public Level() { }

    public void setMap(MapGenerator map) { this.map = map; }
    public void setDelay(int delay) { this.delay = delay; }
    public void setTotalBricks(int totalBricks) { this.totalBricks = totalBricks; }

    public MapGenerator getMap() { return map; }
    public int getDelay() { return delay; }
    public int getTotalBricks() { return totalBricks; }
}
