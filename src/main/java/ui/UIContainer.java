package ui;

import core.Position;
import core.Size;
import game.state.State;
import gfx.ImageUtils;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class UIContainer extends UIComponent {

    protected Color _backgroundColor;

    protected Alignment _alignment;
    protected Size _windowSize;

    protected List<UIComponent> _children;

    public UIContainer(Size windowSize) {
        super();
        this._windowSize = windowSize;
        _alignment = new Alignment(Alignment.Position.START, Alignment.Position.START);
        _backgroundColor = new Color(0, 0, 0, 0);
        _margin = new Spacing(5);
        _padding = new Spacing(5);
        _children = new ArrayList<>();
        calculateSize();
        calculatePosition();
    }

    protected abstract Size calculateContentSize();
    protected abstract void calculateContentPosition();

    private void calculateSize() {
        Size calculatedContentSize = calculateContentSize();
        _size = new Size(
                _padding.getHorizontal() + calculatedContentSize.getWidth(),
                _padding.getVertical() + calculatedContentSize.getHeight());
    }

    private void calculatePosition() {
        int x = _padding.getLeft();
        if(_alignment.getHorizontal().equals(Alignment.Position.CENTER)) {
            x = _windowSize.getWidth() / 2 - _size.getWidth() / 2;
        }
        if(_alignment.getHorizontal().equals(Alignment.Position.END)) {
            x = _windowSize.getWidth() - _size.getWidth() - _margin.getRight();
        }

        int y = _padding.getTop();
        if(_alignment.getVertical().equals(Alignment.Position.CENTER)) {
            y = _windowSize.getHeight() / 2 - _size.getHeight() / 2;
        }
        if(_alignment.getVertical().equals(Alignment.Position.END)) {
            y = _windowSize.getHeight() - _size.getHeight() - _margin.getBottom();
        }

        this._position = new Position(x, y);
        calculateContentPosition();
    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(_size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = image.createGraphics();

        graphics.setColor(_backgroundColor);
        graphics.fillRect(0, 0, _size.getWidth(), _size.getHeight());

        for(UIComponent uiComponent : _children) {
            graphics.drawImage(
                    uiComponent.getSprite(),
                    uiComponent.getPosition().getIntX(),
                    uiComponent.getPosition().getIntY(),
                    null
            );
        }

        graphics.dispose();
        return image;
    }

    @Override
    public void update(State state) {
        _children.forEach(component -> component.update(state));
        calculateSize();
        calculatePosition();
    }

    public void addUIComponent(UIComponent uiComponent) {
        _children.add(uiComponent);
    }

    public void setBackgroundColor(Color color) {
        _backgroundColor = color;
    }

    public void setAlignment(Alignment _alignment) {
        this._alignment = _alignment;
    }
}
