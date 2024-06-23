package entity;

import controller.EntityController;
import core.BoundingBox;
import core.Direction;
import core.Motion;
import core.Vector2d;
import display.Camera;
import entity.action.Action;
import entity.effect.Effect;
import game.state.State;
import gfx.Animation;
import gfx.Sprite;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Entity extends  GameObject{
    protected EntityController controller;
    protected Motion motion;
    private  final List<Effect> _effects;
    private Optional<Action> action;
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
        _effects = new ArrayList<>();
        action = Optional.empty();
        animation = new Animation();
        direction = Direction.D;
        this.spriteSheet = spriteSheet;
        this.controller = controller;
        var i = Direction.D.getAnimationRow();
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
        applyEffects(state);
        origin = motion.applyTo(origin);
        cleanup();
    }

    protected void onAnimate(State state) {
        Direction newDirection = Direction.fromMotion(motion);
        int currentAnimation = direction.getAnimationRow();
        if(motion.IsMoving()) {
            direction = newDirection;
            int newRow = direction.getAnimationRow();
            if(this.currentAnimation != newRow || animation.getDelay() == -1) {
                setAnimation(currentAnimation, spriteSheet.getSpriteArray(currentAnimation), 5);
            }
            System.out.println("Moving " + direction);
        }
        else {
            currentAnimation = direction.getAnimationRow();
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
        currentAnimation = i;
        animation.setFrames(i, frames);
        animation.setDelay(delay);
    }

    public void addEffect(Effect effect) {
        _effects.add(effect);
    }

    public void performAction(Action state) {
        action = Optional.of(state);
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

    private void handleAction(State state){
        if(action.isEmpty())
            return;

        action.get().update(state, this);
    }



    protected void handleMotion(State state){
        if(action.isEmpty()) {

        } else{
            motion.stop();
        }
    }

    private  void applyEffects(State state){
        _effects.forEach(effect -> effect.update(state, this));
    }

    private  void cleanup(){

        List.copyOf(_effects).stream()
                .filter(Effect::isActive)
                .forEach(_effects::remove);

        if(action.isPresent() && action.get().isDone())
            action = Optional.empty();
    }
}
