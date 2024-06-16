package core;

public class Position {

    public static int PROXIMITY_RANGE = 5;
    private Vector2d vector;

    public Position() {
        vector = Vector2d.Zero;
    }

    public Position(Vector2d vector) {
        this.vector = vector;
    }

    public Position(int x, int y) {
        vector = new Vector2d(x, y);
    }

    public Position(double x, double y) {
        vector = new Vector2d(x, y);
    }

    public int getIntX() {
        return (int) Math.round(vector.getX());
    }

    public int getIntY() {
        return (int) Math.round(vector.getY());
    }

    public double getX() {
        return vector.getX();
    }

    public double getY() {
        return vector.getY();
    }

    public void setX(double x) {
        vector = new Vector2d(x, getIntY());
    }

    public Position copy() {
        return new Position(getIntX(), getIntY());
    }

    public void setY(double y) {
        vector = new Vector2d(vector.getX(), y);
    }

    public void setXY(double x, double y) {
        vector = new Vector2d(x, y);
    }

    public Position apply(Motion motion) {
        Vector2d movementVector = motion.getVector();
        return new Position(vector.add(movementVector));
    }

    public Vector2d getVector() {
        return vector;
    }

    public boolean isRangeOf(Position position, double delta) {
        return vector.isEqual(position.vector, delta);
    }

    public Position add(Position other) {
        return new Position(
                vector.add(other.vector)
        );
    }

    public Position subtract(Position collisionBoxOffset) {
        return new Position(
                vector.subtract(collisionBoxOffset.vector)
        );
    }
}
