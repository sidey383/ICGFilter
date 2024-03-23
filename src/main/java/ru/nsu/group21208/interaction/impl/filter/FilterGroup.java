package ru.nsu.group21208.interaction.impl.filter;

import java.util.Collection;

public class FilterGroup {

    private final Collection<FilterInfo<?>> filters;

    private final String name;


    public FilterGroup(Collection<FilterInfo<?>> filters, String name) {
        this.filters = filters;
        this.name = name;
    }

    public Collection<FilterInfo<?>> getFilters() {
        return filters;
    }

    public String getName() {
        return name;
    }
}
