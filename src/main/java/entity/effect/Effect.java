package entity.effect;

import entity.Entity;
import game.state.State;

public abstract class Effect {
    private int _lifeSpanInUpdates;

    protected Effect(int lifeSpanInUpdates) {
        _lifeSpanInUpdates = lifeSpanInUpdates;
    }

    public  void update(State state, Entity entity) {
        _lifeSpanInUpdates--;
    }

    public boolean isActive(){
        return _lifeSpanInUpdates <= 0;
    }

}
