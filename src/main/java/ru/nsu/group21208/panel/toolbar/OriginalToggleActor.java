package ru.nsu.group21208.panel.toolbar;

import ru.nsu.group21208.interaction.OriginalToggleInteraction;

public class OriginalToggleActor extends ToolModeToggleActor<OriginalToggleInteraction.Mode> {
    public OriginalToggleActor(OriginalToggleInteraction originalToggleInteraction) {
        super(originalToggleInteraction);
        addButton(originalToggleInteraction.originalInteraction(), OriginalToggleInteraction.Mode.ORIGINAL);
        addButton(originalToggleInteraction.filterInteraction(), OriginalToggleInteraction.Mode.FILTER);
        originalToggleInteraction.addToggleActor(this);
    }
}
