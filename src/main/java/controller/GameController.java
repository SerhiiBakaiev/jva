package controller;

import game.Game;
import game.Input;
import game.Settings;

import java.awt.event.KeyEvent;

public class GameController  {
    private Input input;

    public GameController(Input input) {
        this.input = input;
    }

    public void update(Game state){
        Settings settings = Settings.getInstance();
        if(input.isPressed(KeyEvent.VK_F1)){
            settings.toogleDebugMode();
        }

        if(input.isPressed(KeyEvent.VK_F2)){
            settings.increaseGameSpeed();
        }
        if(input.isPressed(KeyEvent.VK_F3)){
            settings.decreaseGameSpeed();
        }
    }
}
