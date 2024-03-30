package ru.nsu.group21208.filter.general;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.EmptyEditor;
import ru.nsu.group21208.filter.base.EmptyParams;

public class InvertFilter implements Filter<EmptyParams> {
    @Override
    public FilterEditor<EmptyParams> createFilterEditor() {
        return EmptyEditor.getInstance();
    }

    @Override
    public ImageTransformation apply(EmptyParams parameters) {
        return new InvertFilterTransformation();
    }
}
