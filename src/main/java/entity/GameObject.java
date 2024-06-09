package entity;

import core.BoundingBox;
import core.Position;
import core.Size;
import display.Camera;

public abstract class GameObject implements IGameObject {

    protected Position _position;
    protected Size _size;
    protected Size collisionBoxSize;
    protected GameObject _parent;
    protected Position renderOffset;
    protected Position collisionBoxOffset;

    protected GameObject()
    {
        _position = new Position(50,50);
        _size = new Size(50,50);
        collisionBoxSize = new Size(48,64);
        collisionBoxOffset = new Position();
        renderOffset = new Position();
    }


    public void setPosition(Position position){
        _position = position;
    }

    @Override
    public Position getPosition() {
        Position pos = _position.copy();
        if(_parent != null){
            pos = pos.add(_parent.getPosition());
        }
        return  pos;
    }

    @Override
    public BoundingBox getBoundingBox(){
        return new BoundingBox(
            _position.getIntX(),
            _position.getIntY(),
            collisionBoxSize.getWidth(),
            collisionBoxSize.getHeight()
        );
    }

    @Override
    public boolean collidesWith(IGameObject other) {
        return getBoundingBox().collidesWith(other.getBoundingBox());
    }

    @Override
    public Position getRenderPosition(Camera camera){
        Position currentPosition = getPosition();
        Position cameraPosition = camera.getPosition();
        return new Position(
            currentPosition.getX() - cameraPosition.getX() - renderOffset.getX(),
            currentPosition.getY() - cameraPosition.getY() - renderOffset.getY()
        );
    }
    @Override
    public Size getSize() {
        return _size;
    }

    public void setParent(GameObject parent){
        _parent = parent;
    }
}
