package ru.nsu.group21208.interaction;

import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.Toggle;

public interface InterpolationModInteractions extends Toggle<InterpolationModInteractions.Mode> {

    enum Mode {
        BILINEAR, BICUBIC, NEAREST_NEIGHBOR
    }

    InteractionToggle<Mode> bilinearInteraction();

    InteractionToggle<Mode> bicubicInteraction();

    InteractionToggle<Mode> nearestNeighborInteraction();

}
