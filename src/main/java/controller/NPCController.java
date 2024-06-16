package controller;

import core.Position;
import core.Vector2d;

public class NPCController implements EntityController {

    private  boolean _up;
    private  boolean _right;
    private  boolean _down;
    private  boolean _left;

    @Override
    public boolean isRequestingUp() {
        return _up;
    }

    @Override
    public boolean isRequestingDown() {
        return _down;
    }

    @Override
    public boolean isRequestingRight() {
        return _right;
    }

    @Override
    public boolean isRequestingLeft() {
        return _left;
    }

    @Override
    public boolean isRequestingSpace() {
        return false;
    }

    @Override
    public boolean isRequestingAction() {
        return false;
    }

    public boolean moveToTarget(Position target, Position current) {
        Vector2d targetVector = target.getVector();
        Vector2d currentVector = current.getVector();
        Vector2d diff = targetVector.diff(currentVector);
        double deltaX = diff.getX();
        double deltaY = diff.getY();

        _up = deltaY < 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
        _right = deltaX > 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
        _down = deltaY > 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
        _left = deltaX < 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
        if(!_up && !_down && !_left && !_right) {
            int z =3;

        }
        return currentVector.distanceTo(targetVector) < Position.PROXIMITY_RANGE;
    }

    public void stop() {
        _up = _down = _left = _right = false;
    }
}
