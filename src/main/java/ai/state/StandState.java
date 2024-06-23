package ai.state;

import ai.AITransition;
import controller.NPCController;
import entity.IGameObject;
import entity.Player2;
import game.state.State;

public class StandState extends AIStateBase {

    private boolean isLeaved;

    public StandState() {

    }

    @Override
    protected AITransition getTransition() {
        return new AITransition("wander",((state, character) -> isLeaved));
    }

    @Override
    public void update(State state, IGameObject gameObject) {
        if(!(gameObject.getController() instanceof NPCController npcController))
            return;

        Player2 player2 = state.getPlayer();
        if(isLeavedFrom(player2, gameObject)){
            isLeaved = true;
        }
    }

    private boolean isLeavedFrom(IGameObject first, IGameObject second) {
        return !first.collidesWith(second);
        //return !first.getPosition().isRangeOf(second.getPosition(), 10);
    }
}
