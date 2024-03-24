package ru.nsu.group21208.interaction.impl;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.interaction.OriginalToggleInteraction;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;
import ru.nsu.group21208.visualization.ImageFrame;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class OriginalToggleInteractionImpl extends AbstractToggle<OriginalToggleInteraction.Mode> implements OriginalToggleInteraction {
    private final ImageFrame imageFrame;

    public OriginalToggleInteractionImpl(@NotNull ImageFrame imageFrame) {
        this.imageFrame = imageFrame;
    }
    @Override
    public InteractionToggle<Mode> originalInteraction() {
        return new InteractionToggle<>() {
            @Override
            public void toggle(JComponent component, ToggleActor<Mode> actor) {
                OriginalToggleInteractionImpl.super.toggle(actor, Mode.ORIGINAL);
                imageFrame.setImageTransformation(ImageFrame.identicalImageTransformation());
            }

            @Override
            public @NotNull BufferedImage actionImage() {
                return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB); //TODO Add image
            }

            @Override
            public @NotNull String name() {
                return "Original Image";
            }

            @Override
            public @NotNull String description() {
                return "Show original image";
            }
        };
    }

    @Override
    public InteractionToggle<Mode> filterInteraction() {
        return new InteractionToggle<>() {
            @Override
            public void toggle(JComponent component, ToggleActor<Mode> actor) {
                OriginalToggleInteractionImpl.super.toggle(actor, Mode.FILTER);
                imageFrame.setImageTransformation(ImageFrame.identicalImageTransformation());
            }

            @Override
            public @NotNull BufferedImage actionImage() {
                return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB); //TODO Add image
            }

            @Override
            public @NotNull String name() {
                return "Filtered Image";
            }

            @Override
            public @NotNull String description() {
                return "Show filtered image";
            }
        };
    }
}
