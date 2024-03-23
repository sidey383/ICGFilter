package ru.nsu.group21208.interaction.toggle;

import org.jetbrains.annotations.Nullable;

public interface ToggleActor<T> {

    /**
     * Обновление выбранного объекта. Не отправляется, если данный объект инициировал обновление.
     * **/
    void toggle(@Nullable T item);

}
