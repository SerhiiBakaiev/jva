package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Input implements KeyListener {

    private final boolean[] _pressed = new boolean[256];
    private final boolean[] _curentlyPressed = new boolean[256];

    public Input()
    {

    }

    public  void clear(){
        Arrays.fill(_pressed, false);
        Arrays.fill(_curentlyPressed, false);
    }
    public boolean isPressed(int keyCode){
        if(!_pressed[keyCode] && _curentlyPressed[keyCode]) {
            _pressed[keyCode] = true;
            return true;
        }

        return false;
    }

    public boolean isCurentlyPressed(int keyCode){
        return _curentlyPressed[keyCode];
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        _curentlyPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        _curentlyPressed[e.getKeyCode()] = false;
        _pressed[e.getKeyCode()] = false;
    }
}
