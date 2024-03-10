package ru.nsu.group21208.interaction.filter;

import ru.nsu.group21208.interaction.InteractionVisualization;

import java.util.Collection;

/**
 * Логически отделенная группа фильтров. <br/>
 * Например: Повороты, дизерниг (у каждого свой) <br/>
 * **/
public interface FilterInteractionsGroup<T extends InteractionVisualization> {

    Collection<T> filterInteractions();

    String name();

}
