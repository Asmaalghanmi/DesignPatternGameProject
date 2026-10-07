/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmaa
 */
import java.awt.Color;

public abstract class Brick {

    protected int hitsLeft;

    protected Brick(int hits) {
        this.hitsLeft = hits;
    }

    // Registers one hit. Returns true if this hit destroyed the brick.
    public boolean hit() {
        hitsLeft--;
        return hitsLeft <= 0;
    }

    public boolean isDestroyed() {
        return hitsLeft <= 0;
    }

    public abstract Color getColor();
}
