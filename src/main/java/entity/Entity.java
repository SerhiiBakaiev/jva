package entity;

import controller.EntityController;
import core.BoundingBox;
import core.Direction;
import core.Motion;
import core.Vector2d;
import display.Camera;
import game.state.State;
import gfx.Animation;
import gfx.Sprite;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class Entity extends  GameObject{
    protected EntityController controller;
    protected Motion motion;
    protected Animation animation;
    protected Direction direction;
    protected SpriteSheet spriteSheet;
    protected int currentAnimation = 0;

    protected Entity(
            EntityController controller,
            SpriteSheet spriteSheet,
            int size,
            Vector2d origin
    )
    {
        super(size, origin);
        animation = new Animation();
        direction = Direction.D;
        this.spriteSheet = spriteSheet;
        this.controller = controller;
        var i = 0;
        setAnimation(i, this.spriteSheet.getSpriteArray(i), 10);
    }

    @Override
    public void update(State state) {
        onBeginUpdate(state);
        motion.update(controller);
        handleMotion(state);
        onAnimate(state);
        animation.update(state);
        handleCollisions(state);
        onUpdate(state);
        origin = motion.applyTo(origin);
    }

    protected void onAnimate(State state) {
        Direction newDirection = Direction.fromMotion(motion);
        int currentAnimation = getAnimationRow(direction);
        if(motion.IsMoving()) {
            direction = newDirection;
            int newRow = getAnimationRow(direction);
            if(this.currentAnimation != newRow || animation.getDelay() == -1) {
                setAnimation(currentAnimation, spriteSheet.getSpriteArray(currentAnimation), 5);
            }
            System.out.println("Moving " + direction);
        }
        else {
            currentAnimation = getAnimationRow(direction);
            setAnimation(currentAnimation, spriteSheet.getSpriteArray(currentAnimation), -1);
            System.out.println("Idle " + direction);
        }


    }

    @Override
    public BoundingBox getBoundingBox() {
        var positionWithMotion = motion.applyTo(origin);
        return new BoundingBox(
                positionWithMotion,
                size.getWidth(),
                size.getHeight()
        );
    }

    @Override
    public EntityController getController(){
        return controller;
    }

    protected void onBeginUpdate(State state) {

    }

    protected void onUpdate(State state) {

    }

    protected void setAnimation(int i, Sprite[] frames, int delay){
        if(i == currentAnimation && delay != -1)
            return;;
        currentAnimation = i;
        animation.setFrames(i, frames);
        animation.setDelay(delay);
        System.out.println("Current animation: " + currentAnimation);
    }

    protected int getAnimationRow(Direction direction) {
        return direction.getAnimationRow();
    }

    @Override
    public void render(State state, Graphics2D g){
        Camera camera = state.getCamera();
        int x = (int)this.getRenderPosition(camera).getX();
        int y = (int)this.getRenderPosition(camera).getY();
        Sprite sprite = animation.getSprite();
        BufferedImage image = sprite.getImage();
        g.drawImage(image, x, y, null);
    }

    public void multiplySpeed(int speedMultiplier) {
        motion.multiplySpeed(speedMultiplier);
    }

    private void handleCollisions(State state) {
        state.getCollidingGameObject(this)
                .forEach(this::handleCollision);
    }

    protected abstract void handleCollision(IGameObject gameObject);


    protected void handleMotion(State state){
        motion.stop();
    }
}
