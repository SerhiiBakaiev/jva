package display;

import game.state.State;

import java.awt.*;

public interface IRenderer {
    void render(State state, Graphics graphics);
}
