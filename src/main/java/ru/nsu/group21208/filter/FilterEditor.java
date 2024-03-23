package ru.nsu.group21208.filter;

import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public interface FilterEditor<T extends FilterParams> {

    @Nullable
    JComponent parameterEditor();

    T build();

}
