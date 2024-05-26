package org.example;

import graphic.core.GraphicContext;
import models.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements  Runnable  {
    final  int _baseTileSize = 16;
    final  int _scale = 3;

    final  int _tileSize = _baseTileSize * _scale;
    final  int _maxScreenCol = 16;
    final  int _maxScreenRow = 12;

    final int _width = _tileSize * _maxScreenCol;
    final int _height = _tileSize * _maxScreenRow;
    final int _fps = 120    ;
    final KeyHandler _keyHandler = new KeyHandler();

    private final Player _player = new Player();

    final private Thread _gameThread;

    public  GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(_width, _height));
        this.addKeyListener(_keyHandler);
        this.setFocusable(true);
        _gameThread = new Thread(this);
    }

    public void start()
    {
        _gameThread.start();
    }

    @Override
    public  void run()
    {
        double second =  1_000_000_000.0;
        double drawInterval = second / _fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long curentTime = 0;
        long timer = 0;
        int drawCount = 0;
        long elapsedTime = 0;
        while (!_gameThread.isInterrupted())
        {
            curentTime = System.nanoTime();
            elapsedTime = curentTime - lastTime;
            timer+= elapsedTime;
            delta += elapsedTime / drawInterval;
            lastTime = curentTime;
            if(delta >= 1)
            {
                render();
                delta--;
                drawCount++;
            }
            if(timer >= second)
            {
                System.out.println("FPS: " + _fps);
            }
        }
    }

    private  void render()
    {
        Graphics2D graphics2D = (Graphics2D)this.getGraphics();;
        GraphicContext gc = new GraphicContext(graphics2D, _keyHandler.get_keyBoardAction());
        _player.update(gc);
        repaint();
    }
    private void update()
    {

    }

    public  void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        GraphicContext gc = new GraphicContext(graphics2D, _keyHandler.get_keyBoardAction());
        _player.draw(gc);
        graphics2D.dispose();
    }
}
