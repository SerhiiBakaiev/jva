package graphic.core;

import java.awt.*;

public class GraphicContext implements  IGraphicContext
{
    private KeyBoardAction _lastAction = KeyBoardAction.Unknown;
    private final Graphics2D _scene;

    public GraphicContext(
            Graphics2D scene,
            KeyBoardAction lastAction
    )
    {
        _scene = scene;
        _lastAction = lastAction;
    }

    public KeyBoardAction getLastAction() {
        return _lastAction;
    }

    @Override
    public Graphics2D getGraphics() {
        return _scene;
    }
}
