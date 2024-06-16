package entity.action;

import entity.MovingEntity;
import game.GameLoop;
import game.state.State;

public class Attack extends  Action{

    private int duration;

    public  Attack(){
        duration = GameLoop.UPDATES_PER_SECONDS;
    }
    @Override
    public void update(State state, MovingEntity entity) {
        duration--;
    }

    @Override
    public boolean isDone() {
        return duration <= 0;
    }

    @Override
    public String getAnimationName() {
        return "attacking";
    }
}
