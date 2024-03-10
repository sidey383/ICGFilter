package ru.nsu.group21208.interaction.toggle;

import org.jetbrains.annotations.Nullable;

public interface ToggleActor<T> {

    /**
     * Updating the selected item.
     * **/
    void toggle(@Nullable T item);

}
