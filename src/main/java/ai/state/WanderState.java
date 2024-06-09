package ai.state;

import ai.AITransition;
import controller.EnemyController;
import core.Position;
import entity.Enemy;
import entity.Player2;
import game.state.State;

import java.util.ArrayList;
import java.util.List;

public class WanderState extends  AIStateBase{

    public WanderState() {
        super();
    }

    @Override
    protected AITransition getTransition() {
        return new AITransition("stand", ((state, character) -> isArrived(state.getPlayer(), character)));
    }

    @Override
    public void update(State state, Enemy enemy) {
        Player2 player = state.getPlayer();
        EnemyController controller = (EnemyController)enemy.getController();
        controller.moveToTarget(player.getPosition(), enemy.getPosition());
        if(isArrived(state.getPlayer(), enemy)){
            //controller.stop();
        }
    }

    private  boolean isArrived(Player2 player, Enemy enemy){
        return false;
        //return enemy.getPosition().isRangeOf(player.getPosition());
    }

}
