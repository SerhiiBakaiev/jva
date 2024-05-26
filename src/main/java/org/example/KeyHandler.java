package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    private KeyBoardAction _keyBoardAction = KeyBoardAction.Unknown;

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        _keyBoardAction = switch (code)
        {
            case KeyEvent.VK_UP -> KeyBoardAction.MoveUp;
            case KeyEvent.VK_DOWN -> KeyBoardAction.MoveDown;
            case KeyEvent.VK_LEFT -> KeyBoardAction.MoveLeft;
            case KeyEvent.VK_RIGHT -> KeyBoardAction.MoveRight;
            default -> KeyBoardAction.Unknown;
        };
    }

    @Override
    public void keyReleased(KeyEvent e) {
        _keyBoardAction = KeyBoardAction.Unknown;
    }

    public KeyBoardAction get_keyBoardAction()
    {
        return _keyBoardAction;
    }

}
