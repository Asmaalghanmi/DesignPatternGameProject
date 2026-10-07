/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmaa
 */
import java.awt.Color;

public class NormalBrick extends Brick {

    public NormalBrick() {
        super(1);
    }

    @Override
    public Color getColor() {
        return Color.white;
    }
}