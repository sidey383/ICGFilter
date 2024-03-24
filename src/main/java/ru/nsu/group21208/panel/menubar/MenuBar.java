package ru.nsu.group21208.panel.menubar;

import ru.nsu.group21208.panel.PanelInteractionStorage;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public MenuBar(PanelInteractionStorage panelInteractionStorage) {
        add(new ShowModeToggleActor(panelInteractionStorage.getShowModeInteractions()));
        add(new OriginalToggleActor(panelInteractionStorage.getOriginalToggleInteraction()));
        add(new InterpolationModeToggleActor(panelInteractionStorage.getInterpolationModInteractions()));
        add(new FileInteraction(panelInteractionStorage.getFileInteractions()));
        add(new FilterToggleActor<>(panelInteractionStorage.getFilterInteractions()));
    }
}
