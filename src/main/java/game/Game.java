package game;

import controller.GameController;
import core.Size;
import display.Display;
import game.state.GameState;
import game.state.State;

import java.io.IOException;

public class Game {

    private final Display display;
    private final State state;
    private final GameController gameController;

    public static int SPRITE_SIZE = 48;
    public static final int TILE_SIZE = 48;
    public static int DEFAULT_ZOOM = 4;
    public static int UNITS_SIZE = 16;


    public  Game(int width, int height) throws IOException {
        Size size = new Size(width, height);
        Input input = new Input();
        display = new Display(size, input);
        state = new GameState(size, input);
        gameController = new GameController(input);
    }

    public  void  update(){
        state.update();
        gameController.update(this);
    }

    public  void render(){
        display.render(state);
    }
}
