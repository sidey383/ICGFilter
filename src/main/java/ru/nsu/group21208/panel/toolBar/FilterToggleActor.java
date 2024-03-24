package ru.nsu.group21208.panel.toolBar;

import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.interaction.filter.FilterInteractions;
import ru.nsu.group21208.interaction.filter.FilterInteractionsGroup;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import javax.swing.*;
import java.util.HashMap;

public class FilterToggleActor<T extends InteractionToggle<T>> implements ToggleActor<T> {
    private final HashMap<T, ToolButton<T>> modeToButton = new HashMap<>();
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public FilterToggleActor(FilterInteractions<T> filterInteractions, ToolBar toolBar) {
        filterInteractions.addToggleActor(this);
        for (FilterInteractionsGroup<T> group : filterInteractions.filterGroups()) {
            for (T filter : group.filterInteractions()) {
                ToolButton<T> button = new ToolButton<>(this, filter);
                buttonGroup.add(button);
                modeToButton.put(filter, button);
                toolBar.add(button);
            }
            toolBar.add(new JToolBar.Separator());
        }
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
