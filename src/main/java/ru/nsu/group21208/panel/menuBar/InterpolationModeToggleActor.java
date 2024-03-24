package ru.nsu.group21208.panel.menuBar;

import ru.nsu.group21208.interaction.InterpolationModInteractions;

public class InterpolationModeToggleActor extends MenuModeToggleActor<InterpolationModInteractions.Mode> {
    public InterpolationModeToggleActor(InterpolationModInteractions interpolationModInteractions) {
        super(interpolationModInteractions);
        addButton(interpolationModInteractions.bicubicInteraction(), InterpolationModInteractions.Mode.BICUBIC);
        addButton(interpolationModInteractions.bilinearInteraction(), InterpolationModInteractions.Mode.BILINEAR);
        addButton(interpolationModInteractions.nearestNeighborInteraction(), InterpolationModInteractions.Mode.NEAREST_NEIGHBOR);
    }
}
