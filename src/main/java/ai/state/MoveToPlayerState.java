package ai.state;

import ai.AITransition;
import controller.NPCController;
import entity.IGameObject;
import entity.Player2;
import game.state.State;

public class MoveToPlayerState extends  AIStateBase{

    public MoveToPlayerState() {
        super();
    }

    @Override
    protected AITransition getTransition() {
        return new AITransition("stand", ((state, character) -> isArrived(state.getPlayer(), character)));
    }

    @Override
    public void update(State state, IGameObject enemy) {
        Player2 player = state.getPlayer();
        NPCController controller = (NPCController)enemy.getController();
        controller.moveToTarget(player.getOrigin(), enemy.getOrigin());
        if(isArrived(state.getPlayer(), enemy)){
            //controller.stop();
        }
    }

    private  boolean isArrived(Player2 player, IGameObject enemy){
        return false;
        //return enemy.getPosition().isRangeOf(player.getPosition());
    }

}
