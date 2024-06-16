package entity;

import ai.AIManager;
import controller.EntityController;
import core.Direction;
import core.Motion;
import game.state.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends MovingEntity{

    private final AIManager aiManager;

    public Enemy(EntityController controller) {
        super(controller, null, 64);
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
