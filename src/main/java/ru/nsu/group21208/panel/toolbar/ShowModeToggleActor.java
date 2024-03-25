package ru.nsu.group21208.panel.toolbar;

import ru.nsu.group21208.interaction.ShowModeInteractions;

public class ShowModeToggleActor extends ToolModeToggleActor<ShowModeInteractions.Mode> {
    public ShowModeToggleActor(ShowModeInteractions showModeToggle) {
        super(showModeToggle);
        addButton(showModeToggle.realModeInteraction(), ShowModeInteractions.Mode.REAL);
        addButton(showModeToggle.adaptableModeInteraction(), ShowModeInteractions.Mode.ADAPTABLE);
        showModeToggle.addToggleActor(this);
    }
}
