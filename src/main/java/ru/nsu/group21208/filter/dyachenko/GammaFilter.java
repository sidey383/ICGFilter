package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.DoubleParam;

public class GammaFilter implements Filter<BaseFilterParams> {
    private final BaseFilterEditor editor = new BaseFilterEditor(
            new DoubleParam("gamma", 1, 0.1, 10, 100)
    );

    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        double gamma = parameters.getValue("gamma", Double.class);
        return new GammaTransformation(gamma);
    }
}
