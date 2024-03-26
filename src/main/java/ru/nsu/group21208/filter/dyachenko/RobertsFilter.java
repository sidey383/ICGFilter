package ru.nsu.group21208.filter.dyachenko;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.DoubleParam;

public class RobertsFilter implements Filter<BaseFilterParams> {
    private BaseFilterEditor editor = new BaseFilterEditor(
        new DoubleParam("Threshold", 0.5, 0, 1, 100)
    );

    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new RobertsTransformation(
                parameters.getValue("Threshold", Double.class)
        );
    }
}
