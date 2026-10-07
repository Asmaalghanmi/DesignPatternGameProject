/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmaa
 */
public class StrongBrickCreator extends BrickCreator {

    @Override
    public Brick createBrick() {
        return new StrongBrick();
    }
}