package core;

public class Vector2d {
    private final double _x;
    private final double _y;

    public static Vector2d Zero = new Vector2d();

    public Vector2d(){
        _x = 0;
        _y = 0;
    }
    public Vector2d(double _x, double _y) {
        this._x = _x;
        this._y = _y;
    }

    public Vector2d multiply(double speed) {
        return new Vector2d(_x * speed, _y * speed);
    }

    public double length(){
        return Math.sqrt(Math.pow(_x, 2) + Math.pow(_y, 2));
    }
    public Vector2d add(Vector2d other) {
        return new Vector2d(_x + other._x, _y + other._y);
    }

    public Vector2d normalize() {
        double length = length();
        double x = _x == 0.0 ? 0 : _x / length;
        double y = _y == 0.0 ? 0 : _y / length;
        return new Vector2d(x, y);
    }
    public double getX() {
        return _x;
    }

    public Vector2d diff(Vector2d other) {
        return new Vector2d(_x - other._x, _y - other._y);
    }

    public boolean isEqual(Vector2d other, double tolerance) {
        return Math.abs(_x - other._x) < tolerance && Math.abs(_y  - other._y) < tolerance;
    }

    public double getY() {
        return _y;
    }
}
