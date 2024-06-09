package game.ui;

import core.Size;
import game.state.State;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.UIText;

public class UIGameTime extends HorizontalContainer {
    private UIText _uiText;

    public UIGameTime(Size windowSize) {
        super(windowSize);
        _uiText = new UIText("00:00");
        setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.START));
        addUIComponent(_uiText);
    }

    @Override
    public void update(State state) {
        super.update(state);
        _uiText.setText(state.getTime().getFormattedTime());
    }

}
