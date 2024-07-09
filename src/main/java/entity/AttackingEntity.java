package entity;

import controller.EntityController;
import core.BoundingBox;
import core.Direction;
import core.Vector2d;
import game.state.State;
import gfx.SpriteSheet;

public abstract class AttackingEntity extends Entity
{
    private int _healthPoint = 100;
    protected boolean isAttacking = false;
    protected double attacktime;
    protected int  attackSpeed = 1050; // in milliseconds
    protected int attackDuration = 650; // in millisecond
    protected BoundingBox hitBounds;
    protected AttackingEntity(EntityController controller, SpriteSheet spriteSheet, int size, Vector2d origin) {
        super(controller, spriteSheet, size, origin);
    }

    @Override
    protected void onAnimate(State state) {
        Direction newDirection = Direction.fromMotion(motion);
        System.out.println("Direction: " + newDirection);
        if(motion.IsMoving()) {
            direction = newDirection;
            int currentAnimation = getAnimationRow(direction);
            if(this.currentAnimation != currentAnimation || animation.isDelayUnset()) {
                setAnimation(currentAnimation, 5 );
            }
        }
        else if(controller.isAttacking()){

        }
        else {
            //Idle
            setAnimation(this.currentAnimation, -1 );
        }
    }

    @Override
    protected void onBeginUpdate(State state) {
        super.onBeginUpdate(state);
    }

    protected  void setAnimation(int animation, int delay){
        setAnimation(animation, spriteSheet.getSpriteArray(animation), delay );
    }
}
