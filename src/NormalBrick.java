import java.awt.Color;

public class NormalBrick implements Brick {

    private int hitsLeft = 1;

    @Override
    public boolean hit() {
        hitsLeft--;
        return hitsLeft <= 0;
    }

    @Override
    public boolean isDestroyed() {
        return hitsLeft <= 0;
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }
}