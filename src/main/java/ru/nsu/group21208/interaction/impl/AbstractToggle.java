package ru.nsu.group21208.interaction.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.interaction.toggle.Toggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AbstractToggle<T> implements Toggle<T> {

    private T selected = null;

    private Set<T> availableItems = Set.of();

    private final Set<ToggleActor<T>> actors = new HashSet<>();

    public AbstractToggle() {}

    protected void setAvailableItems(Collection<T> items, T selected) {
        this.availableItems = Collections.unmodifiableSet(new HashSet<>(items));
        this.selected = selected;
    }

    @Override
    public void addToggleActor(@NotNull ToggleActor<T> actor) {
        actors.add(actor);
        actor.toggle(selected);
    }

    @Override
    public void toggle(@Nullable ToggleActor<T> source, @Nullable T item) throws IllegalArgumentException {
        if (item != null && !availableItems.contains(item))
            throw new IllegalArgumentException("Unregistered item");
        for (ToggleActor<T> a : actors) {
            if (a == source)
                continue;
            a.toggle(item);
        }
    }
}
