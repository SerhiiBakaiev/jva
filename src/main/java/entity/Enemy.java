package entity;

import ai.AIManager;
import controller.EntityController;
import core.Motion;
import core.Vector2d;
import game.state.State;

import java.awt.*;

public class Enemy extends Entity {

    private final AIManager aiManager;

    public Enemy(EntityController controller, Vector2d origin) {
        super(controller, null, 64, origin);
        motion = new Motion(2);
        aiManager = new AIManager();
    }

    @Override
    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }



    @Override
    public void render(State state, Graphics2D g) {

    }

    @Override
    protected void handleCollision(IGameObject other) {
        if(other instanceof  Player2){
            motion.stop();
        }
    }
}
