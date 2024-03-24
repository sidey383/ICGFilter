package ru.nsu.group21208.panel.toolbar;

import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.Toggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import javax.swing.*;
import java.util.Collection;
import java.util.HashMap;

public class ToolModeToggleActor<T> implements ToggleActor<T> {
    private final HashMap<T, ToolButton<T>> modeToButton = new HashMap<>();
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public ToolModeToggleActor(Toggle<T> toggle) {
        toggle.addToggleActor(this);
    }

    protected void addButton(InteractionToggle<T> interactionToggle, T mode) {
        ToolButton<T> button = new ToolButton<>(this, interactionToggle);
        buttonGroup.add(button);
        modeToButton.put(mode, button);
    }

    @Override
    public void toggle(@Nullable T item) {
        if (item == null) {
            buttonGroup.clearSelection();
            return;
        }
        buttonGroup.setSelected(modeToButton.get(item).getModel(), true);
    }

    public Collection<ToolButton<T>> getButtons() {
        return modeToButton.values();
    }
}
