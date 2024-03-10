package ru.nsu.group21208.filter;

import org.jetbrains.annotations.NotNull;

//TODO: добавить другие типы параметров (как минимум перечисления)
public class FilterParameter<T> {

    @NotNull
    private final String name;

    @NotNull
    private T value;

    public FilterParameter(@NotNull String name, @NotNull T value) {
        this.name = name;
        this.value = value;
    }

    @NotNull
    public T getValue() {
        return value;
    }

    public void setValue(@NotNull T value) {
        this.value = value;
    }

    @NotNull
    public String getName() {
        return name;
    }

}
