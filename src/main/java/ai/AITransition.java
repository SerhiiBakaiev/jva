package ai;

import entity.Enemy;
import entity.IGameObject;
import game.state.State;

public class AITransition {
    private final String _nextState;
    private final AICondition _condition;

    public AITransition(String nextState, AICondition condition) {
        _nextState = nextState;
        _condition = condition;
    }

    public boolean shouldTransition(State state, IGameObject enemy) {
        return _condition.isMet(state,enemy);
    }

    public String getNextState() {
        return _nextState;
    }
}
