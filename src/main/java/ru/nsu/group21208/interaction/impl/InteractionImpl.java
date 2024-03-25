package ru.nsu.group21208.interaction.impl;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.interaction.Interaction;

import java.awt.image.BufferedImage;

public abstract class InteractionImpl implements Interaction {

    private final ButtonInfo info;

    public InteractionImpl(ButtonInfo info) {
        this.info = info;
    }

    @Override
    public @NotNull BufferedImage actionImage() {
        return info.actionImage();
    }

    @Override
    public @NotNull String name() {
        return info.name();
    }

    @Override
    public @NotNull String description() {
        return info.description();
    }
}
