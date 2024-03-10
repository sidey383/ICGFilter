package ru.nsu.group21208.filter;

public interface Filter<S extends FilterSettings> {

    S getParameters();

    ImageTransformation apply(S parameters);

}
