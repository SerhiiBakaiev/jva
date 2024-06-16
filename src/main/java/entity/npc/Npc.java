package entity.npc;

import ai.AIManager;
import controller.EntityController;
import core.Motion;
import entity.MovingEntity;
import game.state.State;
import gfx.TileLibrary;

public abstract class Npc extends MovingEntity {
    protected AIManager aiManager;
    protected Npc(EntityController controller) {
        super(controller, null, 64);
        aiManager = new AIManager();
        motion = new Motion(1);
    }

    @Override
    public void update(State state) {
        super.update(state);
        aiManager.update(state, this);
    }


}
