package ru.nsu.group21208.interaction;

import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.Toggle;

public interface OriginalToggleInteraction extends Toggle<OriginalToggleInteraction.Mode> {

    enum Mode {
        ORIGINAL, FILTER
    }

    InteractionToggle<Mode> originalInteraction();

    InteractionToggle<Mode> filterInteraction();

}
