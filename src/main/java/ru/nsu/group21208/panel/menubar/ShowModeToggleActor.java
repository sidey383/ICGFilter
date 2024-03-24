package ru.nsu.group21208.panel.menubar;

import ru.nsu.group21208.interaction.ShowModeInteractions;

public class ShowModeToggleActor extends MenuModeToggleActor<ShowModeInteractions.Mode> {
    public ShowModeToggleActor(ShowModeInteractions showModeToggle) {
        super(showModeToggle, "Scale");
        addButton(showModeToggle.realModeInteraction(), ShowModeInteractions.Mode.REAL);
        addButton(showModeToggle.adaptableModeInteraction(), ShowModeInteractions.Mode.ADAPTABLE);
        showModeToggle.addToggleActor(this);
    }
}
