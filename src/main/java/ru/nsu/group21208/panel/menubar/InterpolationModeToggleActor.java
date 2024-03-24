package ru.nsu.group21208.panel.menubar;

import ru.nsu.group21208.interaction.InterpolationModInteractions;

public class InterpolationModeToggleActor extends MenuModeToggleActor<InterpolationModInteractions.Mode> {
    public InterpolationModeToggleActor(InterpolationModInteractions interpolationModInteractions) {
        super(interpolationModInteractions, "Interpolation");
        addButton(interpolationModInteractions.bicubicInteraction(), InterpolationModInteractions.Mode.BICUBIC);
        addButton(interpolationModInteractions.bilinearInteraction(), InterpolationModInteractions.Mode.BILINEAR);
        addButton(interpolationModInteractions.nearestNeighborInteraction(), InterpolationModInteractions.Mode.NEAREST_NEIGHBOR);
        interpolationModInteractions.addToggleActor(this);
    }
}
