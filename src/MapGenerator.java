/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmaa
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {

    public Brick[][] map;
    public int brickWidth;
    public int brickHeight;

    // The bricks are now created by LevelBuilder (through the brick factories)
    public MapGenerator(Brick[][] bricks) {
        map = bricks;
        int row = map.length;
        int col = map[0].length;
        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (!map[i][j].isDestroyed()) {
                    g.setColor(map[i][j].getColor());
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);

                    // this is just to show separate brick, game can still run without it
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    // Returns true if the brick was destroyed by this hit
    public boolean hitBrick(int row, int col) {
        return map[row][col].hit();
    }
}