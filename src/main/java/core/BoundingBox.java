package core;

import java.awt.*;

public class BoundingBox {
    private final Rectangle _bounds;

    public BoundingBox(Rectangle rectangle){
        _bounds = rectangle;
    }
    public BoundingBox(int x, int y, int width, int height) {
        _bounds = new Rectangle(x, y, width, height);
    }

    public boolean collidesWith(BoundingBox other) {
        return _bounds.intersects(other._bounds);
    }

    public Rectangle getBounds(){
        return _bounds;
    }

    public  static  BoundingBox of(Position position, Size size){
        return new BoundingBox(
                position.getIntX(),
                position.getIntY(),
                size.getWidth(),
                size.getHeight()
        );
    }
}
