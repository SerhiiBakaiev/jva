package game.ui;

import core.Size;
import entity.Player2;
import game.state.State;
import ui.*;

public class UIHealthStatistics extends VerticalContainer {

    private UIText _healthText;

    public UIHealthStatistics(Size windowSize) {
        super(windowSize);
        _healthText = new UIText("100");
        UIContainer healthContainer = new VerticalContainer(windowSize);
        healthContainer.setPadding(new Spacing(0));
        healthContainer.addUIComponent(new UIText("Health: "));
        healthContainer.addUIComponent(_healthText);

        addUIComponent(healthContainer);
    }

    @Override
    public void update(State state) {
        super.update(state);
        Player2 player = state.getPlayer();
        _healthText.setText(String.valueOf(player.getHealthPoint()));

    }
}
