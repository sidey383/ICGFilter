package ru.nsu.group21208.filter.general;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.IntegerParam;

public class WaterColorFilter implements Filter<BaseFilterParams> {
    private final String winSizeName = "Window size";
    private final BaseFilterEditor editor = new BaseFilterEditor(
            new IntegerParam(winSizeName, 5, 3, 100)
    );
    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new WaterColorFilterTransformation(
                parameters.getValue(winSizeName, Integer.class)
        );
    }
}
