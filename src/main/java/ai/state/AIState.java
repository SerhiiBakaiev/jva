package ai.state;

import entity.Enemy;
import game.state.State;

public interface AIState {
    void initialize();
    boolean shouldTransition(State state, Enemy enemy);
    void update(State state, Enemy enemy);
    String getNextTransition();
}
