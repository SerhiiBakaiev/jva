package entity.action;

import entity.Entity;
import game.state.State;

public abstract class Action {
    public abstract void update(State state, Entity entity);
    public abstract boolean isDone();
    public abstract  String getAnimationName();
}
