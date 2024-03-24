package ru.nsu.group21208.panel.menuBar;

import ru.nsu.group21208.interaction.ShowModeInteractions;

public class ShowModeToggleActor extends MenuModeToggleActor<ShowModeInteractions.Mode> {
    public ShowModeToggleActor(ShowModeInteractions showModeToggle) {
        super(showModeToggle);
        addButton(showModeToggle.realModeInteraction(), ShowModeInteractions.Mode.REAL);
        addButton(showModeToggle.adaptableModeInteraction(), ShowModeInteractions.Mode.ADAPTABLE);
    }
}
