package ru.nsu.group21208.interaction.toggle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Toggle<T> {

    /**
     * Add toggle actor to listen for item updates.
     * **/
    void addToggleActor(@NotNull ToggleActor<T> actor);

    /**
     * Toggle selected item.<br/>
     * Sends the update of the selected item {@link ToggleActor#toggle(Object)} to everyone except the source.
     * **/
    void toggle(@Nullable ToggleActor<T> source, @Nullable T item);

}
