package ru.nsu.group21208.filter.base;

import org.jetbrains.annotations.Nullable;
import ru.nsu.group21208.filter.FilterEditor;

import javax.swing.*;

public class EmptyEditor implements FilterEditor<EmptyParams> {

    private static final EmptyEditor editor = new EmptyEditor();

    private EmptyEditor() {}

    public static EmptyEditor getInstance() {
        return editor;
    }

    @Override
    public @Nullable JComponent parameterEditor() {
        return null;
    }

    @Override
    public EmptyParams build() {
        return EmptyParams.getInstance();
    }

}
