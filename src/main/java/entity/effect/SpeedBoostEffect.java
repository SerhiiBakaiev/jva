package entity.effect;

import entity.Entity;
import game.GameLoop;
import game.state.State;

public class SpeedBoostEffect extends Effect {

    private int _speedMultiplier;
    public SpeedBoostEffect( int speedMultiplier) {
        super(GameLoop.UPDATES_PER_SECONDS * 5);
        _speedMultiplier = speedMultiplier;
    }

    @Override
    public void update(State state, Entity entity){
          super.update(state, entity);
          entity.multiplySpeed(_speedMultiplier);
    }


}
