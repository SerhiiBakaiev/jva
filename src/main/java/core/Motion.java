package core;

import controller.EntityController;

public class Motion {
    private  Vector2d _vector;
    private  double _speed;

    public Motion(double speed) {
        _speed = speed;
        _vector = new Vector2d(0,0);
    }

    public void update(EntityController controller) {
        //_position = new Position(_position.getX() + 1, _position.getY() + 1);
        int deltaX = 0;
        int deltaY = 0;
        if(controller.isRequestingUp())
        {
            deltaY--;
        }
        if(controller.isRequestingDown())
        {
            deltaY++;
        }
        if(controller.isRequestingLeft())
        {
            deltaX--;
        }
        if(controller.isRequestingRight())
        {
            deltaX++;
        }

        _vector = new Vector2d(deltaX,deltaY)
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
        return _vector;
    }

    public boolean IsMoving() {
        return _vector.length() > 0;
    }

    public void stop() {
        _vector = Vector2d.Zero;
    }
}
