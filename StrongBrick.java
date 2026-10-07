/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmaa
 */
import java.awt.Color;

public class StrongBrick extends Brick {

    public StrongBrick() {
        super(3);
    }

    @Override
    public Color getColor() {
        if (hitsLeft >= 3) {
            return Color.MAGENTA;
        }
        if (hitsLeft == 2) {
            return Color.CYAN;
        }
        return Color.YELLOW;
    }
}