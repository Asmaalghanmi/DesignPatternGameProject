import java.awt.Color;

public interface Brick {
    boolean hit();
    boolean isDestroyed();
    Color getColor();
}