package ru.nsu.group21208.interaction.impl;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.interaction.OriginalToggleInteraction;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;
import ru.nsu.group21208.visualization.ImageFrame;

import javax.swing.*;

public class OriginalToggleInteractionImpl extends AbstractToggle<OriginalToggleInteraction.Mode> implements OriginalToggleInteraction {

    private final InteractionToggle<Mode> originalInteraction;

    private final InteractionToggle<Mode> filterInteraction;

    public OriginalToggleInteractionImpl(@NotNull ImageFrame imageFrame, ButtonInfo original, ButtonInfo filter) {
        this.originalInteraction = new InteractionToggleImpl<>(original) {
            @Override
            public void toggle(JComponent component, ToggleActor<Mode> actor) {
                OriginalToggleInteractionImpl.super.toggle(actor, Mode.ORIGINAL);
                imageFrame.setImageTransformation(ImageFrame.identicalImageTransformation());
            }
        };

        this.filterInteraction = new InteractionToggleImpl<>(filter) {
            @Override
            public void toggle(JComponent component, ToggleActor<Mode> actor) {
                OriginalToggleInteractionImpl.super.toggle(actor, Mode.FILTER);
                imageFrame.setImageTransformation(ImageFrame.identicalImageTransformation());
            }
        };

    }
    @Override
    public InteractionToggle<Mode> originalInteraction() {
        return originalInteraction;
    }

    @Override
    public InteractionToggle<Mode> filterInteraction() {
        return filterInteraction;
    }
}
