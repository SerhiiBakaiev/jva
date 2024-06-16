package entity;

import core.BoundingBox;
import core.Position;
import core.Size;
import display.Camera;
import gfx.Animation;

public abstract class GameObject implements IGameObject {

    protected Position position;
    protected Size size;
    protected IGameObject parent;


    protected GameObject(int size) {
        position = new Position(0, 0);
        this.size = new Size(size, size);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        Position pos = position.copy();
        if (parent != null) {
            pos = pos.add(parent.getPosition());
        }
        return pos;
    }

    @Override
    public  BoundingBox getBoundingBox(){
        Position positionWithMotion = position.copy();

        return new BoundingBox(
                position.getVector(),
                getSize().getHeight(),
                getSize().getHeight()
        );
    }

    @Override
    public boolean collidesWith(IGameObject other) {
        return getBoundingBox().collidesWith(other.getBoundingBox());
    }

    @Override
    public Position getRenderPosition(Camera camera) {
        Position currentPosition = getPosition();
        Position cameraPosition = camera.getPosition();
        return new Position(
                currentPosition.getX() - cameraPosition.getX(),
                currentPosition.getY() - cameraPosition.getY()
        );
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
