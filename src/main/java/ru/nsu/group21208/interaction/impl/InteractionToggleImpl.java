package ru.nsu.group21208.interaction.impl;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;

import java.awt.image.BufferedImage;

public abstract class InteractionToggleImpl<T> implements InteractionToggle<T> {

    private final ButtonInfo info;

    public InteractionToggleImpl(ButtonInfo info) {
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
