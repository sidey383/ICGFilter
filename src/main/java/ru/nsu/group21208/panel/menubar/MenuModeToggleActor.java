package ru.nsu.group21208.panel.menubar;

import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.Toggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import javax.swing.*;
import java.util.HashMap;

public class MenuModeToggleActor<T> extends JMenu implements ToggleActor<T> {
    private final HashMap<T, MenuButton<T>> modeToButton = new HashMap<>();
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public MenuModeToggleActor(Toggle<T> toggle) {
        toggle.addToggleActor(this);
    }

    protected void addButton(InteractionToggle<T> interactionToggle, T mode) {
        MenuButton<T> button = new MenuButton<>(this, interactionToggle);
        buttonGroup.add(button);
        modeToButton.put(mode, button);
        add(button);
    }

    @Override
    public void toggle(@Nullable T item) {
        if (item == null) {
            buttonGroup.clearSelection();
            return;
        }
        buttonGroup.setSelected(modeToButton.get(item).getModel(), true);
    }
}
