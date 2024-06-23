package entity.npc;

import ai.AIManager;
import controller.EntityController;
import core.Motion;
import core.Vector2d;
import entity.Entity;
import game.state.State;

public abstract class Npc extends Entity {
    protected AIManager aiManager;
    protected Npc(EntityController controller, Vector2d origin) {
        super(controller, null, 64, origin);
        aiManager = new AIManager();
        motion = new Motion(1);
    }

    @Override
    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }


}
