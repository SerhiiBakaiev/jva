package entity;

import controller.EntityController;
import core.BoundingBox;
import core.Vector2d;
import game.state.State;
import gfx.SpriteSheet;

public class AttackingEntity extends Entity
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
    protected void handleCollision(IGameObject gameObject) {

    }

    @Override
    protected void onAnimate(State state) {
        if(isAttacking) {
            if(currentAnimation < 5){
                int currentAnimation = this.currentAnimation + 5;
                setAnimation(currentAnimation , spriteSheet.getSpriteArray(currentAnimation), attackDuration / 100 );
            }
        }
        else if(motion.IsMoving()) {
            int currentAnimation = direction.getAnimationRow();
            if(this.currentAnimation != currentAnimation || animation.isDelayUnset()) {
                setAnimation(currentAnimation,spriteSheet.getSpriteArray(currentAnimation), 5 );
            }
        }
        else {
            if(!isAttacking && currentAnimation > 4) {
                int currentAnimation = this.currentAnimation - 5;
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
