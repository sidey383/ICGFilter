package ru.nsu.group21208.panel.toolbar;
import ru.nsu.group21208.panel.PanelInteractionStorage;

import javax.swing.*;
import java.util.Collection;

public class ToolBar extends JToolBar {

    public ToolBar(PanelInteractionStorage panelInteractionStorage) {
        ShowModeToggleActor showModeToggleActor = new ShowModeToggleActor(panelInteractionStorage.getShowModeInteractions());
        OriginalToggleActor originalToggleActor = new OriginalToggleActor(panelInteractionStorage.getOriginalToggleInteraction());
        InterpolationModeToggleActor interpolationModeToggleActor = new InterpolationModeToggleActor(panelInteractionStorage.getInterpolationModInteractions());
        new FilterToggleActor<>(panelInteractionStorage.getFilterInteractions(), this);
        FileInteraction fileInteraction = new FileInteraction(panelInteractionStorage.getFileInteractions());

        addButtons(showModeToggleActor.getButtons());
        addButtons(originalToggleActor.getButtons());
        addButtons(interpolationModeToggleActor.getButtons());
        addButtons(fileInteraction.getButtons());

    }

    private <T extends JButton> void addButtons(Collection<T> buttons) {
        for (T button : buttons) {
            add(button);
        }

        add(new Separator());
    }
}
