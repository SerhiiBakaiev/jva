package game;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Focus implements FocusListener {

    private final JFrame _frame;

    public Focus(JFrame frame)
    {
        _frame = frame;
    }
    @Override
    public void focusGained(FocusEvent e) {
        _frame.requestFocus();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
