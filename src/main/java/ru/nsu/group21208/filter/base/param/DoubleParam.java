package ru.nsu.group21208.filter.base.param;

import ru.nsu.group21208.filter.base.AbstractParam;
import ru.nsu.group21208.filter.base.editor.DoubleParamEditor;

import javax.swing.*;

public class DoubleParam extends AbstractParam<Double> {

    private final double min;

    private final double max;

    private final DoubleParamEditor editor;

    public DoubleParam(String name, double value, double min, double max) {
        super(name, value);
        this.min = min;
        this.max = max;
        this.editor = new DoubleParamEditor(this);
    }

    @Override
    public JComponent editorComponent() {
        return editor;
    }

    public double max() {
        return max;
    }

    public double min() {
        return min;
    }

}
