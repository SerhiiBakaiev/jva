package ai.state;

import ai.AITransition;
import entity.Enemy;
import game.state.State;

public class StandState extends AIStateBase {
    private int _updates = 0;

    @Override
    protected AITransition getTransition() {
        return new AITransition("wander",((state, curentCharacter) ->
        {
            return _updates >= state.getTime().getUpdatesFromSecond(1);
        }));
    }

    @Override
    public void update(State state, Enemy enemy) {
        _updates++;
    }
}
