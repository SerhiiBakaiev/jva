package ui;

public class Spacing {
    private int _top;
    private int _right;
    private int _bottom;
    private int _left;

    public Spacing(int spacing) {
        this(spacing, spacing);
    }

    public Spacing(int horizontal, int vertical) {
        this(vertical, horizontal, vertical, horizontal);
    }

    public Spacing(int top, int right, int bottom, int left) {
        _top = top;
        _right = right;
        _bottom = bottom;
        _left = left;
    }

    public int getTop() {
        return _top;
    }

    public int getRight() {
        return _right;
    }

    public int getBottom() {
        return _bottom;
    }

    public int getLeft() {
        return _left;
    }

    public int getVertical(){
        return _top + _bottom;
    }

    public int getHorizontal(){
        return _left + _right;
    }
}
