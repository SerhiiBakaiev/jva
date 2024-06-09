package entity.action;

import entity.MovingEntity;
import game.state.State;

public class Attack extends  Action{
    @Override
    public void update(State state, MovingEntity entity) {

    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String getAnimationName() {
        return "";
    }
}
