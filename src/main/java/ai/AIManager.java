package ai;

import ai.state.AIState;
import ai.state.StandState;
import ai.state.WanderState;
import entity.Enemy;
import game.state.State;

public class AIManager {

    private AIState _curentState;

    public AIManager() {
        transitionTo("walking");
    }

    public void update(State state, Enemy enemy) {
        _curentState.update(state, enemy);
        if(_curentState.shouldTransition(state, enemy)){
            transitionTo(_curentState.getNextTransition());
        }
    }

    private void transitionTo(String nextTransition) {
        switch(nextTransition) {
            case "wander":
                _curentState = new WanderState();
                break;
            default:
            case "stand":
                _curentState = new StandState();

        }

        _curentState.initialize();
    }

}
