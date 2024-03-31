package ru.nsu.group21208.filter.porotnikov;

import ru.nsu.group21208.filter.Filter;
import ru.nsu.group21208.filter.FilterEditor;
import ru.nsu.group21208.filter.ImageTransformation;
import ru.nsu.group21208.filter.base.BaseFilterEditor;
import ru.nsu.group21208.filter.base.BaseFilterParams;
import ru.nsu.group21208.filter.base.param.IntegerParam;

public class FSDitheringFilter implements Filter<BaseFilterParams> {
    private final String quantRedName = "Quant red degree";
    private final String quantGreenName = "Quant green degree";
    private final String quantBlueName = "Quant blue degree";

    private final BaseFilterEditor editor = new BaseFilterEditor(
            new IntegerParam(quantRedName, 3, 2, 128),
            new IntegerParam(quantGreenName, 3, 2, 128),
            new IntegerParam(quantBlueName, 3, 2, 128)
    );
    @Override
    public FilterEditor<BaseFilterParams> createFilterEditor() {
        return editor;
    }

    @Override
    public ImageTransformation apply(BaseFilterParams parameters) {
        return new FSDitheringFilterTransformation(
                parameters.getValue(quantRedName, Integer.class),
                parameters.getValue(quantGreenName, Integer.class),
                parameters.getValue(quantBlueName, Integer.class));
    }
}
