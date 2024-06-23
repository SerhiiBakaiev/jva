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
    protected int  attackSpeed = 1050; // in milliseconds
    protected int attackDuration = 650; // in millisecond
    protected BoundingBox hitBounds;
    protected AttackingEntity(EntityController controller, SpriteSheet spriteSheet, int size, Vector2d origin) {
        super(controller, spriteSheet, size, origin);
    }

    @Override
    protected void onAnimate(State state) {
        Direction newDirection = Direction.fromMotion(motion);
        int currentAnimation = direction.getAnimationRow();
        if(isAttacking) {
            if(this.currentAnimation < 5){
                currentAnimation = this.currentAnimation + 5;
                setAnimation(currentAnimation , spriteSheet.getSpriteArray(currentAnimation), attackDuration / 100 );
            }
        }
        else if(motion.IsMoving()) {
            direction = newDirection;
            currentAnimation = direction.getAnimationRow();
            if(this.currentAnimation != currentAnimation || animation.isDelayUnset()) {
                setAnimation(currentAnimation,spriteSheet.getSpriteArray(currentAnimation), 5 );
            }
        }
        else {
            if(!isAttacking && currentAnimation > 4) {
                currentAnimation = this.currentAnimation - 5;
                setAnimation(currentAnimation , spriteSheet.getSpriteArray(currentAnimation), -1 );
            }
            else if(!isAttacking) {
                setAnimation(currentAnimation, spriteSheet.getSpriteArray(currentAnimation), -1);
            }
        }
    }

    @Override
    protected void onBeginUpdate(State state) {
        super.onBeginUpdate(state);
        isAttacking = controller.isAttacking();
    }

}
