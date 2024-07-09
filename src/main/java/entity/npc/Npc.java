package entity.npc;

import controller.EntityController;
import core.Motion;
import core.Vector2d;
import entity.Entity;
import game.state.State;

public abstract class Npc extends Entity {
    protected Npc(EntityController controller, Vector2d origin) {
        super(controller, null, 64, origin);
        motion = new Motion(1);
    }

    @Override
    public void update(State state) {
        super.update(state);
    }


}
