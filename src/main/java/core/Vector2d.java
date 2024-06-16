package core;

public class Vector2d {
    private final double x;
    private final double y;

    public static Vector2d Zero = new Vector2d();

    public Vector2d(){
        x = 0;
        y = 0;
    }
    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d multiply(double speed) {
        return new Vector2d(x * speed, y * speed);
    }

    public double length(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d normalize() {
        double length = length();
        double x = this.x == 0.0 ? 0 : this.x / length;
        double y = this.y == 0.0 ? 0 : this.y / length;
        return new Vector2d(x, y);
    }
    public double getX() {
        return x;
    }

    public Vector2d diff(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);
    }

    public double distanceTo(Vector2d other) {
        return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y) );
    }
    public boolean isEqual(Vector2d other, double tolerance) {
        return Math.abs(x - other.x) < tolerance && Math.abs(y - other.y) < tolerance;
    }

    public double getY() {
        return y;
    }

    public Vector2d subtract(Vector2d vector) {
        return new Vector2d(x - vector.x, y - vector.y);
    }
}
