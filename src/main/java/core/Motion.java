package core;

import controller.EntityController;

public class Motion {
    private  Vector2d movementVector;
    private  double _speed;

    public Motion(double speed) {
        _speed = speed;
        movementVector = new Vector2d(0,0);
    }

    public void update(EntityController controller) {
        //_position = new Position(_position.getX() + 1, _position.getY() + 1);
        int deltaX = 0;
        int deltaY = 0;
        if(controller.isRequestingUp())
        {
            deltaY--;
        }
        else if(controller.isRequestingDown())
        {
            deltaY++;
        }
        else if(controller.isRequestingLeft())
        {
            deltaX--;
        }
        else if(controller.isRequestingRight())
        {
            deltaX++;
        }

        movementVector = new Vector2d(deltaX,deltaY)
                .normalize()
                .multiply(_speed);
    }

    public double getSpeed(){
        return _speed;
    }

    public void multiplySpeed(double multiplier){
        _speed = _speed * multiplier;
    }
    public Vector2d getVector() {
        return movementVector;
    }

    public Vector2d applyTo(Vector2d vector) {
        return vector.add(movementVector);
    }

    public boolean IsMoving() {
        return movementVector.length() > 0;
    }

    public void stop() {
        movementVector = Vector2d.Zero;
    }
}
