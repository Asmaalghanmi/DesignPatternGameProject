/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author asmaa
 */
public class LevelDirector {

    public static final int LEVEL_COUNT = 3;

    public Level easy() {
        return new LevelBuilder().rows(3).cols(7).strongPercent(0).delay(8).build();
    }

    public Level medium() {
        return new LevelBuilder().rows(4).cols(9).strongPercent(30).delay(7).build();
    }

    public Level hard() {
        return new LevelBuilder().rows(4).cols(12).strongPercent(50).delay(6).build();
    }

    public Level forLevel(int level) {
        switch (level) {
            case 1:  return easy();
            case 2:  return medium();
            case 3:  return hard();
            default: throw new IllegalArgumentException("No such level: " + level);
        }
    }
}