package ru.nsu.group21208.interaction;

import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

public interface InteractionVisualization {

    @NotNull
    BufferedImage actionImage();

    @NotNull
    String name();

    @NotNull
    String description();

}
