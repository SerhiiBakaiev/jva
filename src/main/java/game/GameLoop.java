package game;

import java.io.IOException;

public class GameLoop implements  Runnable{

    public  static  final int UPDATES_PER_SECONDS = 60;
    private boolean _isRunning;
    private final  double _updateRate = 1.0d/UPDATES_PER_SECONDS;
    private  long _nextStatTime;
    private  int _fps,_ups;
    private  Game _game;
    public  GameLoop(Game game) {
        _game = game;
        _game.update();
    }

    @Override
    public void run() {
        _isRunning = true;
        long currentTime, lastUpdate = System.currentTimeMillis();
        double accumulator = 0.0d;

        _nextStatTime = System.currentTimeMillis() + 1000;
        while(_isRunning)
        {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds * Settings.getInstance().getGameSpeedMultiplier();
            lastUpdate = currentTime;
            if(accumulator >= _updateRate)
            {
                while (accumulator >= _updateRate)
                {
                    update();
                    accumulator -= _updateRate;
                }
            }


            render();
            printStats();
        }
    }

    private  void update() {
        _game.update();
        _ups++;
    }

    private  void render() {
        _game.render();
        _fps++;
    }

    private  void printStats(){
        if(System.currentTimeMillis() > _nextStatTime)
        {
            System.out.printf("FPS : %d, UPS: %d%n", _fps, _ups);
            _ups = _fps = 0;
            _nextStatTime = System.currentTimeMillis() + 1000;
        }

    }
}
