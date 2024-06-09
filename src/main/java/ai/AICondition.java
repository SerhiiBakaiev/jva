package ai;

import entity.Enemy;
import game.state.State;

public interface AICondition {
    boolean isMet(State state, Enemy enemy);
}
