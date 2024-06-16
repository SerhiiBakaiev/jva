package display;

import core.Size;
import entity.IGameObject;
import game.Focus;
import game.Game;
import game.Input;
import game.Settings;
import game.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class Display  extends JFrame {
    private final Canvas _canvas;
    private final  Renderer _renderer;
    private final  DebugRenderer _debugRenderer;
    private final Focus _focus;

    public Display(Size windowSize, Input input) {
        _debugRenderer = new DebugRenderer();
        _renderer = new Renderer(this);
        _focus = new Focus(this, input);
        setTitle("My game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        _canvas = new Canvas();
        _canvas.setPreferredSize(new Dimension(windowSize.getWidth(), windowSize.getHeight()));
        add(_canvas);
        pack();

        _canvas.createBufferStrategy(2);
        addKeyListener(input);
        addFocusListener(_focus);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);

    }

    public  void render(State state) {
        BufferStrategy bufferStrategy = _canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, getWidth(), getHeight());
        _renderer.render(state, graphics);
        if(Settings.getInstance().isDebugMode())
            _debugRenderer.render(state, graphics);
        graphics.dispose();
        bufferStrategy.show();
    }

    public  int getHeight() {
        return _canvas.getHeight();
    }

    public  int getWidth() {
        return _canvas.getWidth();
    }
}
