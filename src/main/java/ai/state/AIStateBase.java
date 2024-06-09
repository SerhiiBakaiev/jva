package ai.state;

import ai.AITransition;
import entity.Enemy;
import game.state.State;

public abstract class AIStateBase implements AIState{
    private AITransition _transition;
    public AIStateBase() {

    }

    @Override
    public void initialize(){
      _transition = getTransition();
    }

    @Override
    public boolean shouldTransition(State state, Enemy enemy) {
        return _transition.shouldTransition(state, enemy);
    }

    @Override
    public String getNextTransition() {
        return _transition.getNextState();
    }

    protected abstract AITransition getTransition();
}
