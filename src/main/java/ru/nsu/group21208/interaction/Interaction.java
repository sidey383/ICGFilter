package ru.nsu.group21208.interaction;

import javax.swing.*;

/**
 * Иниаицаия взаимодействия пользователя с программой.
 * **/
public interface Interaction extends InteractionVisualization {

    void apply(JComponent action);

}
