package ai;

import entity.Enemy;
import entity.IGameObject;
import game.state.State;

public interface AICondition {
    boolean isMet(State state, IGameObject enemy);
}
