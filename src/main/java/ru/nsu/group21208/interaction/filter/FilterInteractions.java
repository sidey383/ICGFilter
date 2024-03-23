package ru.nsu.group21208.interaction.filter;

import ru.nsu.group21208.interaction.toggle.InteractionToggle;
import ru.nsu.group21208.interaction.toggle.Toggle;
import ru.nsu.group21208.interaction.toggle.ToggleActor;

import java.util.Collection;

/**
 * Взаимодействие пользователя с фильтрами. <br/>
 * Переключение текущего фильтра происходит через {@link Toggle#toggle(ToggleActor, Object)}
 * **/
public interface FilterInteractions<T extends InteractionToggle<T>> extends Toggle<T> {

    /**
     * @return Все доступные группы фильтров
     * **/
    Collection<FilterInteractionsGroup<T>> filterGroups();

    /**
     * @return Все доступные фильтры
     * **/
    Collection<T> filterInteractions();

}
