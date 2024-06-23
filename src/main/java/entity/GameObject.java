package entity;

import core.BoundingBox;
import core.Size;
import core.Vector2d;
import display.Camera;

public abstract class GameObject implements IGameObject {

    protected Vector2d origin;
    protected Size size;
    protected IGameObject parent;


    protected GameObject(int size,  Vector2d origin) {
        this.origin = origin;
        this.size = new Size(size, size);
    }
    protected GameObject(int size) {
        this(size, Vector2d.Zero);
    }

    @Override
    public Vector2d getOrigin() {
        return origin;
    }

    @Override
    public  BoundingBox getBoundingBox(){
        return new BoundingBox(
                origin,
                getSize().getHeight(),
                getSize().getHeight()
        );
    }

    @Override
    public boolean collidesWith(IGameObject other) {
        return getBoundingBox().collidesWith(other.getBoundingBox());
    }

    @Override
    public Vector2d getRenderPosition(Camera camera) {
        Vector2d currentPosition = getOrigin();
        Vector2d cameraPosition = camera.getPosition();
        return currentPosition.subtract(cameraPosition);
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setParent(IGameObject parent) {
        this.parent = parent;
    }
}
