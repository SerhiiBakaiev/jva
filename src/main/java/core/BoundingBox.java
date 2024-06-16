package core;

import java.awt.*;

public class BoundingBox {
    private final Rectangle bounds;
    private int size;
    public BoundingBox(Rectangle rectangle){
        bounds = rectangle;
    }
    public BoundingBox(Vector2d orig, int width, int height) {
        bounds = new Rectangle((int)orig.getX(), (int)orig.getY(), width, height);
        size = Math.max(width, height);
    }
    public BoundingBox(Vector2d orig, int r) {
        bounds = new Rectangle((int)orig.getX(), (int)orig.getY(), r, r);
    }

    public boolean collidesWith(BoundingBox other) {
        return bounds.intersects(other.bounds);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public double getWidth(){
        return bounds.getWidth();
    }

    public double getHeight(){
        return bounds.getHeight();
    }

    public static BoundingBox empty(){
        return new BoundingBox(Vector2d.Zero, 0, 0);
    }
    public  static  BoundingBox of(Vector2d vector2d, Size size){
        return new BoundingBox(
                vector2d,
                size.getWidth(),
                size.getHeight()
        );
    }
}
