
import java.awt.Color;

public class StrongBrick implements Brick {

    private int hitsLeft = 3;

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
        if (hitsLeft == 3) {
            return Color.RED;
        } else if (hitsLeft == 2) {
            return Color.ORANGE;
        } else {
            return Color.YELLOW;
        }
    }
}
