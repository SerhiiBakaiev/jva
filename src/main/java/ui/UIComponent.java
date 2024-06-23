package ui;

import core.Size;
import core.Vector2d;
import game.state.State;

import java.awt.*;

public abstract class UIComponent {

    protected Vector2d _position;
    protected Size _size;
    protected Spacing _margin;
    protected Spacing _padding;


    public UIComponent(){
        _position = Vector2d.Zero;
        _size = new Size(1,1);
        _margin = new Spacing(0);
        _padding = new Spacing(0);

    }

    public abstract Image getSprite();
    public abstract void update(State state);
    public Vector2d getPosition() {
        return _position;
    }

    public void setPosition(Vector2d position) {
        _position = position;
    }

    public Size getSize() {
        return _size;
    }

    public void setSize(Size _size) {
        this._size = _size;
    }

    public Spacing getMargin() {
        return _margin;
    }

    public void setMargin(Spacing _margin) {
        this._margin = _margin;
    }

    public Spacing getPadding() {
        return _padding;
    }

    public void setPadding(Spacing _padding) {
        this._padding = _padding;
    }

}
