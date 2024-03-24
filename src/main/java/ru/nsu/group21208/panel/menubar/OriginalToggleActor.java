package ru.nsu.group21208.panel.menubar;

import ru.nsu.group21208.interaction.OriginalToggleInteraction;

public class OriginalToggleActor extends MenuModeToggleActor<OriginalToggleInteraction.Mode> {
    public OriginalToggleActor(OriginalToggleInteraction originalToggleInteraction) {
        super(originalToggleInteraction, "Toggle");
        addButton(originalToggleInteraction.originalInteraction(), OriginalToggleInteraction.Mode.ORIGINAL);
        addButton(originalToggleInteraction.filterInteraction(), OriginalToggleInteraction.Mode.FILTER);
        originalToggleInteraction.addToggleActor(this);
    }
}
