package ru.nsu.group21208.interaction.toggle;

import ru.nsu.group21208.interaction.InteractionVisualization;

import javax.swing.*;

public interface InteractionToggle<T> extends InteractionVisualization {

    void toggle(JComponent component, ToggleActor<T> actor);

}
