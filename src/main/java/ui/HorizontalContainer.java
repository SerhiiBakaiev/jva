package ui;

import core.Size;
import core.Vector2d;

public class HorizontalContainer extends  UIContainer{

    public HorizontalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildWidth = 0;
        int tallestChildHeight = 0;

        for(UIComponent uiComponent : _children) {
            combinedChildWidth += uiComponent.getSize().getWidth() + uiComponent.getMargin().getHorizontal();

            if(uiComponent.getSize().getHeight() > tallestChildHeight) {
                tallestChildHeight = uiComponent.getSize().getHeight();
            }
        }

        return new Size(combinedChildWidth, tallestChildHeight);
    }

    @Override
    protected void calculateContentPosition() {
        int currentX = _padding.getLeft();

        for(UIComponent uiComponent : _children) {
            currentX += uiComponent.getMargin().getLeft();
            uiComponent.setPosition(Vector2d.of(currentX, _padding.getTop()));
            currentX += uiComponent.getSize().getWidth();
            currentX += uiComponent.getMargin().getRight();
        }
    }
}
