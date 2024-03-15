package ru.nsu.group21208.interaction.toggle;

import ru.nsu.group21208.interaction.InteractionVisualization;

import javax.swing.*;

public interface ToggleInteraction<T> extends InteractionVisualization {

    void toggle(JComponent component, ToggleActor<T> actor);

}
