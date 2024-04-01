package ru.nsu.group21208.filter.porotnikov;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.IntegerParam;


public class VignetteFilter implements Filter<BaseFilterParams> {
    private final String sigmaName = "Sigma";

    private final BaseFilterEditor editor = new BaseFilterEditor(
            new IntegerParam(sigmaName, 100, 1, 1000));

    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new VignetteFilterTransformation(parameters.getValue(sigmaName, Integer.class));
    }
}
