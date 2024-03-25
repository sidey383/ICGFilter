package ru.nsu.group21208.panel.menubar;

import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.interaction.filter.FilterInteractions;
import ru.nsu.group21208.interaction.filter.FilterInteractionsGroup;
import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import javax.swing.*;
import java.util.HashMap;

public class FilterToggleActor<T extends InteractionToggle<T>> extends JMenu implements ToggleActor<T> {
    private final HashMap<T, MenuButton<T>> modeToButton = new HashMap<>();
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public FilterToggleActor(FilterInteractions<T> filterInteractions) {
        super("Filters");
        filterInteractions.addToggleActor(this);
        for (FilterInteractionsGroup<T> group : filterInteractions.filterGroups()) {
            JMenu menuGroup = new JMenu(group.name());
            menuGroup.setName(group.name());
            for (T filter : group.filterInteractions()) {
                MenuButton<T> button = new MenuButton<>(this, filter);
                buttonGroup.add(button);
                modeToButton.put(filter, button);
                menuGroup.add(button);
                button.addActionListener((a) -> filter.toggle(button, this));
            }
            add(menuGroup);
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
