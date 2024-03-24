package ru.nsu.group21208.interaction.impl;

import org.jetbrains.annotations.NotNull;
import ru.nsu.group21208.interaction.InteractionVisualization;

import java.awt.image.BufferedImage;

public class ButtonInfo implements InteractionVisualization {


    private final BufferedImage image;

    private final String name;

    private final String description;

    public ButtonInfo(BufferedImage image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    @NotNull
    @Override
    public BufferedImage actionImage() {
        return image;
    }

    @NotNull
    @Override
    public String name() {
        return name;
    }

    @NotNull
    @Override
    public String description() {
        return description;
    }

}
