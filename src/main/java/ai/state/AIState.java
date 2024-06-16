package ai.state;

import entity.Enemy;
import entity.IGameObject;
import game.state.State;

public interface AIState {
    void initialize();
    boolean shouldTransition(State state, IGameObject enemy);
    void update(State state, IGameObject enemy);
    String getNextTransition();
}
