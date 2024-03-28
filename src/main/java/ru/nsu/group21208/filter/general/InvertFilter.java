package ru.nsu.group21208.filter.general;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;

public class InvertFilter implements Filter<BaseFilterParams> {
    private final BaseFilterEditor editor = new BaseFilterEditor();
    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new InvertFilterTransformation();
    }
}
