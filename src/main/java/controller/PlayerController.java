package controller;

import game.Input;

import java.awt.event.KeyEvent;

public class PlayerController implements EntityController {

    private Input _input;

    public PlayerController(Input input) {
        _input = input;
    }

    @Override
    public boolean isRequestingUp() {
        return _input.isCurentlyPressed(KeyEvent.VK_UP);
    }

    @Override
    public boolean isRequestingDown() {
        return _input.isCurentlyPressed(KeyEvent.VK_DOWN);
    }

    @Override
    public boolean isRequestingRight() {
        return _input.isCurentlyPressed(KeyEvent.VK_RIGHT);
    }

    @Override
    public boolean isRequestingLeft() {
        return _input.isCurentlyPressed(KeyEvent.VK_LEFT);
    }

    @Override
    public boolean isRequestingSpace() {
        return _input.isCurentlyPressed(KeyEvent.VK_SPACE);
    }

    @Override
    public boolean isAttacking() {
        return _input.isPressed(KeyEvent.VK_E);
    }
}
