package entity.effect;

import entity.MovingEntity;
import game.GameLoop;
import game.state.State;

public class SpeedBoostEffect extends Effect {

    private int _speedMultiplier;
    public SpeedBoostEffect( int speedMultiplier) {
        super(GameLoop.UPDATES_PER_SECONDS * 5);
        _speedMultiplier = speedMultiplier;
    }

    @Override
    public void update(State state, MovingEntity movingEntity){
          super.update(state, movingEntity);
          movingEntity.multiplySpeed(_speedMultiplier);
    }


}
