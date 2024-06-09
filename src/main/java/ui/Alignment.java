package ui;

public class Alignment {
    public enum Position{
        START, CENTER, END
    }

    private final Position _horizontal;
    private final Position _vertical;


    public Alignment(Position vertical, Position horizontal) {
        _vertical = vertical;
        _horizontal = horizontal;
    }

    public Position getHorizontal() {
        return _horizontal;
    }

    public Position getVertical() {
        return _vertical;
    }
}
