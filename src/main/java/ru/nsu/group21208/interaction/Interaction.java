package ru.nsu.group21208.interaction;

import java.awt.*;

/**
 * Иниаицаия взаимодействия пользователя с программой.
 * **/
public interface Interaction extends InteractionVisualization {

    void apply(Component action);

}
