package ru.nsu.group21208.interaction.impl;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.interaction.ShowModeInteractions;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;
import ru.nsu.group21208.visualization.ImageFrame;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ShowModeInteractionsImpl extends AbstractToggle<ShowModeInteractions.Mode> implements ShowModeInteractions {
    private final ImageFrame _imageFrame;

    public ShowModeInteractionsImpl(@NotNull ImageFrame imageFrame) {
        _imageFrame = imageFrame;
    }
    @Override
    public InteractionToggle<Mode> realModeInteraction() {
        return new InteractionToggle<Mode>() {
            @Override
            public void toggle(JComponent component, ToggleActor<Mode> actor) {
                ShowModeInteractionsImpl.super.toggle(actor, Mode.REAL);
                _imageFrame.setAdaptive(false);
            }

            @Override
            public @NotNull BufferedImage actionImage() {
                return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB); //TODO Add image
            }

            @Override
            public @NotNull String name() {
                return "Real";
            }

            @Override
            public @NotNull String description() {
                return "Pixel-by-pixel showing";
            }
        };
    }

    @Override
    public InteractionToggle<Mode> adaptableModeInteraction() {
        return new InteractionToggle<Mode>() {
            @Override
            public void toggle(JComponent component, ToggleActor<Mode> actor) {
                ShowModeInteractionsImpl.super.toggle(actor, Mode.ADAPTABLE);
                _imageFrame.setAdaptive(true);
            }

            @Override
            public @NotNull BufferedImage actionImage() {
                return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB); //TODO Add image
            }

            @Override
            public @NotNull String name() {
                return "Adaptable";
            }

            @Override
            public @NotNull String description() {
                return "Adapt picture for screen";
            }
        };
    }
}
