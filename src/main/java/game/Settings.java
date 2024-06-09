package game;

public class Settings {

    private static Settings _instance = null;
    private boolean _isDebugMode = false;
    private double _gameSpeedMultiplier = 1;

    public boolean isDebugMode() {
        return _isDebugMode;
    }

    public void toogleDebugMode() {
        _isDebugMode = !_isDebugMode;
    }

    public void setDebugMode(boolean debugMode) {
        _isDebugMode = debugMode;
    }

    public void increaseGameSpeed(){
        _gameSpeedMultiplier += 0.25;
    }

    public void decreaseGameSpeed(){
        _gameSpeedMultiplier -= 0.25;
    }

    public double getGameSpeedMultiplier(){
        return _gameSpeedMultiplier;
    }

    public static Settings getInstance() {
        if (_instance == null)
            _instance = new Settings();
        return _instance;
    }
}
