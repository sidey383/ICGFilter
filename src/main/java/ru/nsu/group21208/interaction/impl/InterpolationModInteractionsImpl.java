package ru.nsu.group21208.interaction.impl;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.interaction.InterpolationModInteractions;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;
import ru.nsu.group21208.visualization.ImageFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class InterpolationModInteractionsImpl extends AbstractToggle<InterpolationModInteractions.Mode> implements InterpolationModInteractions{
    private ImageFrame _imageFrame;

    public InterpolationModInteractionsImpl(@NotNull ImageFrame imageFrame) {
        _imageFrame = imageFrame;
    }
    @Override
    public InteractionToggle<Mode> bilinearInteraction() {
        return new InteractionToggle<Mode>() {
            @Override
            public void toggle(JComponent component, ToggleActor<Mode> actor) {
                InterpolationModInteractionsImpl.super.toggle(actor, Mode.BILINEAR);
                _imageFrame.setInterpolationMode(RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            }

            @Override
            public @NotNull BufferedImage actionImage() {
                return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB); //TODO Add image
            }

            @Override
            public @NotNull String name() {
                return "Bilinear Interpolation";
            }

            @Override
            public @NotNull String description() {
                return "Change interpolation to bilinear";
            }
        };
    }

    @Override
    public InteractionToggle<Mode> bicubicInteraction() {
        return new InteractionToggle<Mode>() {
            @Override
            public void toggle(JComponent component, ToggleActor<Mode> actor) {
                InterpolationModInteractionsImpl.super.toggle(actor, Mode.BICUBIC);
                _imageFrame.setInterpolationMode(RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            }

            @Override
            public @NotNull BufferedImage actionImage() {
                return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB); //TODO Add image
            }

            @Override
            public @NotNull String name() {
                return "Bicubic Interpolation";
            }

            @Override
            public @NotNull String description() {
                return "Change interpolation to bicubic";
            }
        };
    }

    @Override
    public InteractionToggle<Mode> nearestNeighborInteraction() {
        return new InteractionToggle<Mode>() {
            @Override
            public void toggle(JComponent component, ToggleActor<Mode> actor) {
                InterpolationModInteractionsImpl.super.toggle(actor, Mode.NEAREST_NEIGHBOR);
                _imageFrame.setInterpolationMode(RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            }

            @Override
            public @NotNull BufferedImage actionImage() {
                return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB); //TODO Add image
            }

            @Override
            public @NotNull String name() {
                return "Nearest Neighbor Interpolation";
            }

            @Override
            public @NotNull String description() {
                return "Change interpolation to nearest neighbor";
            }
        };
    }
}
