package entity;

import controller.NPCController;
import core.Motion;
import core.Vector2d;
import game.state.State;
import gfx.SpriteSheet;

import java.awt.*;

public abstract class Enemy extends AttackingEntity {

    private final NPCController npcController;

    public Enemy(NPCController controller, SpriteSheet spriteSheet, Vector2d origin) {
        super(controller, spriteSheet, 64, origin);
        this.npcController = controller;
        motion = new Motion(2);
    }

    @Override
    public void update(State state) {
        super.update(state);
        Player2 player = state.getPlayer();
        npcController.moveToTarget(
            player.getOrigin(),
            origin
        );
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
