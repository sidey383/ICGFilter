package ru.nsu.group21208.panel.toolBar;
import ru.nsu.group21208.panel.PanelInteractionStorage;

import javax.swing.*;
import java.util.Collection;

public class ToolBar extends JToolBar {

    public ToolBar(PanelInteractionStorage panelInteractionStorage) {
        ShowModeToggleActor showModeToggleActor = new ShowModeToggleActor(panelInteractionStorage.getShowModeInteractions());
        OriginalToggleActor originalToggleActor = new OriginalToggleActor(panelInteractionStorage.getOriginalToggleInteraction());
        InterpolationModeToggleActor interpolationModeToggleActor = new InterpolationModeToggleActor(panelInteractionStorage.getInterpolationModInteractions());

        addButtons(showModeToggleActor.getButtons());
        addButtons(originalToggleActor.getButtons());
        addButtons(interpolationModeToggleActor.getButtons());


    }

    private <T> void addButtons(Collection<ToolButton<T>> buttons) {
        for (ToolButton<T> button : buttons) {
            add(button);
        }

        add(new Separator());
    }
}
