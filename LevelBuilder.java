/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmaa
 */
import java.util.Random;

public class LevelBuilder {

    // defaults = the original first level
    private int rows = 3;
    private int cols = 7;
    private int strongPercent = 0;   // % of strong bricks, 0 to 100
    private int delay = 8;           // timer delay in ms (smaller = faster ball)

    public LevelBuilder rows(int rows) {
        this.rows = rows;
        return this;
    }

    public LevelBuilder cols(int cols) {
        this.cols = cols;
        return this;
    }

    public LevelBuilder strongPercent(int strongPercent) {
        this.strongPercent = strongPercent;
        return this;
    }

    public LevelBuilder delay(int delay) {
        this.delay = delay;
        return this;
    }

    public Level build() {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalStateException("rows and cols must be positive");
        }

        BrickCreator normalCreator = new NormalBrickCreator();
        BrickCreator strongCreator = new StrongBrickCreator();
        Random random = new Random();

        Brick[][] bricks = new Brick[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // the Factory Method decides which brick is created
                BrickCreator creator = (random.nextInt(100) < strongPercent)
                        ? strongCreator : normalCreator;
                bricks[i][j] = creator.createBrick();
            }
        }

        return new Level(new MapGenerator(bricks), delay, rows * cols);
    }
}