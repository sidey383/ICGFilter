package ru.nsu.group21208.interaction.toggle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Toggle<T> {

    /**
     * Зарегестрировать {@link ToggleActor} в этом переключателе.
     * **/
    void addToggleActor(@NotNull ToggleActor<T> actor);

    /**
     * Переключить выбранный элемент.<br/>
     * В случае успеха отправляет обновления во все зарегестрированные {@link ToggleActor#toggle(Object)} за исключением того, кто его вызвал.<br/>
     * **/
    void toggle(@Nullable ToggleActor<T> source, @Nullable T item);

}
