package ai.state;

import ai.AITransition;
import entity.IGameObject;
import game.state.State;

public abstract class AIStateBase implements AIState{
    private AITransition transition;
    public AIStateBase() {

    }

    @Override
    public void initialize(){
      transition = getTransition();
    }

    @Override
    public boolean shouldTransition(State state, IGameObject enemy) {
        return transition.shouldTransition(state, enemy);
    }

    @Override
    public String getNextTransition() {
        return transition.getNextState();
    }

    protected abstract AITransition getTransition();
}
