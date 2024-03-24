package ru.nsu.group21208.interaction.impl;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.interaction.InterpolationModInteractions;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;
import ru.nsu.group21208.visualization.ImageFrame;

import javax.swing.*;
import java.util.List;

public class InterpolationModInteractionsImpl extends AbstractToggle<InterpolationModInteractions.Mode> implements InterpolationModInteractions{
    private final ImageFrame imageFrame;

    private final InteractionToggle<Mode> bilinearInteraction;

    private final InteractionToggle<Mode> bicubicInteraction;

    private final InteractionToggle<Mode> nearestNeighborInteraction;

    public InterpolationModInteractionsImpl(@NotNull ImageFrame imageFrame, ButtonInfo bilinear, ButtonInfo bicubic, ButtonInfo nearest) {
        this.imageFrame = imageFrame;
        this.bilinearInteraction = new ModeInteractionToggle(bilinear, Mode.BILINEAR);
        this.bicubicInteraction = new ModeInteractionToggle(bicubic, Mode.BICUBIC);
        this.nearestNeighborInteraction = new ModeInteractionToggle(nearest, Mode.NEAREST_NEIGHBOR);
        setAvailableItems(List.of(Mode.values()));
    }
    @Override
    public InteractionToggle<Mode> bilinearInteraction() {
        return bilinearInteraction;
    }

    @Override
    public InteractionToggle<Mode> bicubicInteraction() {
        return bicubicInteraction;
    }

    @Override
    public InteractionToggle<Mode> nearestNeighborInteraction() {
        return nearestNeighborInteraction;
    }

    private class ModeInteractionToggle extends InteractionToggleImpl<Mode> implements InteractionToggle<Mode> {

        private final Mode target;

        public ModeInteractionToggle(ButtonInfo info, Mode target) {
            super(info);
            this.target = target;
        }

        @Override
        public void toggle(JComponent component, ToggleActor<Mode> actor) {
            InterpolationModInteractionsImpl.super.toggle(actor, target);
            imageFrame.setInterpolationMode(target.getHints());
        }
    }

}
