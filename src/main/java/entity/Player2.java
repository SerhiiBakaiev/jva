package entity;

import controller.EntityController;
import core.Direction;
import core.Motion;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public class Player2 extends MovingEntity{

    private int _healthPoint = 100;

    public Player2(EntityController controller, SpriteLibrary spriteLibrary){
        super(controller, spriteLibrary);
        _direction = Direction.D;
        _motion = new Motion(4);
        _animationManager = new AnimationManager(spriteLibrary.getSpriteSet("sprites/unit/character/main"));
        _animationManager.playAnimation("walking");
    }

    public int getHealthPoint(){
        return _healthPoint;
    }
    @Override
    protected void handleCollision(IGameObject gameObject) {

    }
}
