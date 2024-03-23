package ru.nsu.group21208.filter.base;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class AbstractParam<T> {

    private final String name;

    private T value;

    public AbstractParam(String name, T value) {
        this.value = value;
        this.name = name;
    }

    public void setValue(@NotNull T value) {
        this.value = value;
    }

    @NotNull
    public T getValue() {
        return this.value;
    }

    public String name() {
        return name;
    }

    public abstract JComponent editorComponent();

}
