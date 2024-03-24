package ru.nsu.group21208.interaction.impl.filter;

import ru.nsu.group21208.interaction.filter.FilterInteractionsGroup;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FilterInteractionGroupImpl implements FilterInteractionsGroup<FilterHolder<?>> {

    private final String name;

    private final Collection<FilterHolder<?>> holders;

    public FilterInteractionGroupImpl(FilterInteractionImpl parent, FilterGroup group, Component baseComponent) {
        this.name = group.getName();
        List<FilterHolder<?>> holders = new ArrayList<>();
        for (FilterInfo<?> info : group.getFilters()) {
            holders.add(new FilterHolder<>(parent, info, baseComponent));
        }
        this.holders = holders;
    }

    @Override
    public Collection<FilterHolder<?>> filterInteractions() {
        return holders;
    }

    @Override
    public String name() {
        return name;
    }
}
