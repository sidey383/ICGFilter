package ru.nsu.group21208.filter;

public interface Filter<T extends FilterParams> {

    FilterEditor<T> createFilterEditor();

    ImageTransformation apply(T parameters);

}
