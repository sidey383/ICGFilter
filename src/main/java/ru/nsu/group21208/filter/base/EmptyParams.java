package ru.nsu.group21208.filter.base;

import ru.nsu.group21208.filter.FilterParams;

public class EmptyParams implements FilterParams {

    private static final EmptyParams params = new EmptyParams();

    private EmptyParams() {}

    public static EmptyParams getInstance() {
        return params;
    }

}
