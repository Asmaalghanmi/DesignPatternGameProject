/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmaa
 */
public class Level {

    private final MapGenerator map;
    private final int delay;
    private final int totalBricks;

    public Level(MapGenerator map, int delay, int totalBricks) {
        this.map = map;
        this.delay = delay;
        this.totalBricks = totalBricks;
    }

    public MapGenerator getMap() { return map; }
    public int getDelay() { return delay; }
    public int getTotalBricks() { return totalBricks; }
}