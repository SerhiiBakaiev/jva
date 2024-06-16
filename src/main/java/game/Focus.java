package game;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Focus implements FocusListener {

    private final JFrame _frame;
    private final Input input;
    public Focus(JFrame frame, Input input)
    {
        this.input = input;
        _frame = frame;
    }
    @Override
    public void focusGained(FocusEvent e) {
        input.clear();
        _frame.requestFocus();
    }

    @Override
    public void focusLost(FocusEvent e) {
        input.clear();
    }
}
