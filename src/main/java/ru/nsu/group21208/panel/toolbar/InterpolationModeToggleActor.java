package ru.nsu.group21208.panel.toolbar;

import ru.nsu.group21208.interaction.InterpolationModInteractions;

public class InterpolationModeToggleActor extends ToolModeToggleActor<InterpolationModInteractions.Mode> {
    public InterpolationModeToggleActor(InterpolationModInteractions interpolationModInteractions) {
        super(interpolationModInteractions);
        addButton(interpolationModInteractions.bicubicInteraction(), InterpolationModInteractions.Mode.BICUBIC);
        addButton(interpolationModInteractions.bilinearInteraction(), InterpolationModInteractions.Mode.BILINEAR);
        addButton(interpolationModInteractions.nearestNeighborInteraction(), InterpolationModInteractions.Mode.NEAREST_NEIGHBOR);
    }
}
