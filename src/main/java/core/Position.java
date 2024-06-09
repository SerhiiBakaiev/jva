package core;

public class Position {

    public  static int PROXIMITY_RANGE = 5;
    private Vector2d _vector;

    public Position(){
        _vector = Vector2d.Zero;
    }
    public Position(Vector2d vector) {
        _vector = vector;
    }
    public Position(int x, int y) {
        _vector = new Vector2d(x, y);
    }

    public Position(double x, double y) {
        _vector = new Vector2d(x, y);
    }

    public int getIntX() {
        return (int) Math.round(_vector.getX());
    }

    public int getIntY() {
        return (int) Math.round(_vector.getY());
    }

    public double getX() {
        return _vector.getX();
    }

    public double getY() {
        return _vector.getY();
    }

    public void setX(double x) {
        _vector = new Vector2d(x, getIntY());
    }

    public Position copy(){
        return new Position(getIntX(), getIntY());
    }

    public void setY(double y) {
        _vector = new Vector2d(_vector.getX(), y);
    }

    public void setXY(double x, double y) {
        _vector = new Vector2d(x, y);
    }

    public void apply(Motion motion) {
        Vector2d movementVector = motion.getVector();
        _vector = _vector.add(movementVector);
    }

    public Vector2d getVector() {
        return _vector;
    }

    public boolean isRangeOf(Position position) {
        return _vector.isEqual(position._vector, PROXIMITY_RANGE);
    }

    public Position add(Position other) {
        return  new Position(
            _vector.add(other._vector)
        );
    }
}
