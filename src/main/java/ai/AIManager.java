package ai;

import ai.state.AIState;
import ai.state.StandState;
import ai.state.MoveToPlayerState;
import ai.state.WanderState;
import entity.IGameObject;
import game.state.State;

public class AIManager {

    private AIState currentState;

    public AIManager() {
        transitionTo("wander");
    }

    public void update(State state, IGameObject enemy) {
        currentState.update(state, enemy);
        if(currentState.shouldTransition(state, enemy)){
            transitionTo(currentState.getNextTransition());
        }
    }

    private void transitionTo(String nextTransition) {
        switch(nextTransition) {
            case "wander":
                currentState = new WanderState();
                break;
            case "moveToPlayer":
                currentState = new MoveToPlayerState();
                break;
            default:
            case "stand":
                currentState = new StandState();

        }

        currentState.initialize();
    }

}
