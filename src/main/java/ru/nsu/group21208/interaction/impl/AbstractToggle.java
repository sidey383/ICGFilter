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

    private Set<T> availableItems = Set.of();

    private final Set<ToggleActor<T>> actors = new HashSet<>();

    public AbstractToggle() {}

    protected void setAvailableItems(Collection<T> items) {
        this.availableItems = Collections.unmodifiableSet(new HashSet<>(items));
    }

    @Override
    public void addToggleActor(@NotNull ToggleActor<T> actor) {
        actors.add(actor);
    }

    @Override
    public void toggle(@Nullable ToggleActor<T> source, @Nullable T item) throws IllegalArgumentException {
        if (!availableItems.contains(item))
            throw new IllegalArgumentException("Unregistered item");
        for (ToggleActor<T> a : actors) {
            if (a == source)
                continue;
            a.toggle(item);
        }
    }
}
