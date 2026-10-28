public class BrickFactory {

    public Brick createBrick(String type) {
        if (type.equalsIgnoreCase("normal")) {
            return new NormalBrick();
        } else if (type.equalsIgnoreCase("strong")) {
            return new StrongBrick();
        }
        throw new IllegalArgumentException("Unknown brick type");
    }
}