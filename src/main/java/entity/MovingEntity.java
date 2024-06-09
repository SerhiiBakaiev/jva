package entity;

import controller.EntityController;
import core.Direction;
import core.Motion;
import core.Position;
import entity.action.Action;
import entity.effect.Effect;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MovingEntity extends  GameObject{
    protected EntityController _controller;
    protected Motion _motion;
    protected AnimationManager _animationManager;
    protected Direction _direction;
    private  final List<Effect> _effects;
    private Optional<Action> _action;

    protected MovingEntity(EntityController controller, SpriteLibrary spriteLibrary) {
        _effects = new ArrayList<>();
        _action = Optional.empty();
        _controller = controller;
        collisionBoxOffset = new Position(
            collisionBoxSize.getWidth() / 2,
            collisionBoxSize.getHeight() / 2
        );
    }

    @Override
    public void update(State state) {

        handleAction(state);
        handleMotion(state);
        _animationManager.update(_direction);
        applyEffects(state);

        handleCollisions(state);
        manageDirection();
        decideAnimation();

        _position.apply(_motion);
        cleanup();
    }

    @Override
    public Image getSprite(){
        return _animationManager.getSprite();
    }

    public EntityController getController(){
        return _controller;
    }

    public void addEffect(Effect effect) {
        _effects.add(effect);
    }

    public void performAction(Action state) {
        _action = Optional.of(state);
    }

    public void multiplySpeed(int speedMultiplier) {
        _motion.multiplySpeed(speedMultiplier);
    }

    private void handleCollisions(State state) {
        state.getCollidingGameObject(this)
                .forEach(this::handleCollision);
    }

    protected abstract void handleCollision(IGameObject gameObject);
    private void handleAction(State state){
        if(_action.isEmpty())
            return;

        _action.get().update(state, this);
    }

    private void manageDirection(){
        if(_motion.IsMoving()) {
            _direction = Direction.fromMotion(_motion);
        }
    }

    private void decideAnimation() {
        if(_action.isPresent()){
            _animationManager.playAnimation(_action.get().getAnimationName());
        } else{
          _animationManager.playAnimation("walking");
        }
    }
    private void handleMotion(State state){
        if(_action.isEmpty()) {
            _motion.update(_controller);
        } else{
            _motion.stop();
        }
    }

    private  void applyEffects(State state){
        _effects.forEach(effect -> effect.update(state, this));
    }

    private  void cleanup(){

        List.copyOf(_effects).stream()
                .filter(Effect::isActive)
                .forEach(_effects::remove);

        if(_action.isPresent() && _action.get().isDone())
            _action = Optional.empty();
    }
}
