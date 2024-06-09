package entity.effect;

import entity.MovingEntity;
import game.state.State;

public abstract class Effect {
    private int _lifeSpanInUpdates;

    protected Effect(int lifeSpanInUpdates) {
        _lifeSpanInUpdates = lifeSpanInUpdates;
    }

    public  void update(State state, MovingEntity movingEntity) {
        _lifeSpanInUpdates--;
    }

    public boolean isActive(){
        return _lifeSpanInUpdates <= 0;
    }

}
