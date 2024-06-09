package game;

import controller.GameController;
import core.Size;
import display.Display;
import game.state.GameState;
import game.state.State;

import java.io.IOException;

public class Game {

    private final Display _display;
    private final Input _input;
    private final State _state;
    private GameController _gameController;

    public static int SPRITE_SIZE = 48;
    public static int DEFAULT_ZOOM = 4;
    public static int UNITS_SIZE = 16;


    public  Game(int width, int height) throws IOException {
        Size size = new Size(width, height);
        _input = new Input();
        _display = new Display(size, _input);
        _state = new GameState(size, _input);
        _gameController = new GameController(_input);
    }

    public  void  update(){
        _state.update();
        _gameController.update(this);
    }

    public  void render(){
        _display.render(_state);
    }
}
