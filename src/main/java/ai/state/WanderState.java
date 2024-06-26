package ai.state;

import ai.AITransition;
import controller.NPCController;
import core.Vector2d;
import entity.IGameObject;
import entity.Player2;
import game.state.State;

public class WanderState extends AIStateBase {

    private Vector2d target;
    private boolean isArrived;

    public WanderState() {
    }

    @Override
    protected AITransition getTransition() {
        return new AITransition("stand", ((state, character) -> isArrived));
    }

    @Override
    public void update(State state, IGameObject gameObject) {
        if (!(gameObject.getController() instanceof NPCController npcController))
            return;

        if (target == null) {
            target = state.getRandomPosition();
        }

        if (npcController.moveToTarget(target, gameObject.getOrigin())) {
            target = state.getRandomPosition();
        }
        Player2 player2 = state.getPlayer();
        if (isArrivedTo(gameObject, player2)) {
            isArrived = true;
            npcController.stop();
        }
    }

    private boolean isArrivedTo(IGameObject first, IGameObject second) {
        return first.collidesWith(second);
        //return first.getPosition().isRangeOf(second.getPosition(), 10);
    }
}
