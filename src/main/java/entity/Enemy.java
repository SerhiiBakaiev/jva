package entity;

import ai.AIManager;
import controller.EntityController;
import core.Direction;
import core.Motion;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public class Enemy extends MovingEntity{

    private final AIManager _aiManager;
    public Enemy(EntityController controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
        _direction = Direction.R;
        _motion = new Motion(2);
        _aiManager = new AIManager();
        _animationManager = new AnimationManager(spriteLibrary.getSpriteSet("sprites/unit/character/main"));
        _animationManager.playAnimation("walking");
    }

    @Override
    public void update(State state) {
        super.update(state);
        _aiManager.update(state, this);
    }

    @Override
    protected void handleCollision(IGameObject other) {
        if(other instanceof  Player2){
            _motion.stop();
        }
    }
}
