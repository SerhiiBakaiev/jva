package entity;

import controller.EntityController;
import core.BoundingBox;
import core.Direction;
import core.Motion;
import core.Vector2d;
import game.state.State;
import gfx.SpriteSheet;

import java.io.IOException;

public class Player2 extends Entity {

    private int _healthPoint = 100;
    private String state = "walking";
    protected int  attackSpeed = 1050; // in milliseconds
    protected int attackDuration = 650; // in milliseconds
    private BoundingBox hitBounding;

    public Player2(EntityController controller) throws IOException {
        super(controller,
                new SpriteSheet("/sprites/player/wizardPlayer.png", 64, 64),
                64,
                Vector2d.Zero
        );

        motion = new Motion(4);
        animation.setNumFrames(4, Direction.U.getAnimationRow());
        animation.setNumFrames(4, Direction.D.getAnimationRow());
        animation.setNumFrames(4, Direction.U.getAnimationRow() + 5);
        animation.setNumFrames(4, Direction.R.getAnimationRow() + 5);
        animation.setNumFrames(4, Direction.L.getAnimationRow() + 5);
        animation.setNumFrames(4, Direction.D.getAnimationRow() + 5);

    }

    public int getHealthPoint() {
        return _healthPoint;

    }

    @Override
    protected void handleCollision(IGameObject gameObject) {

    }


    @Override
    protected void onUpdate(State state) {
        super.onUpdate(state);
    }


    @Override
    protected void onBeginUpdate(State state) {
        super.onUpdate(state);
    }

    @Override
    protected void onAnimate(State state) {

        if(controller.isRequestingSpace()){
            System.out.println("Spacing");
            if(currentAnimation < 5){
                int currentAnimation = this.currentAnimation + 5;
                setAnimation(currentAnimation , spriteSheet.getSpriteArray(currentAnimation), attackDuration / 100 );
                System.out.println("Attacking ");
            }
        }else{
            super.onAnimate(state);
        }
    }
}
