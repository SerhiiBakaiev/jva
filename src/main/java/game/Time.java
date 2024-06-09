package game;

public class Time {
    private int _updateSinceStart = 0;

    public Time(){
        _updateSinceStart = 0;
    }

    public int getUpdatesFromSecond(int seconds){
        return seconds * GameLoop.UPDATES_PER_SECONDS;
    }

    public void update(){
        _updateSinceStart++;
    }

    public String getFormattedTime(){
        StringBuilder sb = new StringBuilder();
        int minutes = _updateSinceStart / GameLoop.UPDATES_PER_SECONDS / 60;
        int seconds = _updateSinceStart / GameLoop.UPDATES_PER_SECONDS % 60;
        if(minutes < 10){
            sb.append(0);
        }
        sb.append(minutes);
        sb.append(":");
        if(seconds < 10){
            sb.append(0);
        }
        sb.append(seconds);
        return sb.toString();
    }

}
